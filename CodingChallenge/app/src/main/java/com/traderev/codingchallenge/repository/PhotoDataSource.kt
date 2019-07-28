package com.traderev.codingchallenge.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.traderev.codingchallenge.BuildConfig
import com.traderev.codingchallenge.common.LogType
import com.traderev.codingchallenge.common.State
import com.traderev.codingchallenge.model.Photos
import com.traderev.codingchallenge.network.PhotoService
import com.traderev.codingchallenge.utility.UtilHelper
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class PhotoDataSource(
    private val photoService: PhotoService,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Photos>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null
    var responsePhotos = MutableLiveData<List<Photos>>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photos>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(
            photoService.getPhotos(BuildConfig.UNSPLASH_API_KEYS, 1, params.requestedLoadSize, "")
                .subscribe(
                    { response ->

                        UtilHelper.displayLog(
                            "" + responsePhotos.value?.size,
                            PhotoDataSource::class.java.simpleName,
                            LogType.DEBUG
                        )
                        this.responsePhotos.postValue(response)
                        UtilHelper.displayLog(
                            "" + responsePhotos.value?.size,
                            PhotoDataSource::class.java.simpleName,
                            LogType.DEBUG
                        )
                        updateState(State.DONE)

                        callback.onResult(
                            response,
                            null,
                            2
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photos>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            photoService.getPhotos(BuildConfig.UNSPLASH_API_KEYS, params.key, params.requestedLoadSize, "")
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        this.responsePhotos.postValue(response)
                        UtilHelper.displayLog(
                            "" + responsePhotos.value?.size,
                            PhotoDataSource::class.java.simpleName,
                            LogType.DEBUG
                        )

                        callback.onResult(
                            response,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photos>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}
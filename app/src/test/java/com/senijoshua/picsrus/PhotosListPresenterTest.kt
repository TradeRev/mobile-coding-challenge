package com.senijoshua.picsrus

import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.data.repo.PhotoRepoImpl
import com.senijoshua.picsrus.presentation.photolist.PhotoListContract
import com.senijoshua.picsrus.presentation.photolist.PhotoListPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.TestScheduler

class PhotosListPresenterTest {

    @Mock
    lateinit var viewInstance: PhotoListContract.PhotoView

    @Mock
    lateinit var photoRepo: PhotoRepoImpl

    lateinit var presenter: PhotoListPresenter

    //Mock the schedulers using RxJava's TestScheduler
    private val testScheduler: TestScheduler = TestScheduler()

    private val dummyPhotos: List<Photos> =  ArrayList()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = PhotoListPresenter(photoRepo, viewInstance, testScheduler, testScheduler)
    }

    /**
     * Given that the presenter has requested for the photos
     * and the remote source returns them successfully,
     * verify that the view receives it
     */
    @Test
    fun getPhotos_RetrievalPositive() {
        doReturn(Observable.just(dummyPhotos)).`when`(photoRepo).getPhotosList(ArgumentMatchers.anyInt())

        presenter.loadPhotoList(1)
        testScheduler.triggerActions()

        verify(viewInstance).onPhotoListLoaded(dummyPhotos)
    }

    /**
     * Given that the presenter has requested for the photos
     * but the remote source returns an error,
     * verify that the view acts accordingly
     */
    @Test(expected = Throwable::class)
    fun getPhotos_RetrievalNegative(){
        doThrow(Throwable()).`when`(photoRepo).getPhotosList(ArgumentMatchers.anyInt())

        presenter.loadPhotoList(1)
        testScheduler.triggerActions()

        verify(viewInstance).photoListLoadError(ArgumentMatchers.anyString())
    }

}
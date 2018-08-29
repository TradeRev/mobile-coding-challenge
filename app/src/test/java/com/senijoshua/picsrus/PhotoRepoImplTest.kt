package com.senijoshua.picsrus

import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.data.repo.PhotoRepoAPI
import com.senijoshua.picsrus.data.repo.PhotoRepoImpl
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.observers.TestSubscriber
import junit.framework.Assert.assertEquals
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response


class PhotoRepoImplTest {

    @Mock
    lateinit var apiInterface: PhotoRepoAPI

    lateinit var photoRepoImpl: PhotoRepoImpl

    var testSubscriber: TestSubscriber<List<Photos>> = TestSubscriber()

    private val dummyPhotos: List<Photos> = MockImageRepo().getPhotoData()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        photoRepoImpl = PhotoRepoImpl(apiInterface)
    }

    /**
     * Given that the apiInterface requests photos
     * from the remote source and that there's no issue,
     * test that the Repo implementation receives a list of Photos
     */
    @Test
    fun getPhotos_returnsPhotosList(){
        val pageNumber = 1
        `when`(apiInterface.getPhotos(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Observable.just(dummyPhotos))

        photoRepoImpl.getPhotosList(pageNumber).subscribe(testSubscriber)

        testSubscriber.assertNoErrors()

        //get the emitted items
        val onNextEvents: List<List<Photos>> = testSubscriber.onNextEvents
        val photos = onNextEvents[0]
        //verify that the emitted items match expectations
        assertEquals(dummyPhotos[0].id, photos[0].id)
        //verify that the api had the proper query parameters
        verify(apiInterface).getPhotos(pageNumber)
    }

    /**
     * Given that the apiInterface requests photos
     * from the remote source and a httpException is thrown,
     * test that there was a terminating error on the subscriber
     */
    @Test
    fun getPhotos_returnsHttpError(){
        val pageNumber = 1

        `when`(apiInterface.getPhotos(pageNumber)).thenReturn(get422UnprocessableError())

        photoRepoImpl.getPhotosList(pageNumber).subscribe(testSubscriber)

        testSubscriber.assertError(HttpException::class.java)
    }


    private fun get422UnprocessableError(): Observable<List<Photos>> {
        return Observable.error<List<Photos>>(HttpException(
                Response.error<Any>(422, ResponseBody.create(MediaType.parse("application/json"), "Unprocessable Entity"))))

    }

}
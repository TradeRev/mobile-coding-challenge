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
        verify(apiInterface).getPhotos(eq(pageNumber), ArgumentMatchers.anyInt(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
    }

}
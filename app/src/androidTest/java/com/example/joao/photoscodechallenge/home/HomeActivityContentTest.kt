package com.example.joao.photoscodechallenge.home


import com.example.joao.photoscodechallenge.AcceptanceTest
import com.example.joao.photoscodechallenge.robots.robot
import com.example.joao.photoscodechallenge.ui.MainActivity
import com.github.salomonbrys.kodein.Kodein
import okhttp3.mockwebserver.MockResponse
import org.junit.Test

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class HomeActivityContentTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun testWithContentState() {

        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getJson("success.json")))

        startActivity()

        robot{
            withLoading()
        } withContent {
            isSuccess()
        }
    }
    override val testDependencies = Kodein.Module(allowSilentOverride = true) {}
}
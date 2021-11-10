package com.example.githubusers.data.api



import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.data.sources.remoteApi.ApiService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class UsersServiceTest : BaseServiceTest<ApiService>() {

    private lateinit var service: ApiService
    private lateinit var results: UsersResponseItem


    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchUsersListTest()   {
        enqueueResponse("/UsersResponse.json")
        runBlocking {
            results = requireNotNull(service.getUsers().get(0))
        }
        mockWebServer.takeRequest()

        assertThat(results.login,`is`("mojombo"))



    }


}

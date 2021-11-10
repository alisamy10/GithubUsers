package com.example.githubusers.data

import com.example.githubusers.common.NetworkAwareHandler
import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.data.sources.homeCahedData.OfflineDataSource
import com.example.githubusers.data.sources.remoteApi.OnlineDataSource
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test


class UsersRepositoryTest {

   private val fakeList= mutableListOf<UsersResponseItem>().apply {
        add(UsersResponseItem(
          login="Ali",
            id=1
        ))

        add(UsersResponseItem(
            login="ahmed",
            id=2
        ))

    }

    private val exepected= listOf(
        UsersResponseItem(
            login="Ali",
            id=1
        ),
        UsersResponseItem(
            login="ahmed",
            id=2
        )
    )



    @Test
    fun getUsers_andInsertit_inRoom(){

        runBlocking {
           val  offlineDataSource = object : OfflineDataSource {
                override fun getLocalUsers(): List<UsersResponseItem> {
                    return fakeList
                }
            }
            val usersRepository = UsersRepositoryImpl(object : OnlineDataSource {},object : NetworkAwareHandler {}
                ,offlineDataSource)

            val result = usersRepository.getUsers()
            assertThat(result, Is.`is`(exepected))
        }
    }


    @Test
    fun getUsers_withOnlineNetwork_thenReturnListOfSourcesFromOfflineDataSource() {
        // run blocking to call suspend function or Coroutines scope
        runBlocking {
            val  offlineDataSource = object : OfflineDataSource {
                override fun getLocalUsers(): List<UsersResponseItem> {
                    return fakeList
                }
            }
            val usersRepository = UsersRepositoryImpl(object : OnlineDataSource {},
                object : NetworkAwareHandler {},offlineDataSource)

            val result = usersRepository.getUsers()
            assertThat(result, Is.`is`(exepected))
        }

    }


    @Test
    fun getUsers_withOfflineNetwork_thenReturnListOfUsersFromOfflineDataSource() {
        runBlocking {

            val  offlineDataSource = object : OfflineDataSource {
                override fun getLocalUsers(): List<UsersResponseItem> {
                    return fakeList
                }
            }
            val networkAwareHandler =object : NetworkAwareHandler {
                override fun isOnline(): Boolean = false
            }

            val usersRepository = UsersRepositoryImpl( object : OnlineDataSource {},
                networkAwareHandler,
                offlineDataSource,)


            val result = usersRepository.getUsers()

            assertThat(result, Is.`is`(exepected))
        }

    }

    @Test
    fun getUsers_withOnlineNetwork_verifyGetUsersFromNetworkCalled() {
        runBlocking {
            var isGetSourcesInvoked = false

            val onlineDataSource = object : OnlineDataSource {
                override suspend fun getUsers(): List<UsersResponseItem> {
                    isGetSourcesInvoked = true
                    return listOf()
                }
            }

            val usersRepository = UsersRepositoryImpl(onlineDataSource,
                object : NetworkAwareHandler {},
                object : OfflineDataSource {},
                )

            usersRepository.getUsers()

            assertThat(isGetSourcesInvoked, Is.`is`(true))
        }

    }

    @Test
    fun getUsers_withOnlineNetwork_verifyCacheUsersCalled() {
        runBlocking {
            var isCachedInvoked = false

            val onlineDataSource = object : OnlineDataSource {
                override suspend fun getUsers(): List<UsersResponseItem> {
                    return listOf()
                }
            }

            val offlineDataSource = object : OfflineDataSource {
                override suspend fun cacheUsers(data: List<UsersResponseItem>) {
                    isCachedInvoked = true
                }
            }


            val usersRepository = UsersRepositoryImpl(
                onlineDataSource, object : NetworkAwareHandler {}, offlineDataSource)

            usersRepository.getUsers()

            assertThat(isCachedInvoked, Is.`is`(true))
        }
    }

    @Test
    fun getUsers_withOfflineNetwork_verifyGetUsersFromOfflineDataSourceIsCalled() {
        runBlocking {
            var isGetSourcesInvoked = false

            val onlineDataSource = object : OnlineDataSource {
                override suspend fun getUsers(): List<UsersResponseItem> {
                    isGetSourcesInvoked = true
                    return listOf()
                }
            }

            val usersRepository = UsersRepositoryImpl(onlineDataSource,object : NetworkAwareHandler {},
                object : OfflineDataSource {},)

            usersRepository.getUsers()

            assertThat(isGetSourcesInvoked, Is.`is`(true))
        }
    }
}


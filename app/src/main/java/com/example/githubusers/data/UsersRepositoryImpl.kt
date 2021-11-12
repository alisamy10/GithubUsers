package com.example.githubusers.data


import android.util.Log
import com.example.githubusers.common.NetworkAwareHandler
import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.data.sources.homeCahedData.OfflineDataSource
import com.example.githubusers.data.sources.remoteApi.OnlineDataSource
import com.example.githubusers.domain.UsersRepository
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val onlineDataSource: OnlineDataSource,
    private val networkHandler: NetworkAwareHandler,
    private val offlineDataSource: OfflineDataSource,
): UsersRepository {


    override suspend fun getUsers(): List<UsersResponseItem> {
        // you can change this logic depend on the business requirements
        return if (networkHandler.isOnline()) {
            cacheArticles(getRemoteData())
            getCachedData()
        } else {
            getCachedData()
        }
    }


    private suspend fun getRemoteData(): List<UsersResponseItem> = onlineDataSource.getUsers()

    private suspend fun cacheArticles(data: List<UsersResponseItem>) = offlineDataSource.cacheUsers(data)
    private suspend fun getCachedData(): List<UsersResponseItem> = offlineDataSource.getLocalUsers()

    override suspend fun updateFavorite(isFv: Boolean, login: String) = offlineDataSource.updateFav(isFv, login)


}


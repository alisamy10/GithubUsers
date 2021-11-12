package com.example.githubusers.data.sources.homeCahedData

import com.example.githubusers.data.model.UsersResponseItem
import javax.inject.Inject

interface OfflineDataSource {
    suspend fun  getLocalUsers(): List<UsersResponseItem> = emptyList()

    suspend fun cacheUsers(data: List<UsersResponseItem>) {}

    suspend fun updateFav(isFv: Boolean, login: String) {}


}


class OfflineDataSourceImpl @Inject constructor(private val usersDao: UsersDao) :
    OfflineDataSource {

    override suspend fun getLocalUsers(): List<UsersResponseItem> = usersDao.getAllUsers()

    override suspend fun cacheUsers(data: List<UsersResponseItem>) {
        usersDao.insertUsers(data)
    }

    override suspend fun updateFav(isFv: Boolean, login: String) {
        usersDao.updateFav(isFv, login)
    }

}
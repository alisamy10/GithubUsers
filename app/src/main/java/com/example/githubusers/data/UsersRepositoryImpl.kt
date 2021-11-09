package com.example.githubusers.data


import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.data.sources.remoteApi.OnlineDataSource
import com.example.githubusers.domain.UsersRepository
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(private val onlineDataSource: OnlineDataSource): UsersRepository {


    override suspend fun getUsers(): List<UsersResponseItem> = getRemoteData()


    private suspend fun getRemoteData(): List<UsersResponseItem> = onlineDataSource.getUsers()

}


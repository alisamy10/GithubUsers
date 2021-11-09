package com.example.githubusers.data.sources.remoteApi

import com.example.githubusers.data.model.UsersResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ApiHelper {
    fun getUsers(): Flow<List<UsersResponseItem>>
}
class ApiHelperImpl @Inject constructor (private val apiService: ApiService) : ApiHelper {

    override fun getUsers()= flow { emit(apiService.getUsers()) }
}

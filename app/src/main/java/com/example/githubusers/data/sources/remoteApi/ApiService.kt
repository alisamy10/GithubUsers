package com.example.githubusers.data.sources.remoteApi

import com.example.githubusers.common.END_POINT
import com.example.githubusers.data.model.UsersResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getUsers(): List<UsersResponseItem>
}
package com.example.githubusers.data.sources.remoteApi


import android.util.Log
import com.example.githubusers.data.model.UsersResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface OnlineDataSource {
   suspend fun getUsers(): List<UsersResponseItem> = emptyList()
}


class OnlineDataSourceImpl @Inject constructor (private val service: ApiHelper) : OnlineDataSource {

   companion object {
      var errorMsg: String = ""
   }
   @ExperimentalCoroutinesApi
   override suspend fun getUsers(): List<UsersResponseItem> {
       val usersList = mutableListOf<UsersResponseItem>()
      service.getUsers().flowOn(Dispatchers.IO).catch { e ->
         errorMsg = e.message.toString()
         Log.e("TAG", errorMsg)
      }.collect {
         usersList.addAll(it)
      }

      return usersList
   }


}

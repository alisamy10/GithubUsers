package com.example.githubusers.data.sources.homeCahedData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubusers.data.model.UsersResponseItem


@Dao
interface UsersDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(article: List<UsersResponseItem>): List<Long>


    @Query("SELECT * FROM  UsersResponseItem")
    suspend fun getAllUsers(): List<UsersResponseItem>

    @Query("UPDATE UsersResponseItem SET isFav = :isFv  WHERE login = :login")
    suspend fun updateFav(isFv: Boolean, login: String)
}
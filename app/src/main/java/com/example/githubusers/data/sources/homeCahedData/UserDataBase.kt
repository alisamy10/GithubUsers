package com.example.githubusers.data.sources.homeCahedData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubusers.data.model.UsersResponseItem

@Database(entities = [UsersResponseItem::class], version = 2 , exportSchema = false)

abstract class UserDataBase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
}
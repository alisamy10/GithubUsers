package com.example.githubusers.data.sources.homeCahedData

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.githubusers.data.model.UsersResponseItem
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UsersDataBaseTest{
    private lateinit var dataDao: UsersDao
    private lateinit var db: UserDataBase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, UserDataBase::class.java)
            .build()

        dataDao = db.getUsersDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {

        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetData() {
        val fakeList= mutableListOf<UsersResponseItem>().apply {
            add(
                UsersResponseItem(
                   id =1,
                    login = "ali"
                )
            )
        }
        runBlocking {
            dataDao.insertUsers(fakeList)
            val allData = dataDao.getAllUsers()
            assertThat(allData.size, Is.`is`(1))
        }


    }

}
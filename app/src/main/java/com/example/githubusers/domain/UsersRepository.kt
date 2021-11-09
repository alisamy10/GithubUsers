package  com.example.githubusers.domain

import com.example.githubusers.data.model.UsersResponseItem

interface UsersRepository {

    suspend fun getUsers(): List<UsersResponseItem>

}
package  com.example.githubusers.domain

import com.example.githubusers.data.model.UsersResponseItem
import javax.inject.Inject




class UsersUseCase @Inject constructor(private val repository: UsersRepository) {


     suspend fun getUsersUseCase(): List<UsersResponseItem> =repository.getUsers()


    suspend fun updateFavoriteUseCase(isFv:Boolean,login:String)=repository.updateFavorite(isFv,login)



}
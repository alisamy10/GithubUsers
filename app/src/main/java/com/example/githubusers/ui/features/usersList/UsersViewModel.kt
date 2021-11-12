package com.example.githubusers.ui.features.usersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.common.Resource
import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.domain.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel  @Inject constructor (private val userUseCase: UsersUseCase) : ViewModel() {

    private var usersLiveData = MutableLiveData<Resource<UsersResponseItem>>()
     var error = MutableLiveData<Boolean>()




     fun getUsers() {
         usersLiveData.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val result = userUseCase.getUsersUseCase()

            usersLiveData.postValue(Resource.Success(result))
            if(result.isNullOrEmpty()){
                error.postValue(true)
            }
        }
    }

     fun getUsersLiveData()  = usersLiveData  as LiveData<Resource<UsersResponseItem>>






}

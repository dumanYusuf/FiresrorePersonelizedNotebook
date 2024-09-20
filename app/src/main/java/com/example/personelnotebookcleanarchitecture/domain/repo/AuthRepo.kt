package com.example.personelnotebookcleanarchitecture.domain.repo

import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    suspend fun loginUser(email:String,password:String): Flow<Resource<User>>
    suspend fun registerUser(email: String,password: String,userName:String,userLastName:String):Flow<Resource<User>>
    fun logOut()

}
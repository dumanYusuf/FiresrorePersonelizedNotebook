package com.example.personelnotebookcleanarchitecture.data.repo

import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(private val auth:FirebaseAuth):AuthRepo {

    override suspend fun loginUser(email: String, password: String): Flow<Resource<User>> = flow{
       try {
           val result= auth.signInWithEmailAndPassword(email,password).await()
           val firebaseUser=result.user
           firebaseUser?.let {
               emit(Resource.Success(it.toUser()))
           }?: kotlin.run {
               emit(Resource.Error("user null"))
           }
       }
       catch (e:Exception){
           emit(Resource.Error(e.message ?: " unknown error "))
       }
    }

    override suspend fun registerUser(email: String, password: String): Flow<Resource<User>> = flow {
       try {
           val result=auth.createUserWithEmailAndPassword(email,password).await()
           val firebaseUser=result.user
           firebaseUser?.let {
               emit(Resource.Success(firebaseUser.toUser()))
           }?: kotlin.run {
               emit(Resource.Error("user null"))
           }
       }
       catch (e:Exception){
           emit(Resource.Error(e.message ?: " unknown error "))
       }
    }

    // burda FirebaseUserı user Model sınıfımızı çeviriyoruz extension fonksiyon yardımı ile
    private fun FirebaseUser.toUser():User{
        return User(
            id = uid,
            email = email,
            userName = "",
            userLastName = "",
            profilUrl = photoUrl.toString()
        )
    }

}
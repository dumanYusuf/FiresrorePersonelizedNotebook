package com.example.personelnotebookcleanarchitecture.data.repo

import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.domain.model.toMap
import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
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

    override suspend fun registerUser(email: String, password: String, userName: String, userLastName: String): Flow<Resource<User>> = flow {
        try {
            // Firebase Authentication ile kullanıcı kaydı
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user

            firebaseUser?.let { user ->
                val newUser = User(
                    id = user.uid,
                    email = email,
                    userName = userName,
                    userLastName = userLastName
                )

                // Firestore'a kullanıcı bilgilerini kaydetme
                val firestore = FirebaseFirestore.getInstance()
                firestore.collection("Users").document(newUser.id).set(newUser.toMap()).await()

                emit(Resource.Success(newUser))  // Başarılı sonuç dönüyoruz
            } ?: kotlin.run {
                emit(Resource.Error("User is null"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }



    // burda FirebaseUserı user Model sınıfımızı çeviriyoruz extension fonksiyon yardımı ile
    private fun FirebaseUser.toUser():User{
        return User(
            id = uid,
            email = email.toString(),
            userName = "",
            userLastName = "",
            profilUrl = photoUrl.toString()
        )
    }

}
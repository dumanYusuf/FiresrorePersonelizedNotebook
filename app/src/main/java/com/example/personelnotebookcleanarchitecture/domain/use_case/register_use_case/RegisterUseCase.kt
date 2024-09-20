package com.example.personelnotebookcleanarchitecture.domain.use_case.register_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repo: AuthRepo) {

    suspend fun registerUser(email:String,password:String): Flow<Resource<User>> {
        return repo.registerUser(email,password)
    }

}
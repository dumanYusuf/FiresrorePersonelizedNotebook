package com.example.personelnotebookcleanarchitecture.domain.use_case.login_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: AuthRepo) {

   suspend fun loginUser(email:String,password:String): Flow<Resource<User>>{
      return  repo.loginUser(email,password)
    }

}
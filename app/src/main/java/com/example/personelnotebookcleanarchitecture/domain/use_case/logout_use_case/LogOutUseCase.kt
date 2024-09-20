package com.example.personelnotebookcleanarchitecture.domain.use_case.logout_use_case

import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repo: AuthRepo) {

    fun logUutUser(){
        return repo.logOut()
    }
}
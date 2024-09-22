package com.example.personelnotebookcleanarchitecture.domain.use_case.get_user_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: NoteRepo) {

    suspend operator fun invoke():Resource<List<User>>{
        return repo.getUsers()
    }


    // fonksiyonu şu şekilde de ismi ile de yazabilirim aynıı şey aslında
    /*
    suspend fun getUsers():Resource<List<User>>{
        return repo.getUsers()
    }*/

}
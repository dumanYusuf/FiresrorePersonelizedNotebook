package com.example.personelnotebookcleanarchitecture.domain.use_case.get_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetNoteUseCase @Inject constructor(private val repo: NoteRepo){

    fun getNotes(): Flow<Resource<List<Notes>>>{
        return repo.getNotes()
    }
}
package com.example.personelnotebookcleanarchitecture.domain.use_case.add_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import javax.inject.Inject

class AddUseCase @Inject constructor(private val repo: NoteRepo) {

   suspend fun addNote(notes: Notes):Resource<Notes>{
         return repo.addNote(notes)
    }

}
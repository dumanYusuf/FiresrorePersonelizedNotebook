package com.example.personelnotebookcleanarchitecture.domain.use_case.update_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val repo: NoteRepo) {


    suspend operator fun invoke(notes: Notes):Resource<Notes>{
        return repo.updateNote(notes)
    }

}
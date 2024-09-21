package com.example.personelnotebookcleanarchitecture.domain.use_case.delete_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import javax.inject.Inject

class DeleteUseCase @Inject constructor(private val repo: NoteRepo) {

    suspend operator fun invoke(note: Notes):Resource<Notes>{
        return repo.deleteNote(note)
    }
}
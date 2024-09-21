package com.example.personelnotebookcleanarchitecture.domain.use_case.search_use_case

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repo: NoteRepo) {


     fun searchNote(searchNote:String): Flow<Resource<List<Notes>>>{
        return repo.searchNotes(searchNote)
    }

}
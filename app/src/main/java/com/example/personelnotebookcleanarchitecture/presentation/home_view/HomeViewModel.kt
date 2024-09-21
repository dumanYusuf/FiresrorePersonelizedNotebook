package com.example.personelnotebookcleanarchitecture.presentation.home_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.use_case.add_use_case.AddUseCase
import com.example.personelnotebookcleanarchitecture.domain.use_case.delete_use_case.DeleteUseCase
import com.example.personelnotebookcleanarchitecture.domain.use_case.get_use_case.GetNoteUseCase
import com.example.personelnotebookcleanarchitecture.domain.use_case.search_use_case.SearchUseCase
import com.example.personelnotebookcleanarchitecture.domain.use_case.update_use_case.UpdateUseCase
import com.example.personelnotebookcleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addUseCase: AddUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _state= MutableStateFlow<HomeState>(HomeState())
    val state:StateFlow<HomeState> =_state



    fun addNote(notes: Notes)=viewModelScope.launch {
        addUseCase.addNote(notes)
        Log.e("viewModel add ","Success Note")
    }

    fun getNotes(){
        viewModelScope.launch {
            _state.value=HomeState(isLoading = true)
            getNoteUseCase.getNotes().collect{result->
                when(result){
                    is Resource.Success->{
                        _state.value= HomeState(noteList = result.data?: emptyList())
                        Log.e("get notes","viewmodel success get notes")
                    }
                    is Resource.Error->{
                        _state.value=HomeState(isError = result.message?:"Error")
                        Log.e("get notes","viewmodel failure get notes:${result.message}")
                    }
                    is Resource.Loading->{
                        _state.value=HomeState(isLoading =true)
                        Log.e("get notes","viewmodel isloading get notes")
                    }
                }
            }
        }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch {
            deleteUseCase.invoke(notes)
            Log.e("viewModel delete note ","delete Note")
        }
    }

    fun updateNote(notes: Notes){
        viewModelScope.launch {
            updateUseCase.invoke(notes)
            Log.e("viewModel update note ","update Note")
        }
    }


    fun searchNotes(searchNote:String){
        viewModelScope.launch {
            _state.value=HomeState(isLoading = true)
            searchUseCase.searchNote(searchNote).collect{result->
                when(result){
                    is Resource.Success->{
                        _state.value= HomeState(noteList = result.data?: emptyList())
                        Log.e("get notes","viewmodel success search notes")
                    }
                    is Resource.Error->{
                        _state.value=HomeState(isError = result.message?:"Error")
                        Log.e("get notes","viewmodel failure search notes:${result.message}")
                    }
                    is Resource.Loading->{
                        _state.value=HomeState(isLoading =true)
                        Log.e("get notes","viewmodel isloading search notes")
                    }
                }
            }
        }
    }

}
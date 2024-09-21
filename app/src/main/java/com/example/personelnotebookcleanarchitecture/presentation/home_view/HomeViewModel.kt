package com.example.personelnotebookcleanarchitecture.presentation.home_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.use_case.add_use_case.AddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addUseCase: AddUseCase
): ViewModel() {

    private val _state= MutableStateFlow<HomeState>(HomeState())
    val state:StateFlow<HomeState> =_state


    fun addNote(notes: Notes)=viewModelScope.launch {
        addUseCase.addNote(notes)
        Log.e("viewModel add ","Success Note")
    }


}
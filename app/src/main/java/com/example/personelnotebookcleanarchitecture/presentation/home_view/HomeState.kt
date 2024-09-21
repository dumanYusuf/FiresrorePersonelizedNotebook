package com.example.personelnotebookcleanarchitecture.presentation.home_view

import com.example.personelnotebookcleanarchitecture.domain.model.Notes

data class HomeState(
   // val isSucsess:Boolean=false,
     val noteList:List<Notes> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)
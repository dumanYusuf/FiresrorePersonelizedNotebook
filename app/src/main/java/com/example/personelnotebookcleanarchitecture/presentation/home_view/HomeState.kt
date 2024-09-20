package com.example.personelnotebookcleanarchitecture.presentation.home_view

data class HomeState(
    val isSucsess:Boolean=false,
    // val noteList=List<Note> =emptlist()
    val isError:String="",
    val isLoading:Boolean=false
)
package com.example.personelnotebookcleanarchitecture.presentation.register_view

data class RegisterState(
    val isSucsess:Boolean=false,
    val isError:String="",
    val isLoading:Boolean=false
)

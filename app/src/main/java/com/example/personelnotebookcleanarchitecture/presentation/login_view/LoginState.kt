package com.example.personelnotebookcleanarchitecture.presentation.login_view

data class LoginState(
    val isSucsess:Boolean=false,
    val isError:String="",
    val isLoading:Boolean=false
)
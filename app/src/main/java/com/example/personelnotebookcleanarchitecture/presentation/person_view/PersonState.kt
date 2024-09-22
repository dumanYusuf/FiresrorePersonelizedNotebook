package com.example.personelnotebookcleanarchitecture.presentation.person_view

import com.example.personelnotebookcleanarchitecture.domain.model.User


data class PersonState(
    val isSucsess: List<User> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)

package com.example.personelnotebookcleanarchitecture.domain.model

data class User(
    val id:String="",
    val email:String="",
    val userName:String="",
    val userLastName:String="",
    val profilUrl:String=""
)


fun User.toMap():Map<String,Any>{
    return mapOf(
        "id" to id ,
        "email" to email,
        "userName" to userName,
        "userLastName" to userLastName,
        "profilUrl" to profilUrl,
    )
}

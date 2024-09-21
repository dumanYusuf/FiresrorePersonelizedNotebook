package com.example.personelnotebookcleanarchitecture.domain.model

data class Notes(
    val noteId:String="",
    val title:String="",
    val content:String=""
)


fun Notes.toMap():Map<String,Any>{
    return mapOf(
        "noteId" to noteId,
        "title" to title,
        "content" to content
    )
}

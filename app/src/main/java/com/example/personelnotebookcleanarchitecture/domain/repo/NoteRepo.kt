package com.example.personelnotebookcleanarchitecture.domain.repo

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.util.Resource


interface NoteRepo {


    // sürekli bir akış varsa veri yükleme durumlarında flow kullanılır ama tek seferlik bir durumu söz konusu ise o zamandda suspend

    suspend fun addNote(notes: Notes): Resource<Notes>// burda tek seferlik bir işelm yapacagımdan Flow kulluanmadım



}
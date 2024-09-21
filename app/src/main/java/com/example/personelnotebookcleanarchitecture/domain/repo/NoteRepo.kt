package com.example.personelnotebookcleanarchitecture.domain.repo

import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow


interface NoteRepo {


    // sürekli bir akış varsa veri yükleme durumlarında flow kullanılır ama tek seferlik bir durumu söz konusu ise o zamandda suspend

     suspend fun addNote(notes: Notes): Resource<Notes>// burda tek seferlik bir işelm yapacagımdan Flow kulluanmadım
     fun getNotes(): Flow<Resource<List<Notes>>>// burda flow kullandık suspend yazmamamın sebebi flow zaten coruitene çalışır o yüzden gerek yok
     suspend fun deleteNote(note:Notes):Resource<Notes>

}
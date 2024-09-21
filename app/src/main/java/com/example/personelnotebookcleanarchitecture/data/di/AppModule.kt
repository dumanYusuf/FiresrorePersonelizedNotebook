package com.example.personelnotebookcleanarchitecture.data.di

import NoteRepoImpl
import com.example.personelnotebookcleanarchitecture.data.repo.AuthRepoImpl
import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // auth
    @Provides
    @Singleton
    fun providesFirebseAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepo(auth:FirebaseAuth):AuthRepo{
        return AuthRepoImpl(auth)
    }

    // firestore
    @Provides
    @Singleton
    fun providesFirestore():FirebaseFirestore=FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesNoteRepo(firestore:FirebaseFirestore,auth: FirebaseAuth):NoteRepo{
        return NoteRepoImpl(firestore,auth)
    }



}
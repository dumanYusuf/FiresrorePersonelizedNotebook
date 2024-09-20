package com.example.personelnotebookcleanarchitecture.presentation.home_view

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.personelnotebookcleanarchitecture.domain.use_case.logout_use_case.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val logOutUseCase: LogOutUseCase): ViewModel() {


    fun logOut(){
        logOutUseCase.logUutUser()
        Log.e("success","viewModel succseess logout")
    }


}
package com.example.personelnotebookcleanarchitecture.presentation.person_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personelnotebookcleanarchitecture.domain.use_case.get_user_use_case.GetUserUseCase
import com.example.personelnotebookcleanarchitecture.domain.use_case.logout_use_case.LogOutUseCase
import com.example.personelnotebookcleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logOutUseCase: LogOutUseCase
):ViewModel(){


    private val _state= MutableStateFlow<PersonState>(PersonState())
    val state:StateFlow<PersonState> =_state


    fun getUser()=viewModelScope.launch {
        _state.value=PersonState(isLoading = true)
       val result=getUserUseCase.invoke()
        when(result){
            is Resource.Success->{
                _state.value=PersonState(isSucsess = result.data?: emptyList())
            }
            is Resource.Error->{
                _state.value=PersonState(isError = "Eror:${result.message}")
            }
            is Resource.Loading->{
                _state.value=PersonState(isLoading = true)
            }
        }
        Log.e("success","viewModel succseess getUser")
    }


    fun logOut(){
        logOutUseCase.logUutUser()
        Log.e("success","viewModel succseess logout")
    }


}
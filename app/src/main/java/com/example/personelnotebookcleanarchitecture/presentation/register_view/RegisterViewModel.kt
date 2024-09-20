package com.example.personelnotebookcleanarchitecture.presentation.register_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personelnotebookcleanarchitecture.domain.use_case.register_use_case.RegisterUseCase
import com.example.personelnotebookcleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase):ViewModel(){


    private val _authState= MutableStateFlow<RegisterState>(RegisterState())
    val authStat: StateFlow<RegisterState> = _authState


    fun register(email:String,password:String,userName:String,userLastName:String){
        viewModelScope.launch {
            _authState.value= RegisterState(isLoading = true)
            registerUseCase.registerUser(email,password,userName,userLastName).collect{
                when(it){
                    is Resource.Success->{
                        _authState.value= RegisterState(isSucsess = true)
                        Log.e("succsees user","viewModel sussceess user")
                    }
                    is Resource.Error->{
                        _authState.value= RegisterState(isError = "Error meesssaage")
                        Log.e("error","viewModel error user:${_authState.value}")
                    }
                    is Resource.Loading->{
                        _authState.value= RegisterState(isLoading = true)
                        Log.e("loading","viewModel loading")
                    }
                }
            }
        }
    }


}
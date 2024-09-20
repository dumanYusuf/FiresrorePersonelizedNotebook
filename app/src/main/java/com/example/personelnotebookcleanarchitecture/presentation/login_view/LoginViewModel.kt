package com.example.personelnotebookcleanarchitecture.presentation.login_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personelnotebookcleanarchitecture.domain.use_case.login_use_case.LoginUseCase
import com.example.personelnotebookcleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase):ViewModel(){

    private val _authState= MutableStateFlow<LoginState>(LoginState())
    val authStat:StateFlow<LoginState> = _authState

    fun login(email:String,password:String){
        viewModelScope.launch {
            _authState.value=LoginState(isLoading = true)
            loginUseCase.loginUser(email,password).collect{
                when(it){
                    is Resource.Success->{
                        _authState.value=LoginState(isSucsess = true)
                        Log.e("succsees user","viewModel sussceess user")
                    }
                    is Resource.Error->{
                        _authState.value=LoginState(isError = "Error meesssaage")
                        Log.e("error","viewModel error user")
                    }
                    is Resource.Loading->{
                        _authState.value=LoginState(isLoading = true)
                        Log.e("loading","viewModel loading")
                    }
                }
            }
            }
        }
    }
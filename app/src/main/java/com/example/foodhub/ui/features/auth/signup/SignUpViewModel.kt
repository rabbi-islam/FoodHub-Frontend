package com.example.foodhub.ui.features.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhub.data.FoodApi
import com.example.foodhub.data.model.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val foodApi: FoodApi) : ViewModel() {

    private val _uiState = MutableStateFlow<SignUpEvent>(SignUpEvent.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignUpNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _uiState.value = SignUpEvent.Loading
            try {
                val response = foodApi.signUp(
                    SignUpRequest(
                        name.value,
                        email.value,
                        password.value,
                    )
                )
                if (response.token.isNotEmpty()) {
                    _uiState.value = SignUpEvent.Success
                    _navigationEvent.emit(SignUpNavigationEvent.NavigateToHome)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = SignUpEvent.Error
            }
        }
    }


    sealed class SignUpNavigationEvent {
        object NavigateToLogin : SignUpNavigationEvent()
        object NavigateToHome : SignUpNavigationEvent()
    }


    sealed class SignUpEvent {
        object Success : SignUpEvent()
        object Error : SignUpEvent()
        object Loading : SignUpEvent()
        object Nothing : SignUpEvent()
    }

}
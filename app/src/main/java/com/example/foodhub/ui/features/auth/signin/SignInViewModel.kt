package com.example.foodhub.ui.features.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhub.data.FoodApi
import com.example.foodhub.data.model.SignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val foodApi: FoodApi) : ViewModel() {

    private val _uiState = MutableStateFlow<SignInEvent>(SignInEvent.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignInNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _uiState.value = SignInEvent.Loading
            try {
                val response = foodApi.signIn(
                    SignInRequest(
                        email.value,
                        password.value,
                    )
                )
                if (response.token.isNotEmpty()) {
                    _uiState.value = SignInEvent.Success
                    _navigationEvent.emit(SignInNavigationEvent.NavigateToHome)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = SignInEvent.Error
            }
        }
    }

    fun onSignUpClicked(){
        viewModelScope.launch{
            _navigationEvent.emit(SignInNavigationEvent.NavigateToSignUp)
        }
    }

    sealed class SignInNavigationEvent {
        object NavigateToSignUp : SignInNavigationEvent()
        object NavigateToHome : SignInNavigationEvent()
    }


    sealed class SignInEvent {
        object Success : SignInEvent()
        object Error : SignInEvent()
        object Loading : SignInEvent()
        object Nothing : SignInEvent()
    }

}
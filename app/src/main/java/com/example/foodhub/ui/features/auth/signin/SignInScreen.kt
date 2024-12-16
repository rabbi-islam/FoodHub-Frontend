package com.example.foodhub.ui.features.auth.signin

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.foodhub.R
import com.example.foodhub.ui.FoodHubTextField
import com.example.foodhub.ui.GroupSocialButtons
import com.example.foodhub.ui.navigation.AuthScreen
import com.example.foodhub.ui.navigation.Home
import com.example.foodhub.ui.navigation.SignUp
import com.example.foodhub.ui.theme.orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {

    Box(modifier = Modifier.fillMaxSize()) {
        val email = viewModel.email.collectAsStateWithLifecycle()
        val password = viewModel.password.collectAsStateWithLifecycle()
        val errorMessage = remember { mutableStateOf<String?>(null) }
        val loading = remember { mutableStateOf(false) }

        val uiState = viewModel.uiState.collectAsState()
        when (uiState.value) {
            SignInViewModel.SignInEvent.Error -> {
                loading.value = false
                errorMessage.value = "Failed"
            }

            SignInViewModel.SignInEvent.Loading -> {
                loading.value = true
                errorMessage.value = null
            }

            else -> {
                loading.value = false
                errorMessage.value = null
            }
        }
        LaunchedEffect(true) {
            viewModel.navigationEvent.collectLatest {event->
                when(event) {
                    SignInViewModel.SignInNavigationEvent.NavigateToHome -> {
                        navController.navigate(Home){
                            popUpTo(AuthScreen){
                                inclusive = true
                            }
                        }
                    }

                    SignInViewModel.SignInNavigationEvent.NavigateToSignUp ->{
                        navController.navigate(SignUp)
                    }
                }
            }
        }




        Image(
            painter = painterResource(R.drawable.ic_auth_bg),
            contentDescription = "auth_background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.weight(1f))


            Text(
                text = stringResource(R.string.sign_in),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(Modifier.size(20.dp))
            FoodHubTextField(
                value = email.value,
                onValueChange = { viewModel.onEmailChange(it) },
                label = {
                    Text(
                        text = stringResource(R.string.email),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )


            FoodHubTextField(
                value = password.value,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = {
                    Text(
                        text = stringResource(R.string.password),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_eye),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }

            )
            Spacer(Modifier.size(16.dp))
            Text(text = errorMessage.value?: "", color = Color.Red)
            Button(
                onClick = viewModel::onSignInClick,
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                Box {

                    AnimatedContent(
                        targetState = loading.value,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f) togetherWith
                                    fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
                        }
                    ) { target ->
                        if (target) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 32.dp)
                                    .size(24.dp)

                            )
                        } else {
                            Text(
                                text = stringResource(R.string.sign_in),
                                modifier = Modifier.padding(horizontal = 32.dp)
                            )
                        }
                    }

                }

            }
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.dont_have_an_account_signup),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        viewModel.onSignUpClicked()
                    }
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(12.dp))
            GroupSocialButtons(Color.Black, {}, {})
        }
    }

}


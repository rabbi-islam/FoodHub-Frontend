package com.example.foodhub.ui.features.auth.signup

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodhub.R
import com.example.foodhub.ui.FoodHubTextField
import com.example.foodhub.ui.GroupSocialButtons
import com.example.foodhub.ui.theme.orange

@Composable
fun SignUpScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        var name by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
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
                text = stringResource(R.string.sign_up),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth().padding(16.dp)
            )
            Spacer(Modifier.size(20.dp))
            FoodHubTextField(
                value = name,
                onValueChange = { name = it},
                label = {
                    Text(
                        text = stringResource(R.string.full_name),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            FoodHubTextField(
                value = email,
                onValueChange = {email = it},
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
                value = password,
                onValueChange = {password = it},
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
            Button(
                onClick = {},
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.already_have_an_account_signin),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {}
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.padding(12.dp))
            GroupSocialButtons(Color.Black, {}, {})
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PrevSignUp() {
    SignUpScreen()
}
package com.example.foodhub.ui.features.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodhub.R
import com.example.foodhub.ui.theme.skipButtonTextColor

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {
    val imageSize = remember { mutableStateOf(IntSize.Zero) }
    val brush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = imageSize.value.height.toFloat()/3,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ){
        Image(
            modifier = Modifier
                .onGloballyPositioned {
                    imageSize.value = it.size
                }
                .alpha(alpha = 0.7f),
            painter = painterResource(R.drawable.background),
            contentDescription = null
        )
        Box(modifier = Modifier
            .matchParentSize()
            .background(brush = brush))
        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = skipButtonTextColor
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 110.dp)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,

            )
            Text(
                text = stringResource(R.string.food_hub),
                color = skipButtonTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp

            )
            Text(
                text = stringResource(R.string.food_desc),
                color = Color.DarkGray,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)

            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )

                Text(
                    text = "sign in with",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )

                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(32.dp)

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.ic_facebook),
                            contentDescription = "facebook_icon"
                        )
                        Spacer(Modifier.size(6  .dp))
                        Text(text = stringResource(R.string.facebook), color = Color.Black)
                    }
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(32.dp)

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.ic_google),
                            contentDescription = "google"
                        )
                        Spacer(Modifier.size(8.dp))
                        Text(text = stringResource(R.string.google), color = Color.Black)
                    }
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, Color.White)

            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = stringResource(R.string.start_with_email_or_phone),
                    color = Color.White
                )
            }

            TextButton(onClick = {}) {
                Text(text = stringResource(R.string.already_have_an_account_signin), color = Color.White)
            }
        }
    }

}

















@Preview(showBackground = true)
@Composable
private fun AuthScreenPrev() {
    AuthScreen()
}
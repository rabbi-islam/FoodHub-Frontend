package com.example.foodhub.data

import com.example.foodhub.data.model.AuthResponse
import com.example.foodhub.data.model.SignInRequest
import com.example.foodhub.data.model.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface FoodApi {

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): AuthResponse

}
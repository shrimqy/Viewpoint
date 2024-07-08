package com.dokja.viewpoint.dto

data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val uuid: String,
    val username: String,
    val accessToken: String
)

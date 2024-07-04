package com.dokja.viewpoint.service

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.model.User

interface UserService {
    fun signUp(user: User): User?
    fun login(authRequest: AuthRequest): User?
    fun findBbyUsername(username: String): User?
}
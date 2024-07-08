package com.dokja.viewpoint.service

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.dto.UserBookRequest
import com.dokja.viewpoint.model.User
import com.dokja.viewpoint.model.UserBook

interface UserService {
    fun signUp(user: User): User?
    fun login(authRequest: AuthRequest): User?
    fun findBbyUsername(username: String): User?
    fun createUserBook(userBook: UserBook): UserBook
    fun updateUserBook(userBook: UserBook): UserBook?
}
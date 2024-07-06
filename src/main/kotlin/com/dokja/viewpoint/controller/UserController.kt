package com.dokja.viewpoint.controller

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.dto.AuthResponse
import com.dokja.viewpoint.model.BookCategory
import com.dokja.viewpoint.model.Role
import com.dokja.viewpoint.model.User
import com.dokja.viewpoint.service.AuthService
import com.dokja.viewpoint.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/user/")
class UserController(
    private val userService: UserService,
    private val authService: AuthService,
    val passwordEncoder: BCryptPasswordEncoder
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody authRequest: AuthRequest): AuthResponse =
        userService.signUp(authRequest.toModel())
            ?.toResponse(authService, authRequest)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot Sign Up")

    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest: AuthRequest) : AuthResponse =
        userService.login(authRequest)
            ?.toResponse(authService, authRequest)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot Login")    
    
    
    private fun User.toResponse(authService: AuthService, authRequest: AuthRequest): AuthResponse =
        authService.authentication(authRequest, user = this)

    private fun AuthRequest.toModel(): User =
        User(
            id = UUID.randomUUID().toString(),
            username = this.username,
            passwordHash = passwordEncoder.encode(this.password),
            createdAt = Date(System.currentTimeMillis()),
            updatedAt = Date(System.currentTimeMillis()),
            bio = null,
            avatar = null,
            userBooks = null,
            bookCategories = predefinedCategories,
            role = Role.USER
        )
}


private val predefinedCategories = listOf(
    BookCategory(id = 1, name = "All"),
    BookCategory(id = 2, name = "Reading"),
    BookCategory(id = 3, name = "Planning"),
    BookCategory(id = 4, name = "Paused"),
    BookCategory(id = 5, name = "Dropped"),
    BookCategory(id = 6, name = "Completed")
)
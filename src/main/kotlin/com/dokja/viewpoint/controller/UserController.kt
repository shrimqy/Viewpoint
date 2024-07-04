package com.dokja.viewpoint.controller

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.dto.AuthResponse
import com.dokja.viewpoint.model.Role
import com.dokja.viewpoint.model.User
import com.dokja.viewpoint.service.AuthService
import com.dokja.viewpoint.service.UserService
import org.springframework.http.HttpStatus
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
    private val authService: AuthService
) {
    @PostMapping("/signUp")
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
        authService.authentication(authRequest, userDetails = this)

    private fun AuthRequest.toModel(): User =
        User(
            id = UUID.randomUUID().toString(),
            username = this.username,
            passwordHash = this.password,
            createdAt = Date(System.currentTimeMillis()),
            updatedAt = Date(System.currentTimeMillis()),
            bio = null,
            avatar = null,
            userBooks = null,
            role = Role.USER
        )
}

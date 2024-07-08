package com.dokja.viewpoint.controller

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.dto.UserBookRequest
import com.dokja.viewpoint.service.AuthService
import com.dokja.viewpoint.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/userbook/")
class UserBookController (
    private val userService: UserService,
    private val authService: AuthService
) {
    @PostMapping()
    fun createUserBook(@RequestBody userBookRequest: UserBookRequest): ResponseEntity<Any> {
        return try {
            val userBook = userService.createUserBook(userBookRequest)
            ResponseEntity.status(HttpStatus.CREATED).body(userBook.id)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "Internal server error"))
        }
    }
}
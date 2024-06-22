package com.dokja.viewpoint.controller

import com.dokja.viewpoint.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/books")
class BookController(private val bookService: BookService) {
    @GetMapping
    fun searchBooks(
        @RequestParam title: String?,
        @RequestParam id: String?,
        @RequestParam authors: String?,
    ): ResponseEntity<Any> {
        return try {
            val books = bookService.searchBooks(title)
            ResponseEntity.ok(mapOf("books" to books))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "Internal server error"))
        }
    }
}
package com.dokja.viewpoint.model

data class Book(
    val id: String,
    val description: String,
) {
}

data class BookCategory(
    val id: Int,
    val name: String,
    val userBook: UserBook,
    val user: User
)
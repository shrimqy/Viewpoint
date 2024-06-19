package com.dokja.viewpoint.model

import java.util.Date

data class User(
    val id: String,
    val username: String,
    val passwordHash: String,
    val userAuthToken: String,
    val createdAt: Date,
    val updatedAt: Date,
    val avatar: String?,
    val bio: String?,

    val userBook: UserBook
) {
}

data class UserBook(
    val id: String,
    val user: User,
    val book: Book,
    val bookCategory: BookCategory,
    val pagesRead: String?,
    val chaptersRead: String?,
    val rating: String?,
    val rereads: String?,
    val notes: String?,
    val startedDate: Date,
    val completedDate: Date
)
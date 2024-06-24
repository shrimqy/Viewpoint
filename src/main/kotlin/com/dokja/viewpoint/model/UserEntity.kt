package com.dokja.viewpoint.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

//@Entity
//@Table(name = "User")
data class User(
//    @Id
    val id: String,
    val username: String,
    val passwordHash: String,
    val userAuthToken: String,
    val createdAt: Date,
    val updatedAt: Date,
    val avatar: String?,
    val bio: String?,
    val userBook: UserBook
)

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
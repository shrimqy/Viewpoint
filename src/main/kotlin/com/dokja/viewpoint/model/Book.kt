package com.dokja.viewpoint.model

import jakarta.persistence.*

data class Book(
    @Id val id: String,
    val title: String,
    val publisher: String?,
    val authors: String,
    val publicRating: Float?,
    val description: String?,
    val coverUrl: String?,
    val chapters: Int?,
    val genres: String?,
    val ratingCount: Int?,
    val firstPublished: String?
)

data class BookCategory(
    @Id val id: Int,
    val name: String,
    val userBook: UserBook,
    val user: UserEntity
)
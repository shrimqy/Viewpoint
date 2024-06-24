package com.dokja.viewpoint.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Book")
data class Book(
    @Id
    @Column(name = "id")
    val id: String,
    @Column(name = "title")
    val title: String?,
    @Column(name = "authors")
    val authors: String?,
    @Column(name = "publisher")
    val publisher: String?,
    @Column(name = "publicRating")
    val publicRating: Float?,
    @Column(name = "description")
    val description: String?,
    @Column(name = "coverUrl")
    val coverUrl: String?,
    @Column(name = "chapters")
    val chapters: String?,
    @Column(name = "ratingCount")
    val ratingCount: Int?,
    @Column(name = "firstPublished")
    val firstPublished: LocalDateTime?
)

data class BookCategory(
    @Id val id: Int,
    val name: String,
    val userBook: UserBook,
    val user: UserEntity
)
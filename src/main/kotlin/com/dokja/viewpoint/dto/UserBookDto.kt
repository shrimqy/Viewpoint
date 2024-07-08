package com.dokja.viewpoint.dto

import com.dokja.viewpoint.model.BookCategory
import java.util.*

data class UserBookRequest (
    val userId: String,
    val bookId: String,
    val bookCategory: BookCategory,
    val pagesRead: String?,
    val chaptersRead: String?,
    val rating: String?,
    val rereads: String?,
    val notes: String?,
    val startedDate: Date?,
    val completedDate: Date?
)

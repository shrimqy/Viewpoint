package com.dokja.viewpoint.service

import com.dokja.viewpoint.model.Book

interface BookService {
    fun searchBooks(title: String?): List<Book>

    fun getAllBooks(): List<Book>
}
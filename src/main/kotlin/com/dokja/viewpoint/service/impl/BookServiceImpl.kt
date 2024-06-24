package com.dokja.viewpoint.service.impl

import com.dokja.viewpoint.model.Book
import com.dokja.viewpoint.repository.BookRepository
import com.dokja.viewpoint.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(@Autowired private val bookRepository: BookRepository) : BookService {

    override fun getAllBooks(): List<Book> {
        return bookRepository.findAll()
    }

    override fun searchBooks(title: String?): List<Book> {
        return when {
            title != null -> bookRepository.findByTitleContainingIgnoreCase(title)
            else -> bookRepository.findAll() // Return all if no filter is provided
        }
    }
}
package com.dokja.viewpoint.repository

import com.dokja.viewpoint.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, String> {
    fun findByTitleContainingIgnoreCase(title: String): List<Book>
}
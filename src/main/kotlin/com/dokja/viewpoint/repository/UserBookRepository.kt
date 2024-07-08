package com.dokja.viewpoint.repository

import com.dokja.viewpoint.model.UserBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBookRepository : JpaRepository<UserBook, String> {
}
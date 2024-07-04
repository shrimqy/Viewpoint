package com.dokja.viewpoint.repository

import com.dokja.viewpoint.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
    fun findByUsername(username: String): User?
}
package com.dokja.viewpoint.service.impl

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.dto.UserBookRequest
import com.dokja.viewpoint.model.User
import com.dokja.viewpoint.model.UserBook
import com.dokja.viewpoint.repository.UserBookRepository
import com.dokja.viewpoint.repository.UserRepository
import com.dokja.viewpoint.service.UserService
import com.dokja.viewpoint.utils.UsernameAlreadyExistsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userBookRepository: UserBookRepository
) : UserService {

    override fun signUp(user: User): User? {
        if (userRepository.findByUsername(user.username) != null) {
            throw UsernameAlreadyExistsException("Username already exists")
        } else {
            userRepository.save(user)
            return user
        }
    }

    override fun login(authRequest: AuthRequest): User? {
        return userRepository.findByUsername(authRequest.username) ?: throw UsernameNotFoundException("Check your username")
    }

    override fun findBbyUsername(username: String): User {
        return userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }

    override fun createUserBook(userBook: UserBook): UserBook {
        userBookRepository.save(userBook)
        return userBook
    }

    override fun updateUserBook(userBook: UserBook): UserBook? {
        val repoUserBook = userBookRepository.findById(userBook.id)
            ?: return null

        return userBookRepository.save(userBook)
    }


}

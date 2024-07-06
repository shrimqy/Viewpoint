package com.dokja.viewpoint.service.impl

import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.utils.UsernameAlreadyExistsException
import com.dokja.viewpoint.model.User
import com.dokja.viewpoint.repository.UserRepository
import com.dokja.viewpoint.service.UserService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
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


}

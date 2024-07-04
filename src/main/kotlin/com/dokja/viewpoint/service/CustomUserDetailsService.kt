package com.dokja.viewpoint.service

import com.dokja.viewpoint.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


typealias ApplicationUser = com.dokja.viewpoint.model.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not Found")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.username)
            .password(this.passwordHash)
            .roles(this.role.name)
            .build()
}
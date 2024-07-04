package com.dokja.viewpoint.service

import com.dokja.viewpoint.config.JwtProperties
import com.dokja.viewpoint.dto.AuthRequest
import com.dokja.viewpoint.dto.AuthResponse
import com.dokja.viewpoint.model.User
import com.dokja.viewpoint.service.impl.UserServiceImpl
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
) {
    fun authentication(authRequest: AuthRequest, user: User): AuthResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.username,
                authRequest.password
            )
        )
        val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
        val accessToken = tokenService.generate(
            userDetails = userDetails,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthResponse(
            uuid = user.id,
            username = userDetails.username,
            accessToken = accessToken
        )
    }
}

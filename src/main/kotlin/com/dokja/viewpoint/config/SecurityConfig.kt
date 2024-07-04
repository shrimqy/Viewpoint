package com.dokja.viewpoint.config

import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity
class SecurityConfig(private val authenticationProvider: AuthenticationProvider) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthFilter: JwtAuthFilter
    ): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth", "api/auth/refresh", "/error").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                    .requestMatchers("/api/user**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

//    fun configure(http: HttpSecurity) {
//        http
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()
//            .authorizeRequests()
//            .requestMatchers("/api/user/login", "/api/user/signup").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginProcessingUrl("/api/user/login")
//            .successHandler(AuthenticationSuccessHandler { request, response, authentication ->
//                response.contentType = "application/json"
//                response.status = HttpServletResponse.SC_OK
//                response.outputStream.println("{\"success\":true}")
//            })
//            .failureHandler(AuthenticationFailureHandler { request, response, exception ->
//                response.contentType = "application/json"
//                response.status = HttpServletResponse.SC_UNAUTHORIZED
//                response.outputStream.println("{\"success\":false,\"error\":\"${exception.message}\"}")
//            })
//            .and()
//            .logout()
//            .logoutUrl("/api/user/logout")
//            .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//    }
}
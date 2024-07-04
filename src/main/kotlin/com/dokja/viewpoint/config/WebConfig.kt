package com.dokja.viewpoint.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//@Configuration
//class WebConfig : WebMvcConfigurer {
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOrigins("http://localhost:5173") // Your SvelteKit app's URL
//            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//            .allowCredentials(true)
//    }
//}
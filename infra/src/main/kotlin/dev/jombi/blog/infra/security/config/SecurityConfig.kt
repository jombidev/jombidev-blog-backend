package dev.jombi.blog.infra.security.config

import dev.jombi.blog.infra.exception.ExceptionHandleFilter
import dev.jombi.blog.infra.security.jwt.JwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authFilter: JwtAuthFilter,
    private val authExceptionFilter: ExceptionHandleFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .sessionManagement { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").anonymous() // .permitAll()
                    .requestMatchers(HttpMethod.GET, "/articles", "/articles/{id}", "/image/{id}", "/image/{id}/file").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterAt(authFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authExceptionFilter, JwtAuthFilter::class.java)
            .build()
    }

    @Bean
    fun corsConfigurationSource() = UrlBasedCorsConfigurationSource()
        .apply {
            registerCorsConfiguration("/**",
                CorsConfiguration()
                    .apply { // kotlin style builder
                        addAllowedOrigin("https://jombi.dev")
                        addAllowedHeader("*")
                        addAllowedMethod("*")
                        allowCredentials = true
                    }
            )
        }
}

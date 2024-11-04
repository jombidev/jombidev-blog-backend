package dev.jombi.blog.core.auth.service

import dev.jombi.blog.core.auth.extern.TokenGenerator
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val tokenGenerator: TokenGenerator,
) : dev.jombi.blog.business.auth.service.AuthService {
    override fun authenticate(credential: String, password: String): dev.jombi.blog.business.auth.dto.TokenDto {
        val token = UsernamePasswordAuthenticationToken(credential, password)

        val auth = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext().authentication = auth

        val access = tokenGenerator.generateAccessToken()
        val refresh = tokenGenerator.generateRefreshToken()

        return dev.jombi.blog.business.auth.dto.TokenDto(access, refresh)
    }

    override fun getNewToken(refreshToken: String): dev.jombi.blog.business.auth.dto.TokenDto {
        val newAccessToken = tokenGenerator.refreshToNewToken(refreshToken)
        return dev.jombi.blog.business.auth.dto.TokenDto(
            newAccessToken,
            refreshToken // no changes ;)
        )
    }
}

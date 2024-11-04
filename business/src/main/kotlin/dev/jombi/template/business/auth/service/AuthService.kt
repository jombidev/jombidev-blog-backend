package dev.jombi.template.business.auth.service

import dev.jombi.template.business.auth.dto.TokenDto

interface AuthService {
    fun authenticate(credential: String, password: String): TokenDto
    fun getNewToken(refreshToken: String): TokenDto
}

package dev.jombi.blog.business.auth.service

interface AuthService {
    fun authenticate(credential: String, password: String): dev.jombi.blog.business.auth.dto.TokenDto
    fun getNewToken(refreshToken: String): dev.jombi.blog.business.auth.dto.TokenDto
}

package dev.jombi.blog.business.auth.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)

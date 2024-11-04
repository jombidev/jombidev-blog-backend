package dev.jombi.blog.api.auth.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class NewTokenRequestDto(
    val refreshToken: String
)

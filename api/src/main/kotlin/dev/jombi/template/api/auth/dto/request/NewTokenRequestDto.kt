package dev.jombi.template.api.auth.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class NewTokenRequestDto(
    val refreshToken: String
)

package dev.jombi.template.api.auth.dto.request

import jakarta.validation.constraints.NotBlank
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateRequestDto(
    @field:NotBlank
    val credential: String,
    @field:NotBlank
    val password: String
)

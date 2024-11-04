package dev.jombi.blog.business.image.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ImageDto(
    @Contextual val id: UUID,
    val name: String,
    val url: String,
    val createdAt: LocalDateTime,
)

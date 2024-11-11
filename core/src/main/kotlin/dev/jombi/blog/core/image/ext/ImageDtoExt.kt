package dev.jombi.blog.core.image.ext

import dev.jombi.blog.business.image.dto.ImageDto
import dev.jombi.blog.core.image.entity.Image
import kotlinx.datetime.toKotlinLocalDateTime

fun ImageDto.Companion.of(image: Image) =
    ImageDto(
        image.id.get,
        image.name,
        image.createdAt.toKotlinLocalDateTime()
    )

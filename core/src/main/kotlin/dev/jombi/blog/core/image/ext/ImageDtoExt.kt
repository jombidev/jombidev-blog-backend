package dev.jombi.blog.core.image.ext

import dev.jombi.blog.core.image.entity.Image
import kotlinx.datetime.toKotlinLocalDateTime

fun dev.jombi.blog.business.image.dto.ImageDto.Companion.of(image: Image) =
    dev.jombi.blog.business.image.dto.ImageDto(
        image.id.get,
        image.name,
        image.url,
        image.createdAt.toKotlinLocalDateTime()
    )

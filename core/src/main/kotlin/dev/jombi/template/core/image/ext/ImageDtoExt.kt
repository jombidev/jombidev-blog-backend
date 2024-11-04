package dev.jombi.template.core.image.ext

import dev.jombi.template.business.image.dto.ImageDto
import dev.jombi.template.core.image.entity.Image
import kotlinx.datetime.toKotlinLocalDateTime

fun ImageDto.Companion.of(image: Image) =
    ImageDto(image.id.get, image.name, image.url, image.createdAt.toKotlinLocalDateTime())

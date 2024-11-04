package dev.jombi.template.business.image.service

import dev.jombi.template.business.image.dto.ImageDto

interface ImageService {
    fun uploadImage(originalName: String?, bytes: ByteArray): ImageDto
    fun deleteImage(id: String)
    fun getImage(id: String): ImageDto
}

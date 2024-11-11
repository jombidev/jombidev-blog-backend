package dev.jombi.blog.business.image.service

import dev.jombi.blog.business.image.dto.ImageDto

interface ImageService {
    fun uploadImage(originalName: String?, bytes: ByteArray): ImageDto
    fun deleteImage(id: String)
    fun readImage(id: String): ByteArray
    fun getImage(id: String): ImageDto
}

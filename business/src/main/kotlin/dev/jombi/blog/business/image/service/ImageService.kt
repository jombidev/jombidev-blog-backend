package dev.jombi.blog.business.image.service

interface ImageService {
    fun uploadImage(originalName: String?, bytes: ByteArray): dev.jombi.blog.business.image.dto.ImageDto
    fun deleteImage(id: String)
    fun getImage(id: String): dev.jombi.blog.business.image.dto.ImageDto
}

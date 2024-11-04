package dev.jombi.blog.core.image.infra

interface ImageUploader {
    fun uploadImage(name: String, bytes: ByteArray): String // imageURL
}

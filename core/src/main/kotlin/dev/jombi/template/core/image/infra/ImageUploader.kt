package dev.jombi.template.core.image.infra

interface ImageUploader {
    fun uploadImage(name: String, bytes: ByteArray): String // imageURL
}

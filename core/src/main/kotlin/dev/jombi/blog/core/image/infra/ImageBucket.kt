package dev.jombi.blog.core.image.infra

import java.util.*

interface ImageBucket {
    fun uploadImage(id: UUID, bytes: ByteArray): Boolean // imageURL
    fun readImage(id: UUID): ByteArray?
}

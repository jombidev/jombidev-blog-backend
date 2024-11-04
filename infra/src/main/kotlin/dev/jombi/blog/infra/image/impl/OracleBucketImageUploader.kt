package dev.jombi.blog.infra.image.impl

import dev.jombi.blog.core.image.infra.ImageUploader
import org.springframework.stereotype.Component

@Component
class OracleBucketImageUploader : ImageUploader {
    override fun uploadImage(name: String, bytes: ByteArray): String {
        TODO("im fucking idiot")
    }

}

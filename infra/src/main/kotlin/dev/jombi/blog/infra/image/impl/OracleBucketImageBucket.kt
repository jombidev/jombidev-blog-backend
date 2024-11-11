package dev.jombi.blog.infra.image.impl

import com.oracle.cloud.spring.storage.Storage
import dev.jombi.blog.core.image.infra.ImageBucket
import dev.jombi.blog.infra.image.BucketProperties
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.*

class OracleBucketImageBucket(
    private val storage: Storage,
    private val bucketProperties: BucketProperties,
) : ImageBucket {
    override fun uploadImage(id: UUID, bytes: ByteArray): Boolean {
        try {
            storage.upload(bucketProperties.bucketName, "$id", ByteArrayInputStream(bytes))
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    override fun readImage(id: UUID): ByteArray {
        val res = storage.download(bucketProperties.bucketName, "$id")
        return res.contentAsByteArray
    }
}

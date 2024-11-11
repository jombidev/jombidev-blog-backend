package dev.jombi.blog.infra.image.impl

import dev.jombi.blog.core.image.infra.ImageBucket
import dev.jombi.blog.infra.image.BucketProperties
import java.io.IOException
import java.nio.file.StandardOpenOption
import java.util.*
import kotlin.io.path.*

class LocalImageBucket(
    private val bucketProperties: BucketProperties
) : ImageBucket {
    private val savePath = Path("idiot").apply {
        createDirectories()
    }

    override fun uploadImage(id: UUID, bytes: ByteArray): Boolean {
        return try {
            val path = Path(savePath.absolutePathString(), bucketProperties.bucketName, "$id")
            path.writeBytes(bytes, StandardOpenOption.CREATE)

            true
        } catch (e: IOException) {
            false
        }
    }

    override fun readImage(id: UUID): ByteArray? {
        val fileOrNull = Path(savePath.toString(), bucketProperties.bucketName, "$id")
        if (!fileOrNull.exists() || !fileOrNull.isReadable()) return null
        return fileOrNull.readBytes()
    }
}

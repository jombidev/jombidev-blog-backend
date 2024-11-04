package dev.jombi.template.infra.image.impl

import dev.jombi.template.core.image.infra.ImageUploader
import org.springframework.stereotype.Component
import java.nio.file.StandardOpenOption
import kotlin.io.path.*

@Component
class LocalImageUploader : ImageUploader {
    val savePath = Path("idiot").apply {
        createDirectories()
    }

    override fun uploadImage(name: String, bytes: ByteArray): String {
        val path = Path(savePath.absolutePathString(), name)
        path.writeBytes(bytes, StandardOpenOption.CREATE)

        return path.toUri().toString()
    }
}

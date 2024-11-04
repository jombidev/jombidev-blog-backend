package dev.jombi.blog.core.image.service

import dev.jombi.blog.common.exception.CustomException
import dev.jombi.blog.core.common.utils.UUIDSafe
import dev.jombi.blog.core.image.ext.of
import dev.jombi.blog.core.image.entity.Image
import dev.jombi.blog.core.image.exception.ImageExceptionDetails
import dev.jombi.blog.core.image.infra.ImageUploader
import dev.jombi.blog.core.image.repository.ImageRepository
import org.apache.tika.config.TikaConfig
import org.apache.tika.detect.DefaultDetector
import org.apache.tika.metadata.Metadata
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.util.*

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository,
    private val uploader: ImageUploader,
) : dev.jombi.blog.business.image.service.ImageService {
    override fun uploadImage(originalName: String?, bytes: ByteArray): dev.jombi.blog.business.image.dto.ImageDto {
        val name = "${UUID.randomUUID()}.${guessExtension(originalName, bytes)}"
        val url = uploader.uploadImage(name, bytes)
        val image = imageRepository.save(Image(url = url, name = name))
        return dev.jombi.blog.business.image.dto.ImageDto.of(image)
    }

    private fun guessExtension(ogName: String?, bytes: ByteArray) =
        ogName?.substring(ogName.lastIndexOf('.') + 1) ?: TikaConfig.getDefaultConfig().mimeRepository.forName(
            DefaultDetector().detect(
                ByteArrayInputStream(bytes),
                Metadata()
            ).toString()
        ).extension

    override fun deleteImage(id: String) = imageRepository.deleteById(UUIDSafe(id))

    override fun getImage(id: String): dev.jombi.blog.business.image.dto.ImageDto {
        val uuid = UUIDSafe(id)
        val image = imageRepository.findByIdOrNull(uuid) ?: throw CustomException(ImageExceptionDetails.IMAGE_NOT_FOUND)
        return dev.jombi.blog.business.image.dto.ImageDto.of(image)
    }
}

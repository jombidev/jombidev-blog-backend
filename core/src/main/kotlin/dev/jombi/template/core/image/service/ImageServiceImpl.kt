package dev.jombi.template.core.image.service

import dev.jombi.template.business.image.dto.ImageDto
import dev.jombi.template.business.image.service.ImageService
import dev.jombi.template.common.exception.CustomException
import dev.jombi.template.core.common.utils.UUIDSafe
import dev.jombi.template.core.image.ext.of
import dev.jombi.template.core.image.entity.Image
import dev.jombi.template.core.image.exception.ImageExceptionDetails
import dev.jombi.template.core.image.infra.ImageUploader
import dev.jombi.template.core.image.repository.ImageRepository
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
) : ImageService {
    override fun uploadImage(originalName: String?, bytes: ByteArray): ImageDto {
        val name = "${UUID.randomUUID()}.${guessExtension(originalName, bytes)}"
        val url = uploader.uploadImage(name, bytes)
        val image = imageRepository.save(Image(url = url, name = name))
        return ImageDto.of(image)
    }

    private fun guessExtension(ogName: String?, bytes: ByteArray) =
        ogName?.substring(ogName.lastIndexOf('.') + 1) ?: TikaConfig.getDefaultConfig().mimeRepository.forName(
            DefaultDetector().detect(
                ByteArrayInputStream(bytes),
                Metadata()
            ).toString()
        ).extension

    override fun deleteImage(id: String) = imageRepository.deleteById(UUIDSafe(id))

    override fun getImage(id: String): ImageDto {
        val uuid = UUIDSafe(id)
        val image = imageRepository.findByIdOrNull(uuid) ?: throw CustomException(ImageExceptionDetails.IMAGE_NOT_FOUND)
        return ImageDto.of(image)
    }
}

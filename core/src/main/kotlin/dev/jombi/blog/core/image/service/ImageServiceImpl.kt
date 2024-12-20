package dev.jombi.blog.core.image.service

import dev.jombi.blog.business.image.dto.ImageDto
import dev.jombi.blog.business.image.service.ImageService
import dev.jombi.blog.common.exception.CustomException
import dev.jombi.blog.common.multipart.guessExtension
import dev.jombi.blog.core.common.utils.UUIDSafe
import dev.jombi.blog.core.image.entity.Image
import dev.jombi.blog.core.image.exception.ImageExceptionDetails
import dev.jombi.blog.core.image.ext.of
import dev.jombi.blog.core.image.infra.ImageBucket
import dev.jombi.blog.core.image.repository.ImageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository,
    private val bucket: ImageBucket,
) : ImageService {
    override fun uploadImage(originalName: String?, bytes: ByteArray): ImageDto {
        val name = "$originalName.${guessExtension(bytes)}"
        val image = imageRepository.save(Image(name = name))

        val isUploaded = bucket.uploadImage(image.id.get, bytes)
        if (!isUploaded) {
            imageRepository.delete(image)
            throw CustomException(ImageExceptionDetails.IMAGE_UPLOAD_FAILED)
        }

        return ImageDto.of(image)
    }

    override fun deleteImage(id: String) = imageRepository.deleteById(UUIDSafe(id))

    override fun readImage(id: String): ByteArray =
        bucket.readImage(UUIDSafe(id)) ?: throw CustomException(ImageExceptionDetails.IMAGE_NOT_FOUND)

    override fun getImage(id: String): ImageDto {
        val uuid = UUIDSafe(id)
        val image = imageRepository.findByIdOrNull(uuid)
            ?: throw CustomException(ImageExceptionDetails.IMAGE_NOT_FOUND)

        return ImageDto.of(image)
    }
}

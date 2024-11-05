package dev.jombi.blog.api.image.presentation

import dev.jombi.blog.business.image.dto.ImageDto
import dev.jombi.blog.common.multipart.ValidImageFile
import dev.jombi.blog.common.multipart.guessMediaType
import dev.jombi.blog.common.response.ResponseData
import dev.jombi.blog.common.response.ResponseEmpty
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URL

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageService: dev.jombi.blog.business.image.service.ImageService
) {
    @PostMapping
    @Validated
    fun uploadImage(
        @Valid
        @ValidImageFile([MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE], 10L * 1024L * 1024L)
        @RequestPart(name = "image", required = true)
        image: MultipartFile
    ): ResponseEntity<ResponseData<ImageDto>> {
        val imageDto = imageService.uploadImage(image.originalFilename, image.bytes)
        return ResponseData.ok(data = imageDto)
    }

    @GetMapping("/{id}")
    fun getImage(@PathVariable id: String): ResponseEntity<ResponseData<ImageDto>> {
        val image = imageService.getImage(id)
        return ResponseData.ok(data = image)
    }

    @GetMapping("/{id}/file")
    fun getBytes(@PathVariable id: String): ResponseEntity<ByteArray> {
        val bytes = URL(imageService.getImage(id).url).openStream().readBytes()
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.parseMediaType(guessMediaType(bytes)))
            .contentLength(bytes.size.toLong())
            .body(bytes)
    }

    @DeleteMapping("/{id}")
    fun deleteImage(@PathVariable id: String): ResponseEntity<ResponseEmpty> {
        imageService.deleteImage(id)
        return ResponseEmpty.ok()
    }
}

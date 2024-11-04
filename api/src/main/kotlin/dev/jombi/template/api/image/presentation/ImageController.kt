package dev.jombi.template.api.image.presentation

import dev.jombi.template.business.image.dto.ImageDto
import dev.jombi.template.business.image.service.ImageService
import dev.jombi.template.common.multipart.ValidImageFile
import dev.jombi.template.common.response.ResponseData
import dev.jombi.template.common.response.ResponseEmpty
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageService: ImageService
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

    @DeleteMapping("/{id}")
    fun deleteImage(@PathVariable id: String): ResponseEntity<ResponseEmpty> {
        imageService.deleteImage(id)
        return ResponseEmpty.ok()
    }
}

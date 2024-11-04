package dev.jombi.template.infra.image

import dev.jombi.template.core.image.infra.ImageUploader
import dev.jombi.template.infra.image.impl.LocalImageUploader
import dev.jombi.template.infra.image.impl.OracleBucketImageUploader
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class UploaderChoicer(
    @Value("\${app.image_uploader_type}")
    val uploaderType: String
) {
    @Bean
    @Primary
    fun uploader(localImageUploader: LocalImageUploader, oracleBucketImageUploader: OracleBucketImageUploader): ImageUploader {
        return when (UploaderType.guess(uploaderType)) {
            UploaderType.Local -> localImageUploader
            UploaderType.Oracle -> oracleBucketImageUploader
        }
    }
}

package dev.jombi.blog.infra.image

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app.bucket")
class BucketProperties {
    lateinit var bucketType: String

    fun getBucketTypeEnum(): BucketType = BucketType.valueOf(bucketType)

    private val validNameRegex = Regex("[a-zA-Z-_0-9]*")
    var bucketName: String = ""
        get() {
            if (field.isEmpty() || validNameRegex.matchEntire(field) == null)
                throw IllegalArgumentException("bucketName is required when UploaderType.ORACLE")
            return field
        }
}

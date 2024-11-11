package dev.jombi.blog.infra.image

import com.oracle.cloud.spring.storage.Storage
import dev.jombi.blog.core.image.infra.ImageBucket
import dev.jombi.blog.infra.image.annotation.ConditionalOnServiceType
import dev.jombi.blog.infra.image.impl.LocalImageBucket
import dev.jombi.blog.infra.image.impl.OracleBucketImageBucket
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BucketInjection {
    @Bean
    @ConditionalOnServiceType(BucketType.LOCAL)
    fun localImageBucket(bucketProperties: BucketProperties): ImageBucket {
        return LocalImageBucket(bucketProperties)
    }

    @Bean
    @ConditionalOnServiceType(BucketType.ORACLE)
    fun oracleImageBucket(
        storage: Storage,
        bucketProperties: BucketProperties
    ): ImageBucket {
        return OracleBucketImageBucket(storage, bucketProperties)
    }
}

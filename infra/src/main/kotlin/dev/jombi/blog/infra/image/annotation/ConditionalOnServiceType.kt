package dev.jombi.blog.infra.image.annotation

import dev.jombi.blog.infra.image.BucketType
import org.springframework.context.annotation.Conditional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Conditional(OnServiceTypeCondition::class)
annotation class ConditionalOnServiceType(val value: BucketType)

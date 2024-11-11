package dev.jombi.blog.infra.image.annotation

import dev.jombi.blog.infra.image.BucketType
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

class OnServiceTypeCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val properties = context.environment
        val actual = BucketType.valueOf(properties.getProperty("app.bucket.bucket-type")?.uppercase() ?: "")

        val excepted = metadata
            .annotations[ConditionalOnServiceType::class.java]
            .getEnum("value", BucketType::class.java)

        return excepted == actual
    }
}

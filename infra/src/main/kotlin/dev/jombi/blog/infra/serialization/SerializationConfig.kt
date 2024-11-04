package dev.jombi.blog.infra.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.UUID

@Configuration
@OptIn(ExperimentalSerializationApi::class)
class SerializationConfig : WebMvcConfigurer {
    @Bean
    fun json() = Json {
        isLenient = true
        allowComments = true
        encodeDefaults = true
        allowSpecialFloatingPointValues = true
        ignoreUnknownKeys = true
        allowTrailingComma = true
        decodeEnumsCaseInsensitive = true
        serializersModule = SerializersModule {
            contextual(UUID::class, UUIDSerializer())
        }
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val converter = KotlinSerializationJsonHttpMessageConverter(json())
        converters.forEachIndexed { index, httpMessageConverter ->
            if (httpMessageConverter is KotlinSerializationJsonHttpMessageConverter) {
                converters[index] = converter
                return@extendMessageConverters
            }
        }
    }
}

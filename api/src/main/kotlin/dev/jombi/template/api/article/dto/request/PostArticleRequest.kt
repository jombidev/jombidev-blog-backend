package dev.jombi.template.api.article.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class PostArticleRequest(
    val title: String,
    val content: String,
    val images: List<String> = emptyList(), // uuid list
)

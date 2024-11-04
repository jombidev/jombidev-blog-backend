package dev.jombi.blog.api.article.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class EditArticleRequest(
    val title: String? = null,
    val content: String? = null,
    val images: List<String>? = null, // uuid list
)

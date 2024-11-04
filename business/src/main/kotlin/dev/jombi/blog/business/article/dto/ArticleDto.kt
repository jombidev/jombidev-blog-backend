package dev.jombi.blog.business.article.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val images: List<String>,
)

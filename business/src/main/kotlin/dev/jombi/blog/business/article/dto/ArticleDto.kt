package dev.jombi.blog.business.article.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ArticleDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val images: List<@Contextual UUID>,
)

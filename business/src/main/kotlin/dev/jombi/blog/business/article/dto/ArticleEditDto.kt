package dev.jombi.blog.business.article.dto

data class ArticleEditDto(
    val title: String?,
    val content: String?,
    val images: List<String>?, // uuid list
)

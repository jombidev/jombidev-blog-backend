package dev.jombi.blog.core.article.ext

import dev.jombi.blog.business.article.dto.ArticleDto
import dev.jombi.blog.core.article.entity.Article

fun ArticleDto.Companion.of(article: Article) =
    ArticleDto(
        article.id.get,
        article.title,
        article.content,
        article.author.name,
        article.images.map { it.url }
    )

fun ArticleDto.Companion.ofImageUUID(article: Article) =
    ArticleDto(
        article.id.get,
        article.title,
        article.content,
        article.author.name,
        article.images.map { it.id.get.toString() }
    )

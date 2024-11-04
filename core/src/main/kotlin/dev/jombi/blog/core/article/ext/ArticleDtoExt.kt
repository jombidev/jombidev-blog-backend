package dev.jombi.blog.core.article.ext

import dev.jombi.blog.core.article.entity.Article

fun dev.jombi.blog.business.article.dto.ArticleDto.Companion.of(article: Article) =
    dev.jombi.blog.business.article.dto.ArticleDto(
        article.id.get,
        article.title,
        article.content,
        article.author.name,
        article.images.map { it.url }
    )

package dev.jombi.template.core.article.ext

import dev.jombi.template.business.article.dto.ArticleDto
import dev.jombi.template.core.article.entity.Article

fun ArticleDto.Companion.of(article: Article) = ArticleDto(
    article.id.get,
    article.title,
    article.content,
    article.author.name,
    article.images.map { it.url }
)

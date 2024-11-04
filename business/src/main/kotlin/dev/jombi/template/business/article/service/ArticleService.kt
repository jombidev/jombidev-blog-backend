package dev.jombi.template.business.article.service

import dev.jombi.template.business.article.dto.ArticleDto
import dev.jombi.template.business.article.dto.ArticleEditDto
import dev.jombi.template.business.article.dto.ArticleWriteDto
import dev.jombi.template.common.paged.PageRequest
import dev.jombi.template.common.paged.PagedList

interface ArticleService {
    fun createArticle(dto: ArticleWriteDto): ArticleDto
    fun getArticles(paged: PageRequest): PagedList<ArticleDto>
    fun getArticle(articleId: Long): ArticleDto
    fun editArticle(articleId: Long, dto: ArticleEditDto): ArticleDto
    fun deleteArticle(articleId: Long)
}

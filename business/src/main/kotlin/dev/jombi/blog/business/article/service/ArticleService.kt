package dev.jombi.blog.business.article.service

import dev.jombi.blog.business.article.dto.ArticleDto
import dev.jombi.blog.common.paged.PageRequest
import dev.jombi.blog.common.paged.PagedList

interface ArticleService {
    fun createArticle(dto: dev.jombi.blog.business.article.dto.ArticleWriteDto): ArticleDto
    fun getArticles(paged: PageRequest): PagedList<ArticleDto>
    fun getArticle(articleId: Long): ArticleDto
    fun editArticle(articleId: Long, dto: dev.jombi.blog.business.article.dto.ArticleEditDto): ArticleDto
    fun deleteArticle(articleId: Long)
}

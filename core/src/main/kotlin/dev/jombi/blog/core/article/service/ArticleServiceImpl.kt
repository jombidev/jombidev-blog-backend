package dev.jombi.blog.core.article.service

import dev.jombi.blog.business.article.dto.ArticleDto
import dev.jombi.blog.common.exception.CustomException
import dev.jombi.blog.common.paged.PageRequest
import dev.jombi.blog.common.paged.PagedList
import dev.jombi.blog.core.article.ext.of
import dev.jombi.blog.core.article.entity.Article
import dev.jombi.blog.core.article.exception.ArticleExceptionDetails
import dev.jombi.blog.core.article.repository.ArticleRepository
import dev.jombi.blog.core.common.entity.FetchableId.FetchableLong
import dev.jombi.blog.core.common.entity.FetchableId.FetchableUUID
import dev.jombi.blog.core.common.utils.UUIDSafe
import dev.jombi.blog.core.image.repository.ImageRepository
import dev.jombi.blog.core.member.MemberHolder
import dev.jombi.blog.core.member.ext.isSameMember
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest as JpaPageRequest

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository,
    private val imageRepository: ImageRepository,
    private val holder: MemberHolder,
) : dev.jombi.blog.business.article.service.ArticleService {
    override fun createArticle(dto: dev.jombi.blog.business.article.dto.ArticleWriteDto): ArticleDto {
        val (title, content, images) = dto

        val fetchedImages = images.mapNotNull { FetchableUUID(UUIDSafe(it)).fetch(imageRepository) }

        val article = articleRepository.save(
            Article(
                author = holder.get(),
                title = title,
                content = content,
                images = fetchedImages.toSet()
            )
        )

        return ArticleDto.of(article)
    }

    override fun getArticles(paged: PageRequest): PagedList<ArticleDto> {
        val articles = articleRepository.findAllOrderByLatest(JpaPageRequest.of(paged.page, paged.size))
            .map { ArticleDto.of(it) }

        return PagedList(
            articles.number,
            articles.numberOfElements,
            articles.totalPages,
            articles.hasNext(),
            articles.content
        )
    }

    override fun getArticle(articleId: Long): ArticleDto {
        val article = articleRepository.findByIdOrNull(articleId)
            ?: throw CustomException(ArticleExceptionDetails.ARTICLE_NOT_FOUND, articleId)

        return ArticleDto.of(article)
    }

    override fun editArticle(articleId: Long, dto: dev.jombi.blog.business.article.dto.ArticleEditDto): ArticleDto {
        val (title, content, images) = dto

        val origin = FetchableLong(articleId).fetch(articleRepository)
            ?: throw CustomException(ArticleExceptionDetails.ARTICLE_NOT_FOUND, articleId)

        val me = holder.get()
        if (!origin.author.isSameMember(me))
            throw CustomException(ArticleExceptionDetails.NOT_YOUR_ARTICLE)

        val fetchedImages = images?.mapNotNull { FetchableUUID(UUIDSafe(it)).fetch(imageRepository) }

        val saved = articleRepository.save(
            origin.copy(
                title = title ?: origin.title,
                content = content ?: origin.content,
                images = fetchedImages?.toSet() ?: origin.images
            )
        )

        return ArticleDto.of(saved)
    }

    override fun deleteArticle(articleId: Long) {
        val origin = FetchableLong(articleId).fetch(articleRepository)
            ?: throw CustomException(ArticleExceptionDetails.ARTICLE_NOT_FOUND, articleId)

        val me = holder.get()
        if (!origin.author.isSameMember(me))
            throw CustomException(ArticleExceptionDetails.NOT_YOUR_ARTICLE)

        articleRepository.delete(origin)
    }
}

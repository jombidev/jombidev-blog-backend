package dev.jombi.blog.core.article.repository

import dev.jombi.blog.core.article.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a ORDER BY a._createdAt DESC")
    fun findAllOrderByLatest(page: Pageable): Page<Article>
}

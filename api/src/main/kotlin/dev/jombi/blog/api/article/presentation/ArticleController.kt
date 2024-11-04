package dev.jombi.blog.api.article.presentation

import dev.jombi.blog.business.article.dto.ArticleDto
import dev.jombi.blog.common.paged.PageRequest
import dev.jombi.blog.common.paged.PagedList
import dev.jombi.blog.common.response.ResponseData
import dev.jombi.blog.common.response.ResponseEmpty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: dev.jombi.blog.business.article.service.ArticleService
) {
    @PostMapping
    fun createArticle(@RequestBody request: dev.jombi.blog.api.article.dto.request.PostArticleRequest): ResponseEntity<ResponseData<ArticleDto>> {
        val written = articleService.createArticle(
            dev.jombi.blog.business.article.dto.ArticleWriteDto(request.title, request.content, request.images)
        )

        return ResponseData.created(data = written)
    }

    @GetMapping
    fun getArticles(page: PageRequest): ResponseEntity<ResponseData<PagedList<ArticleDto>>> {
        val articles = articleService.getArticles(page)
        return ResponseData.ok(data = articles)
    }

    @GetMapping("/{id}")
    fun getArticle(@PathVariable id: Long): ResponseEntity<ResponseData<ArticleDto>> {
        val article = articleService.getArticle(id)
        return ResponseData.ok(data = article)
    }

    @PatchMapping("/{id}")
    fun editArticle(@PathVariable id: Long, @RequestBody request: dev.jombi.blog.api.article.dto.request.EditArticleRequest): ResponseEntity<ResponseData<ArticleDto>> {
        val article = articleService.editArticle(
            id, dev.jombi.blog.business.article.dto.ArticleEditDto(request.title, request.content, request.images)
        )
        return ResponseData.ok(data = article)
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<ResponseEmpty> {
        articleService.deleteArticle(id)
        return ResponseEmpty.ok()
    }
}

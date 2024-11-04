package dev.jombi.template.api.article.presentation

import dev.jombi.template.api.article.dto.request.EditArticleRequest
import dev.jombi.template.api.article.dto.request.PostArticleRequest
import dev.jombi.template.business.article.dto.ArticleDto
import dev.jombi.template.business.article.dto.ArticleEditDto
import dev.jombi.template.business.article.dto.ArticleWriteDto
import dev.jombi.template.business.article.service.ArticleService
import dev.jombi.template.common.paged.PageRequest
import dev.jombi.template.common.paged.PagedList
import dev.jombi.template.common.response.ResponseData
import dev.jombi.template.common.response.ResponseEmpty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService
) {
    @PostMapping
    fun createArticle(@RequestBody request: PostArticleRequest): ResponseEntity<ResponseData<ArticleDto>> {
        val written = articleService.createArticle(
            ArticleWriteDto(request.title, request.content, request.images)
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
    fun editArticle(@PathVariable id: Long, @RequestBody request: EditArticleRequest): ResponseEntity<ResponseData<ArticleDto>> {
        val article = articleService.editArticle(
            id, ArticleEditDto(request.title, request.content, request.images)
        )
        return ResponseData.ok(data = article)
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<ResponseEmpty> {
        articleService.deleteArticle(id)
        return ResponseEmpty.ok()
    }
}

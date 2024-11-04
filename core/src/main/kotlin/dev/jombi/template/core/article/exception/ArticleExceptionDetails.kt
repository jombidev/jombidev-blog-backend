package dev.jombi.template.core.article.exception

import dev.jombi.template.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class ArticleExceptionDetails(override val message: String, override val status: HttpStatus) : ExceptionDetail {
    ARTICLE_NOT_FOUND("게시글 '%s'를 찾을 수 없음.", HttpStatus.NOT_FOUND),
    NOT_YOUR_ARTICLE("당신의 게시글이 아님.", HttpStatus.UNAUTHORIZED),
    ;
    override val code = name
}

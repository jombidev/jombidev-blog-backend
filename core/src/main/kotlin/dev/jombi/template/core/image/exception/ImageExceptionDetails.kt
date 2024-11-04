package dev.jombi.template.core.image.exception

import dev.jombi.template.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class ImageExceptionDetails(override val message: String, override val status: HttpStatus) : ExceptionDetail {
    IMAGE_NOT_FOUND("그런 이미지는 없다.", HttpStatus.NOT_FOUND),
    ;
    override val code = name
}

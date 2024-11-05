package dev.jombi.blog.common.exception

import org.springframework.http.HttpStatus

interface ExceptionDetail {
    val message: String
    val status: HttpStatus
    val code: String
}
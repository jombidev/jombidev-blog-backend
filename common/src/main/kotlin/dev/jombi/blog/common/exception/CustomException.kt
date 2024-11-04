package dev.jombi.blog.common.exception

class CustomException(val detail: ExceptionDetail, vararg val formats: Any?) : RuntimeException()

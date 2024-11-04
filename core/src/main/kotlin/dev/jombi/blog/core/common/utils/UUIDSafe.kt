package dev.jombi.blog.core.common.utils

import dev.jombi.blog.common.exception.CustomException
import dev.jombi.blog.common.exception.GlobalExceptionDetail
import java.util.*

fun UUIDSafe(uuidString: String) = try {
    UUID.fromString(uuidString)
} catch (e: IllegalArgumentException) {
    throw CustomException(GlobalExceptionDetail.ILLEGAL_UUID_TYPE, uuidString)
}

package dev.jombi.template.core.common.utils

import dev.jombi.template.common.exception.CustomException
import dev.jombi.template.common.exception.GlobalExceptionDetail
import java.util.*

fun UUIDSafe(uuidString: String) = try {
    UUID.fromString(uuidString)
} catch (e: IllegalArgumentException) {
    throw CustomException(GlobalExceptionDetail.ILLEGAL_UUID_TYPE, uuidString)
}

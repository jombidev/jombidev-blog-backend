package dev.jombi.blog.common.response

import kotlinx.serialization.Serializable

@Serializable
sealed interface BaseResponse {
    val code: String
    val status: Int
}

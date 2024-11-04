package dev.jombi.template.common.response

import kotlinx.serialization.Serializable

@Serializable
sealed interface BaseResponse {
    val code: String
    val status: Int
}

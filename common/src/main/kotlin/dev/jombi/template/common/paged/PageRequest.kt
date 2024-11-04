package dev.jombi.template.common.paged

import kotlinx.serialization.Serializable

@Serializable
data class PageRequest(
    val size: Int,
    val page: Int
)

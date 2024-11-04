package dev.jombi.blog.common.paged

import kotlinx.serialization.Serializable

@Serializable
data class PagedList<T>(
    val page: Int,
    val size: Int,
    val total: Int,
    val hasNext: Boolean,
    val data: List<T>
)

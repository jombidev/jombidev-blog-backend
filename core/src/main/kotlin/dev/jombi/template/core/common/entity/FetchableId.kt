package dev.jombi.template.core.common.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID

sealed interface FetchableId<ID : Any> {
    val _id: ID?
    val get get() = _id!!
    fun <T : Any> fetch(repository: JpaRepository<T, ID>) = repository.findByIdOrNull(get)

    @JvmInline
    value class FetchableUUID(override val _id: UUID?) : FetchableId<UUID> {
        companion object {
            val NULL = FetchableUUID(null)
        }
    }
    @JvmInline
    value class FetchableLong(override val _id: Long?) : FetchableId<Long> {
        companion object {
            val NULL = FetchableLong(null)
        }
    }
}

package dev.jombi.blog.core.image.entity

import dev.jombi.blog.core.common.entity.BaseTimeEntity
import dev.jombi.blog.core.common.entity.FetchableId.FetchableUUID
import jakarta.persistence.*

@Entity
@Table
class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: FetchableUUID = FetchableUUID.NULL,
    val name: String,
) : BaseTimeEntity()

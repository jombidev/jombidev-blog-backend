package dev.jombi.template.core.image.entity

import dev.jombi.template.core.common.entity.BaseTimeEntity
import dev.jombi.template.core.common.entity.FetchableId.FetchableUUID
import jakarta.persistence.*

@Entity
@Table
class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: FetchableUUID = FetchableUUID.NULL,

    @Column(nullable = false, updatable = false)
    val url: String,

    val name: String,
) : BaseTimeEntity()

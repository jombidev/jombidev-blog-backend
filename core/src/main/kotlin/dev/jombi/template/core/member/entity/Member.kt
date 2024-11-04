package dev.jombi.template.core.member.entity

import dev.jombi.template.core.common.entity.BaseTimeEntity
import dev.jombi.template.core.common.entity.FetchableId.FetchableUUID
import jakarta.persistence.*

@Entity(name = "tb_member")
data class Member(
    @Column(unique = true)
    val credential: String,

    @Column
    val password: String, // bcrypt

    @Column
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: FetchableUUID = FetchableUUID.NULL
) : BaseTimeEntity()

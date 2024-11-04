package dev.jombi.blog.core.member.entity

import dev.jombi.blog.core.common.entity.BaseTimeEntity
import dev.jombi.blog.core.common.entity.FetchableId.FetchableUUID
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

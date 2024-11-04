package dev.jombi.template.core.member.repository

import dev.jombi.template.core.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberJpaRepository : JpaRepository<Member, UUID> {
    fun existsByCredential(credential: String): Boolean
    fun findMemberByCredential(credential: String): Member? // uniq
}

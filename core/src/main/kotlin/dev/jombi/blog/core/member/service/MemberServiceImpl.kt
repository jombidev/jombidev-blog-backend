package dev.jombi.blog.core.member.service

import dev.jombi.blog.core.member.MemberHolder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberHolder: MemberHolder
) : dev.jombi.blog.business.member.service.MemberService {
    override fun me(): dev.jombi.blog.business.member.dto.MemberDto {
        val member = memberHolder.get()
        return dev.jombi.blog.business.member.dto.MemberDto(member.name)
    }
}

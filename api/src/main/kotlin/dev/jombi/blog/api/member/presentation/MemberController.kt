package dev.jombi.blog.api.member.presentation

import dev.jombi.blog.business.member.dto.MemberDto
import dev.jombi.blog.common.response.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: dev.jombi.blog.business.member.service.MemberService
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<MemberDto>> {
        val me = memberService.me()

        return ResponseData.ok(data = me)
    }
}

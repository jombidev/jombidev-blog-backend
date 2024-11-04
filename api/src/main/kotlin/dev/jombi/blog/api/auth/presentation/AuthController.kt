package dev.jombi.blog.api.auth.presentation

import dev.jombi.blog.business.auth.dto.TokenDto
import dev.jombi.blog.common.response.ResponseData
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: dev.jombi.blog.business.auth.service.AuthService
) {
    @PostMapping("/login")
    fun authenticate(@RequestBody @Valid request: dev.jombi.blog.api.auth.dto.request.AuthenticateRequestDto): ResponseEntity<ResponseData<TokenDto>> {
        val dto = authService.authenticate(request.credential, request.password)
        return ResponseData.ok(data = dto)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody @Valid request: dev.jombi.blog.api.auth.dto.request.NewTokenRequestDto): ResponseEntity<ResponseData<TokenDto>> {
        val dto = authService.getNewToken(request.refreshToken)
        return ResponseData.ok(data = dto)
    }
}

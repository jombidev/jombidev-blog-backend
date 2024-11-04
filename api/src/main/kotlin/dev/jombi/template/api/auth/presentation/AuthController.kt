package dev.jombi.template.api.auth.presentation

import dev.jombi.template.api.auth.dto.request.AuthenticateRequestDto
import dev.jombi.template.api.auth.dto.request.NewTokenRequestDto
import dev.jombi.template.business.auth.dto.TokenDto
import dev.jombi.template.business.auth.service.AuthService
import dev.jombi.template.common.response.ResponseData
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun authenticate(@RequestBody @Valid request: AuthenticateRequestDto): ResponseEntity<ResponseData<TokenDto>> {
        val dto = authService.authenticate(request.credential, request.password)
        return ResponseData.ok(data = dto)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody @Valid request: NewTokenRequestDto): ResponseEntity<ResponseData<TokenDto>> {
        val dto = authService.getNewToken(request.refreshToken)
        return ResponseData.ok(data = dto)
    }
}

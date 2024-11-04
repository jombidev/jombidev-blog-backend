package dev.jombi.blog.infra.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Component
class JwtProperties(
    @Value("\${app.jwt.secret}")
    private val secret: String,

    @Value("\${app.jwt.access_expires_after}")
    val accessExpiresAfter: Long,

    @Value("\${app.jwt.refresh_expires_after}")
    val refreshExpiresAfter: Long,

    @Value("\${app.jwt.issuer}")
    val issuer: String
) {
    private var _key: SecretKey? = null

    @OptIn(ExperimentalEncodingApi::class)
    fun secretKey(): SecretKey {
        if (_key == null) {
            val key = if (secret == "null") {
                val generated = java.util.Base64.getEncoder().encodeToString(Jwts.SIG.HS512.key().build().encoded)
                println("JWT Secret is not found on config. Initially generated new Secret to run.")
                println()
                println("Your Secret is: $generated")
                println()
                generated
            } else secret

            _key = Keys.hmacShaKeyFor(Base64.decode(key))
        }
        return _key!!
    }
}

package dev.jombi.template.infra.exception

import dev.jombi.template.common.exception.CustomException
import dev.jombi.template.common.exception.GlobalExceptionDetail
import dev.jombi.template.common.response.ResponseError
import dev.jombi.template.core.auth.exception.AuthExceptionDetails
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.resource.NoResourceFoundException
import kotlin.math.pow
import kotlin.math.roundToLong


@RestControllerAdvice
class ExceptionHandlerAdvice {
    private val log = LoggerFactory.getLogger(ExceptionHandlerAdvice::class.java)

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception?): ResponseEntity<ResponseError> {
        log.error("Error while processing request: ", e)
        return ResponseError.of(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(CustomException::class)
    fun customException(e: CustomException): ResponseEntity<ResponseError> {
        return ResponseError.of(e.detail, *e.formats)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingParameter(e: MissingServletRequestParameterException): ResponseEntity<ResponseError> {
        return ResponseError.of(GlobalExceptionDetail.PARAMETER_NOT_MATCH, e.parameterName, e.parameterType)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun noResourceFound(e: NoResourceFoundException): ResponseEntity<ResponseError> {
        return ResponseError.of(GlobalExceptionDetail.RESOURCE_NOT_FOUND, e.resourcePath)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException) =
        ResponseError.of(
            GlobalExceptionDetail.METHOD_NOT_SUPPORTED,
            e.method,
            e.supportedMethods?.joinToString("', '") ?: "N/A"
        )

    @OptIn(ExperimentalSerializationApi::class)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ResponseError> {
        (e.rootCause as? MissingFieldException)?.let {
            return ResponseError.of(
                GlobalExceptionDetail.BODY_MISSING_FIELD,
                it.missingFields.joinToString(", ") { field -> "'$field'" },
            )
        }
        return ResponseError.of(GlobalExceptionDetail.UNPROCESSABLE_BODY)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun httpMediaTypeNotSupportedException(e: HttpMediaTypeNotSupportedException) =
        ResponseError.of(
            GlobalExceptionDetail.UNSUPPORTED_MEDIA_TYPE,
            e.contentType,
            e.supportedMediaTypes.takeIf { it.isNotEmpty() }?.joinToString("', '") ?: "N/A"
        )

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxUploadSizeExceededException(e: MaxUploadSizeExceededException): ResponseEntity<ResponseError> {
        val cause = (e.rootCause as? SizeLimitExceededException)
        return if (cause != null) {
            ResponseError.of(
                GlobalExceptionDetail.PAYLOAD_TOO_LARGE,
                (cause.actualSize / 1024.0 / 1024.0).roundTo(2).toString(),
                (cause.permittedSize / 1024.0 / 1024.0).roundTo(2).toString()
            )
        } else {
            e.printStackTrace()
            ResponseError.of(GlobalExceptionDetail.PAYLOAD_TOO_LARGE_EMPTY)
        }
    }

    private fun Double.roundTo(decimals: Int): Double {
        val factor = 10.0.pow(decimals)
        return (this * factor).roundToLong() / factor
    }

    @ExceptionHandler(HandlerMethodValidationException::class)
    fun handleHandlerMethodValidationException(e: HandlerMethodValidationException): ResponseEntity<ResponseError> {

        e.printStackTrace()
        return ResponseError.of(GlobalExceptionDetail.UNPROCESSABLE_BODY)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun badCredentialsException(e: BadCredentialsException) =
        ResponseError.of(AuthExceptionDetails.BAD_CREDENTIALS)
}

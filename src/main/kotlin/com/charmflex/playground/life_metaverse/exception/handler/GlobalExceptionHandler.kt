package com.charmflex.playground.life_metaverse.exception.handler

import com.charmflex.playground.life_metaverse.exception.LMException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
internal class GlobalExceptionHandler {
    @ExceptionHandler(LMException::class)
    fun handleException(LMException: LMException): ResponseEntity<Map<String, Any>> {
        val body = mapOf(
            "errorCode" to LMException.errorCode,
            "errorMessage" to LMException.errorMessage
        )
        return ResponseEntity
            .status(LMException.statusCode)
            .body(body)
    }
}
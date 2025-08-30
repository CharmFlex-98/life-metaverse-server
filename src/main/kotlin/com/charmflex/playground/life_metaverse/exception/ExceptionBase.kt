package com.charmflex.playground.life_metaverse.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse

abstract class ExceptionBase(
    val statusCode: Int,
    val errorCode: String,
    val errorMessage: String
) : Throwable() {
    fun toBodyString(): String {
        val objectMapper = ObjectMapper()
        return objectMapper.writer().writeValueAsString(mapOf("errorCode" to errorCode, "errorMessage" to errorCode))
    }
}


sealed interface LMErrors {
    object GenericException : ExceptionBase(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "GENERIC_ERROR", "generic error occurred.")
    object InvalidAvatar : ExceptionBase(HttpServletResponse.SC_BAD_REQUEST, "AVATAR_CREATION_FAILURE", "Failed to create avatar.")
}


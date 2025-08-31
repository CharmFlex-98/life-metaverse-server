package com.charmflex.playground.life_metaverse.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse

sealed class LMException(
    val statusCode: Int,
    val errorCode: String,
    val errorMessage: String
) : Throwable() {
    fun toBodyString(): String {
        val objectMapper = ObjectMapper()
        return objectMapper.writer().writeValueAsString(mapOf("errorCode" to errorCode, "errorMessage" to errorCode))
    }



    object GenericException : LMException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "GENERIC_ERROR", "generic error occurred.")
    object InvalidAvatar : LMException(HttpServletResponse.SC_BAD_REQUEST, "AVATAR_CREATION_FAILURE", "Failed to create avatar.")
    object DuplicatedAvatar: LMException(HttpServletResponse.SC_BAD_REQUEST, "DUPLICATE", "Oops, the avatar name given is too popular, there is already existing avatar. Please use another name.")
}


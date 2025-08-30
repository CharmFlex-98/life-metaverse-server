package com.charmflex.playground.life_metaverse.avatar.controller

import com.charmflex.playground.life_metaverse.avatar.Constant
import com.charmflex.playground.life_metaverse.avatar.model.AvatarInfo
import com.charmflex.playground.life_metaverse.avatar.service.AvatarService
import com.charmflex.playground.life_metaverse.exception.LMErrors
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpStatusCodeException


@RestController
@RequestMapping("/api/avatars")
class AvatarRestController(
    private val broadcaster: SimpMessagingTemplate,
    private val avatarService: AvatarService
) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody avatarInfo: AvatarInfo
    ) {
        try {
            avatarService.add(avatarInfo)
            broadcaster.convertAndSend(Constant.BroadCast.TOPIC_CREATE_AVATAR, avatarInfo)
        } catch (e: Exception) {
            throw LMErrors.InvalidAvatar
        }
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<AvatarInfo> {
        try {
            return avatarService.avatar
        } catch (e: Exception) {
            throw LMErrors.InvalidAvatar
        }
    }
}
package com.charmflex.playground.life_metaverse.avatar.controller

import com.charmflex.playground.life_metaverse.avatar.model.AvatarInfo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class AvatarController {
    // Client sends to: /app/avatar.update
    // Server broadcasts to: /topic/avatars
    @MessageMapping("/avatar.create")
    @SendTo("/topic/avatars")
    fun createAvatar(avatar: String): String {
        println("Received avatar creation")
        return avatar // broadcast to all subscribers
    }
}
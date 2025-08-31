package com.charmflex.playground.life_metaverse.session.handler
import com.charmflex.playground.life_metaverse.avatar.Constant
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.user.SimpUserRegistry
import org.springframework.stereotype.Service

@Service
class TopicPublisher(
    private val simpUserRegistry: SimpUserRegistry,
    private val simpleMessagingTemplate: SimpMessagingTemplate
) {
    fun publishConnectedUserCount() {
        simpleMessagingTemplate.convertAndSend(Constant.BroadCast.TOPIC_SESSION_METADATA, mapOf("onlineCount" to simpUserRegistry.userCount))
    }
}
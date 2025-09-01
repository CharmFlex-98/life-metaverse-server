package com.charmflex.playground.life_metaverse.session.handler
import com.charmflex.playground.life_metaverse.avatar.Constant
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.user.SimpUserRegistry
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class TopicPublisher(
    private val simpUserRegistry: SimpUserRegistry,
    private val simpleMessagingTemplate: SimpMessagingTemplate
) {

    @Scheduled(fixedRate = 5_000)
    fun publishConnectedUserCount() {
        simpleMessagingTemplate.convertAndSend(Constant.BroadCast.TOPIC_SESSION_METADATA, mapOf("onlineCount" to simpUserRegistry.userCount))
    }
}
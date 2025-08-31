package com.charmflex.playground.life_metaverse.session.handler
import com.charmflex.playground.life_metaverse.avatar.Constant
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Service
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent
import java.util.*


@Service
class BroadcastEventListener(
    private val topicListeners: List<TopicListener>
) {
    @EventListener
    fun onConnected(event: SessionConnectEvent) {
        onListen(Constant.BroadCast.TOPIC_SESSION_METADATA)
    }

    @EventListener
    fun onDisconnected(event: SessionDisconnectEvent) {
        onListen(Constant.BroadCast.TOPIC_SESSION_METADATA)
    }

    @EventListener
    fun onUnsubscribed(event: SessionUnsubscribeEvent) {
        val destination = StompHeaderAccessor.wrap(event.message).destination
        destination?.let { onListen(it) }
    }

    @EventListener
    fun onSubscribed(event: SessionSubscribeEvent) {
        val destination = StompHeaderAccessor.wrap(event.message).destination
        destination?.let {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    onListen(it)
                }
            }, 150)
        }
    }

    private fun onListen(destination: String) {
        topicListeners.forEach {
            if (it.topic() == destination) {
                it.onListen()
            }
        }
    }
}
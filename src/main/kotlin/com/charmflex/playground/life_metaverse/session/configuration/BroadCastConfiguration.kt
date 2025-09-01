package com.charmflex.playground.life_metaverse.session.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.security.Principal
import java.util.*

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(
    @Value("\${web.allowed-origins}") private val allowedOrigins: String
) : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry
            .enableSimpleBroker("/topic")
            .setHeartbeatValue(longArrayOf(10000, 10000))
        registry.setApplicationDestinationPrefixes("/app") // for client -> server
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws-avatar")
            .setHandshakeHandler(handshakeHandler())
            .setAllowedOriginPatterns(allowedOrigins) // allow all for now
//            .withSockJS() // fallback for browsers
    }

    @Bean
    fun handshakeHandler(): DefaultHandshakeHandler {
        return object : DefaultHandshakeHandler() {
            override fun determineUser(
                request: ServerHttpRequest,
                wsHandler: WebSocketHandler,
                attributes: MutableMap<String, Any>
            ): Principal {
                return Principal { UUID.randomUUID().toString() }
            }
        }
    }
}
package com.charmflex.playground.life_metaverse.session.configuration

import com.charmflex.playground.life_metaverse.session.handler.BroadcastEventListener
import com.charmflex.playground.life_metaverse.session.handler.SessionMetaDataTopicListener
import com.charmflex.playground.life_metaverse.session.handler.TopicListener
import com.charmflex.playground.life_metaverse.session.handler.TopicPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SessionConfiguration(
    private val topicPublisher: TopicPublisher
) {

    @Bean
    fun topicListeners(): List<TopicListener> {
        return listOf(
            SessionMetaDataTopicListener(topicPublisher)
        )
    }
}
package com.charmflex.playground.life_metaverse.session.handler

import com.charmflex.playground.life_metaverse.avatar.Constant

interface TopicListener {
    fun topic(): String
    fun onListen()
}

class SessionMetaDataTopicListener(
    private val topicPublisher: TopicPublisher
) : TopicListener {
    override fun topic(): String {
        return Constant.BroadCast.TOPIC_SESSION_METADATA
    }

    override fun onListen() {
        topicPublisher.publishConnectedUserCount()
    }
}
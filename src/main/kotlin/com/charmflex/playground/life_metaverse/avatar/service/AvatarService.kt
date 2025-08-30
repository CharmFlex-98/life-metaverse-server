package com.charmflex.playground.life_metaverse.avatar.service

import com.charmflex.playground.life_metaverse.avatar.model.AvatarInfo
import org.springframework.stereotype.Service

@Service
class AvatarService {
    private val _avatars: MutableList<AvatarInfo> = mutableListOf()
    val avatar: List<AvatarInfo> = _avatars

    fun add(avatarInfo: AvatarInfo) {
        _avatars.add(avatarInfo)
    }
}
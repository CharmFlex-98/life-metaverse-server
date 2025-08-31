package com.charmflex.playground.life_metaverse.avatar.service

import com.charmflex.playground.life_metaverse.avatar.model.AvatarInfo
import com.charmflex.playground.life_metaverse.exception.LMException
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class AvatarService {
    private val _avatars: ConcurrentHashMap<String, AvatarInfo> = ConcurrentHashMap()
    val avatar: Map<String, AvatarInfo> = _avatars

    fun add(avatarInfo: AvatarInfo) {
        if (isNotValid(avatarInfo)) throw LMException.InvalidAvatar
        if (isDuplicated(avatarInfo)) throw LMException.DuplicatedAvatar

        _avatars[avatarInfo.name] = avatarInfo
    }

    private fun isDuplicated(avatarInfo: AvatarInfo): Boolean {
        return _avatars.containsKey(avatarInfo.name)
    }

    private fun isNotValid(avatarInfo: AvatarInfo): Boolean {
        return avatarInfo.name.isBlank()
    }
}
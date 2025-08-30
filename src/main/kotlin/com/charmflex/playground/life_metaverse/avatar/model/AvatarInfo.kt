package com.charmflex.playground.life_metaverse.avatar.model

data class AvatarInfo(
    val name: String,
    val parts: HashMap<String, AvatarPart>
) {
    data class AvatarPart(
        val id: Long
    )
}
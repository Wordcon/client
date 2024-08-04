package com.wordcon.client.core.network.entities

data class GameInfo(
    val type: String,
    val playersIn: Int,
    val maxPlayers: Int,
    val status: String,
    val id: String
)
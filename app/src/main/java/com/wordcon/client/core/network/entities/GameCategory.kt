package com.wordcon.client.core.network.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GameCategory(
    val id: Long,
    // val name: String,
    @StringRes val name: Int,
    // val image: String
    @DrawableRes val image: Int
)

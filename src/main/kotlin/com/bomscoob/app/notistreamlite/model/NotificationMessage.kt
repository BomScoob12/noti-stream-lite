package com.bomscoob.app.notistreamlite.model

data class NotificationMessage(
    val fromUser: String,
    val toUser: String,
    val type: String,
    val timestamp: String
)


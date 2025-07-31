package com.bomscoob.app.notistreamlite.model.request

data class NotificationActionRequest(
    val fromUser: String,
    val toUser: String,
    val content: String,
    val type: String,
)
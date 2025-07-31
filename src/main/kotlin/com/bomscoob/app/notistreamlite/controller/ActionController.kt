package com.bomscoob.app.notistreamlite.controller

import com.bomscoob.app.notistreamlite.model.ActionType
import com.bomscoob.app.notistreamlite.model.request.NotificationActionRequest
import com.bomscoob.app.notistreamlite.publisher.NotificationPublisher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/actions")
class ActionController(
    private val publisher: NotificationPublisher
) {

    @PostMapping("/like")
    fun likeAction(@RequestBody request: NotificationActionRequest) {
        publisher.sendToWeb(request, ActionType.LIKE)
    }

    @PostMapping("/comment")
    fun commentAction(@RequestBody request: NotificationActionRequest) {
        publisher.sendToWeb(request, ActionType.COMMENT)
    }

    @PostMapping("/mention")
    fun followAction(@RequestBody request: NotificationActionRequest) {
        publisher.sendToWeb(request, ActionType.MENTION)
    }

    @PostMapping("/invite")
    fun inviteAction(@RequestBody request: NotificationActionRequest) {
        publisher.sendToWeb(request, ActionType.INVITE)
        publisher.sendToEmail(request, ActionType.INVITE)
    }
}

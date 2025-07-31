package com.bomscoob.app.notistreamlite.controller

import com.bomscoob.app.notistreamlite.publisher.NotificationPublisher
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/actions")
class ActionController(
    private val publisher: NotificationPublisher
) {

}

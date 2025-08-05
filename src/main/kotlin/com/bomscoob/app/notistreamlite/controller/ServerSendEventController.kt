package com.bomscoob.app.notistreamlite.controller

import com.bomscoob.app.notistreamlite.service.SsePushService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/v1/sse")
// This controller is intended to handle Server-Sent Events (SSE) for real-time notifications
class ServerSendEventController(
    private val ssePushService: SsePushService
) {
    @GetMapping("/subscribe")
    fun subscribe(@RequestParam email: String): SseEmitter {
        return ssePushService.subscribe(email)
    }
}
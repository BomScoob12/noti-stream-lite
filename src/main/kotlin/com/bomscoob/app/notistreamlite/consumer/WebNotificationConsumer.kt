package com.bomscoob.app.notistreamlite.consumer

import com.bomscoob.app.notistreamlite.config.RabbitMQConfig
import com.bomscoob.app.notistreamlite.model.NotificationMessage
import com.bomscoob.app.notistreamlite.service.SsePushService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class WebNotificationConsumer(
    private val ssePushService: SsePushService
) {
    @RabbitListener(queues = [RabbitMQConfig.WEB_QUEUE_NAME])
    fun receiveWebNotification(message: NotificationMessage) {
        println(
            "Received Web Notification from ${message.fromUser}, to ${message.toUser}, " +
            "content: ${message.content}, timestamp: ${message.timestamp}"
        )
        ssePushService.sendNotificationToUser(message)
    }
}

package com.bomscoob.app.notistreamlite.publisher

import com.bomscoob.app.notistreamlite.config.RabbitMQConfig
import com.bomscoob.app.notistreamlite.model.NotificationMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class NotificationPublisher(
    private val rabbitTemplate: RabbitTemplate
) {
    companion object {
        private const val EMAIL_ROUTE_NAME = "noti.email"
        private const val WEB_ROUTE_NAME = "noti.web"
    }

    fun sendToEmail(message: NotificationMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, EMAIL_ROUTE_NAME, message)
    }

    fun sendToWeb(message: NotificationMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, WEB_ROUTE_NAME, message)
    }
}

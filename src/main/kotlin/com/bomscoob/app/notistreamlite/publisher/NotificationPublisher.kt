package com.bomscoob.app.notistreamlite.publisher

import com.bomscoob.app.notistreamlite.config.RabbitMQConfig
import com.bomscoob.app.notistreamlite.model.ActionType
import com.bomscoob.app.notistreamlite.model.NotificationMessage
import com.bomscoob.app.notistreamlite.model.request.NotificationActionRequest
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Service class for publishing notifications to different channels (Email, Web).
 */
@Service
class NotificationPublisher(
    private val rabbitTemplate: RabbitTemplate
) {
    companion object {
        private const val EMAIL_ROUTE_NAME = "noti.email"
        private const val WEB_ROUTE_NAME = "noti.web"
    }

    /**
     * Publishes a notification message to the email queue.
     * @param request the notification action request
     */
    fun sendToEmail(request: NotificationActionRequest, type: ActionType) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            EMAIL_ROUTE_NAME,
            buildNotificationMessage(request, type)
        )
    }

    /**
     * Publishes a notification message to the web queue.
     * @param request the notification action request
     */
    fun sendToWeb(request: NotificationActionRequest, type: ActionType) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            WEB_ROUTE_NAME,
            buildNotificationMessage(request, type)
        )
    }

    /**
     * Builds a NotificationMessage from a NotificationActionRequest.
     * @param request the notification action request
     * @return NotificationMessage
     */
    private fun buildNotificationMessage(request: NotificationActionRequest, type: ActionType): NotificationMessage {
        return NotificationMessage(
            fromUser = request.fromUser,
            toUser = request.toUser,
            type = type.toString(),
            content = request.content,
            timestamp = LocalDateTime.now().toString()
        )
    }
}

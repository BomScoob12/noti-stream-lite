package com.bomscoob.app.notistreamlite.consumer

import com.bomscoob.app.notistreamlite.config.RabbitMQConfig
import com.bomscoob.app.notistreamlite.model.NotificationMessage
import com.bomscoob.app.notistreamlite.service.EmailService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class EmailNotificationConsumer(
    private val emailService: EmailService,
) {

    @RabbitListener(queues = [RabbitMQConfig.EMAIL_QUEUE_NAME])
    fun receiveEmailNotification(message: NotificationMessage) {
        println(
            "Received Email Notification \nfrom ${message.fromUser}, to ${message.toUser}, " +
            "content: ${message.content}, timestamp: ${message.timestamp}"
        )
        emailService.sendEmail(message.fromUser, message.toUser, message.content, message.content)
    }
}


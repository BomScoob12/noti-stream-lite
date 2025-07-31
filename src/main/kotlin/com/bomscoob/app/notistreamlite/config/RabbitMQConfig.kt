package com.bomscoob.app.notistreamlite.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object {
        const val EXCHANGE_NAME = "noti.exchange"
        const val EMAIL_QUEUE_NAME = "noti.email.queue"
        const val WEB_QUEUE_NAME = "noti.web.queue"
    }

    @Bean
    fun topicExchange(): TopicExchange = TopicExchange(EXCHANGE_NAME)

    @Bean
    fun emailQueue(): Queue = Queue(EMAIL_QUEUE_NAME)

    @Bean
    fun webQueue(): Queue = Queue(WEB_QUEUE_NAME)

    @Bean
    fun emailBinding(): Binding = BindingBuilder.bind(emailQueue())
        .to(topicExchange()).with("noti.email")

    @Bean
    fun webBinding(): Binding = BindingBuilder.bind(webQueue())
        .to(topicExchange()).with("noti.web")
}
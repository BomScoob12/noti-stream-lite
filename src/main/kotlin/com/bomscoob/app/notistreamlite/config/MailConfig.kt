package com.bomscoob.app.notistreamlite.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig {

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "sandbox.smtp.mailtrap.io"
        mailSender.port = 2525
        mailSender.username = "52910b17704bad"
        mailSender.password = "5a2b8fe765744a"

        val props = mailSender.javaMailProperties
        props["mail.smtp.auth"] = "true"
        return mailSender
    }
}
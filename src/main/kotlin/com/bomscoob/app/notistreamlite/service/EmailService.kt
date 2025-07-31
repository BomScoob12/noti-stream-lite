package com.bomscoob.app.notistreamlite.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService {
    private val mailSender: JavaMailSender = JavaMailSenderImpl()

    fun sendEmail(from: String, to: String, subject: String, text: String) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(from)
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(text, true)
        mailSender.send(message)
    }
}
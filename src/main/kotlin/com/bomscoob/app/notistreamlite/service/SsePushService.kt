package com.bomscoob.app.notistreamlite.service

import com.bomscoob.app.notistreamlite.model.NotificationMessage
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap

@Service
class SsePushService {
    private val emitters: MutableMap<String, SseEmitter> = ConcurrentHashMap()

    fun subscribe(clientId: String): SseEmitter {
        val emitter = SseEmitter(Long.MAX_VALUE)
        emitters[clientId] = emitter
        emitter.onCompletion { emitters.remove(clientId) }
        emitter.onTimeout { emitters.remove(clientId) }
        return emitter
    }

    fun sendNotificationToUser(message: NotificationMessage) {
        val deadEmitters = ArrayList<String>()
        emitters.forEach { (clientEmail, emitter) ->
            // only send to the intended recipient
            if (clientEmail == message.toUser) {
                try {
                    emitter.send(message)
                } catch (ex: Exception) {
                    emitter.completeWithError(ex)
                    deadEmitters.add(clientEmail)
                }
            }
        }
        deadEmitters.forEach { clientEmail ->
            emitters.remove(clientEmail)
        }
    }
}
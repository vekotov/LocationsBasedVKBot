package eventConsumers

import User
import com.petersamokhin.vksdk.core.model.event.MessageNew

interface MessageEventConsumer {
    fun consumeEvent(event : MessageNew, user : User)
}
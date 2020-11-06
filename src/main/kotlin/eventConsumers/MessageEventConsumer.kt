package eventConsumers

import com.petersamokhin.vksdk.core.model.event.MessageNew
import user.User

interface MessageEventConsumer {
    fun consumeEvent(event : MessageNew, user : User)
}
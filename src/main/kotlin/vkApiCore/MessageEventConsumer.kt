package vkApiCore

import com.petersamokhin.vksdk.core.model.event.MessageNew
import eventConsumers.Locations
import user.Database
import user.User

object MessageEventConsumer {
    fun consumeEvent(event: MessageNew) {
        val userId = event.message.fromId
        if (!Database.isUserExist(userId)) {
            Locations.NewbieMessage.consumeEvent(event, User())
            return
        }
        val user = Database.loadUser(userId)!!
        user.location.consumeEvent(event, user)
    }
}
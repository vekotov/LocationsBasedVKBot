package eventConsumers

import User
import vkApiCore.LongPoller
import com.petersamokhin.vksdk.core.model.event.MessageNew

object NewbieMessageConsumer : MessageEventConsumer{
    override fun consumeEvent(event: MessageNew, user: User) {
        LongPoller.client.sendMessage {
            peerId = event.message.peerId
            message = "Добро пожаловать в игрового бота C10DA.\n" +
                    "Данная игра является симулятором постапокалипсиса.\n" +
                    "Для начала вы, как любой другой крутой выживший, придумать себе прозвище и ввести его:"
        }.execute()
        user.id = event.message.fromId
        user.hydration = 50
        user.saturation = 50
        user.energy = 50
        user.isBlocked = false
        user.name = ""
        user.location = Locations.EnteringNickname
        Database.saveUser(user)
    }
}
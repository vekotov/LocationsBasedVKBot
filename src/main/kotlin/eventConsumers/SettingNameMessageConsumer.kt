package eventConsumers

import com.petersamokhin.vksdk.core.model.event.MessageNew
import helpers.Helpers
import user.Database
import user.User
import vkApiCore.LongPoller

object SettingNameMessageConsumer : MessageEventConsumer{
    override fun consumeEvent(event: MessageNew, user: User) {
        val text = event.message.text
        if(text.length > 50){
            LongPoller.client.sendMessage {
                peerId = event.message.peerId
                message = "Данной прозвище слишком длинное, такое люди не запомнят. Максимальная длина - 50 символов."
            }.execute()
            return
        }
        if(text.length < 5){
            LongPoller.client.sendMessage {
                peerId = event.message.peerId
                message = "Данной прозвище слишком короткое, вас будут путать с другими. Минимальная длина - 5 символов."
            }.execute()
            return
        }
        user.name = Helpers.sanitizeNickname(text)
        Database.saveUser(user)
        TODO("Добавить здесь вывод главного меню.")
    }
}
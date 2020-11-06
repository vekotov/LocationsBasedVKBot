package eventConsumers

import com.petersamokhin.vksdk.core.model.event.MessageNew
import user.User

enum class Locations {
    NewbieMessage {
        override fun consumeEvent(event: MessageNew, user: User) {
            NewbieMessageConsumer.consumeEvent(event, user)
        }
    },

    EnteringNickname {
        override fun consumeEvent(event: MessageNew, user: User) {
            SettingNameMessageConsumer.consumeEvent(event, user)
        }
    },

    MainMenu {
        override fun consumeEvent(event: MessageNew, user: User) {
            MainMenuConsumer.consumeEvent(event, user)
        }
    };

    abstract fun consumeEvent(event : MessageNew, user: User)
}
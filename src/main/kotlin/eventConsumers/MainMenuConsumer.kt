package eventConsumers

import com.petersamokhin.vksdk.core.model.event.MessageNew
import user.User
import vkApiCore.LongPoller

object MainMenuConsumer : MessageEventConsumer {
    override fun consumeEvent(event: MessageNew, user: User) {
        val payload = event.message.payload ?: ""
        when (payload) {
            "looting_button" -> sendLootingMenu(event, user)
            "my_safehouse" -> sendSafehouseMenu(event, user)
            "my_inventory" -> sendInventoryMenu(event, user)
            "authors" -> sendAuthors(event, user)
            else -> sendErrorInput(event, user)
        }
    }

    private fun sendLootingMenu(event: MessageNew, user: User) {

    }

    private fun sendSafehouseMenu(event: MessageNew, user: User) {

    }

    private fun sendInventoryMenu(event: MessageNew, user: User) {

    }

    private fun sendAuthors(event: MessageNew, user: User) {

    }

    private fun sendErrorInput(event: MessageNew, user: User) {
        LongPoller.client.sendMessage {
            peerId = event.message.peerId

        }
    }
}
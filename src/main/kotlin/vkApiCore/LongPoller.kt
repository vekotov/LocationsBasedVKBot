package vkApiCore

import com.petersamokhin.vksdk.core.client.VkApiClient

object LongPoller {
    lateinit var client : VkApiClient
    fun activate(){
        client = VkApiClientBuilder.getVkApiClient()
        print("GOT VK API CLIENT\n")
        client.onMessage { messageEvent ->
            print(messageEvent.toString() + "\n")
            MessageEventConsumer.consumeEvent(messageEvent)
        }
        print("STARTED LONG POLLING\n")
        client.startLongPolling()
    }
}
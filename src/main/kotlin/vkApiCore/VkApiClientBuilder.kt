package vkApiCore

import com.petersamokhin.vksdk.core.client.VkApiClient
import com.petersamokhin.vksdk.core.model.VkSettings
import com.petersamokhin.vksdk.http.VkOkHttpClient

object VkApiClientBuilder {
    fun getVkApiClient(): VkApiClient {
        val vkHttpClient = VkOkHttpClient()
        return VkApiClient(
            Config.getGroupId(),
            Config.getApiKey(),
            VkApiClient.Type.Community,
            VkSettings(vkHttpClient)
        )
    }
}
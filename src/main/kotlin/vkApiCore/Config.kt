package vkApiCore

import com.natpryce.konfig.*
import java.io.File

object Config {
    private var config: Configuration? = null
    private val apiKey = Key("api.key", stringType)
    private val groupId = Key("api.groupid", intType)

    private fun load() {
        config = ConfigurationProperties.systemProperties() overriding
                EnvironmentVariables() overriding
                ConfigurationProperties.fromFile(File("config.cfg")) overriding
                ConfigurationProperties.fromResource("config.cfg")
        print("API KEY = ${config!![apiKey]}\nGROUP ID = ${config!![groupId]}\n")
    }

    fun getApiKey(): String {
        return if (config != null) config!![apiKey]
        else {
            load()
            config!![apiKey]
        }
    }

    fun getGroupId(): Int {
        return if (config != null) config!![groupId]
        else {
            load()
            config!![groupId]
        }
    }
}
import vkApiCore.LongPoller

fun main() {
    Database.initialize()
    print("DATABASE IS INITIALIZED")
    LongPoller.activate()
}
package helpers

import User
import com.petersamokhin.vksdk.core.model.objects.keyboard
import vkApiCore.LongPoller

object MenuPrinter {
    fun printMainMenu(peerId : Int, user : User){
        LongPoller.client.sendMessage {
            this.peerId = peerId
            this.message = "Добро пожаловать, ${user.name}. Ты находишься на столе планирования. " +
                    "Реши, что ты будешь делать.\n" +
                    "Ваше состояние: \n" +
                    "${Helpers.getSaturationState(user.saturation)}\n" +
                    "${Helpers.getHydrationState(user.hydration)}\n" +
                    Helpers.getEnergyState(user.energy);

            keyboard = keyboard(oneTime = false) {
                row {
                    primaryButton("Искать лут","looting_button")
                    secondaryButton("Мой лагерь", "my_safehouse")
                }
                row {
                    positiveButton("Мой инвентарь", "my_inventory")
                }
                row {
                    positiveButton("Об авторах", "authors")
                }
            }
        }
    }
}
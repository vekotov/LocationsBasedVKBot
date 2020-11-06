package helpers

object Helpers {
    fun sanitizeNickname(nickname : String) : String{
        nickname
            .replace("[", "")
            .replace("(", "")
            .replace("{", "")
            .replace("]", "")
            .replace(")", "")
            .replace("}", "")
            .replace(";", "")
        return nickname
    }

    fun getSaturationState(saturation : Int) : String{
        return when(saturation){
            in 0..30 -> "Смертельно голодны"
            in 31..50 -> "Очень голодны"
            in 51..70 -> "Голодны"
            in 71..85 -> "Проголодались"
            in 86..100 -> "Сыты"
            else -> "ОШИБКА. СООБЩИТЕ РАЗРАБОТЧИКУ"
        }
    }

    fun getHydrationState(hydration : Int) : String{
        return when(hydration){
            in 0..30 -> "Смертельно высохли"
            in 31..50 -> "Очень хотите пить"
            in 51..70 -> "Хотите пить"
            in 71..85 -> "У вас легкая жажда"
            in 86..100 -> "Вы напились"
            else -> "ОШИБКА. СООБЩИТЕ РАЗРАБОТЧИКУ"
        }
    }

    fun getEnergyState(energy : Int) : String{
        return when(energy){
            in 0..30 -> "Теряете сознание от недосыпа"
            in 31..50 -> "Очень хотите спать"
            in 51..70 -> "Хотите спать"
            in 71..85 -> "Слегка зеваете"
            in 86..100 -> "Бодры"
            else -> "ОШИБКА. СООБЩИТЕ РАЗРАБОТЧИКУ"
        }
    }
}
package items

import user.User

open class BasicFoodItem(
    override val id: Int = -1,
    override val name: String = "Абстрактная еда",
    override val description: String = "Если вы нашли данный предмет - поделитесь с разработчиком. Есть не рекомендую.",
    override var count: Int = 1,
    val hydrationPower: Int = 0,
    val saturationPower: Int = 0,
    val energyPower: Int = 0
) : Item() {
    override fun useItem(user: User): Map<String, Int>? {
        user.addHydration(hydrationPower)
        user.addSaturation(saturationPower)
        user.addEnergy(energyPower)
        count--
        if(count <= 0) user.inventory.remove(id)
        return null
    }
}
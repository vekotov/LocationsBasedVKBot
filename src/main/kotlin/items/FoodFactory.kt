package items

object FoodFactory {
    fun addItems(itemsList : MutableMap<Int, Item>){
        itemsList[1] = BasicFoodItem(
            id = 1,
            name = "Пачка чипсов",
            description = "Обычная пачка чипсов. Вкус: луково-сметанный крем. 100 грамм.",
            saturationPower = 20,
            hydrationPower = -5
        )
        itemsList[2] = BasicFoodItem(
            id = 2,
            name = "Бутылка воды",
            description = "Бутылка чистой питьевой воды. Негазированная.",
            hydrationPower = 45
        )
        itemsList[3] = BasicFoodItem(
            id = 3,
            name = "Энергетик",
            description = "Энергетик в жестяной банке. Содержит таурин.",
            saturationPower = 5,
            hydrationPower = 10,
            energyPower = 25
        )
    }
}
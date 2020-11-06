package items

object ItemFactory {
    private var itemsId : MutableMap<Int, Item> = mutableMapOf()
    init {
        FoodFactory.addItems(itemsId)
    }
    fun getItem(id: Int): Item? {
        return if(itemsId.containsKey(id)) itemsId[id] else null
    }
}
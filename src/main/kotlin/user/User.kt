package user

import eventConsumers.Locations
import items.Item

class User {
    var id: Int = 0
    var name: String = "ERROR"
    var saturation: Int = 0
    var hydration: Int = 0
    var energy: Int = 0
    var x: Int = 0
    var y: Int = 0
    var inventory: MutableMap<Int, Item> = mutableMapOf()
    var isBlocked: Boolean = false
    var location: Locations = Locations.NewbieMessage

    fun addSaturation(howMuch: Int) {
        saturation += howMuch
        if (saturation > 100) saturation = 100
        if (saturation < 0) saturation = 0
    }

    fun addHydration(howMuch: Int) {
        hydration += howMuch
        if (hydration > 100) hydration = 100
        if (hydration < 0) hydration = 0
    }

    fun addEnergy(howMuch: Int){
        energy += howMuch
        if(energy > 100) energy = 100
        if(energy < 0) energy = 0
    }
}
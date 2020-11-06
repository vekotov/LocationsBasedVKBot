package items

import User

abstract class Item {
    abstract val id : Int
    abstract val name : String
    abstract val description : String
    abstract var count : Int
    abstract fun useItem(user : User) : Map<String, Int>?
}
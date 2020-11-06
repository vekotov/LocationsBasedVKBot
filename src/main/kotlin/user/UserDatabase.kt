package user

import eventConsumers.Locations
import items.Item
import items.ItemFactory
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import user.Inventories.count
import user.Inventories.itemId
import user.Users.hydration
import user.Users.isBlocked
import user.Users.location
import user.Users.name
import user.Users.saturation
import user.Users.x
import user.Users.y

object Database {
    fun initialize() {
        Database.connect("jdbc:sqlite:data.db", driver = "org.sqlite.JDBC")
        transaction {
            SchemaUtils.create(Users)
            SchemaUtils.create(Inventories)
            Users.update({ isBlocked eq true }) {
                it[isBlocked] = false
            }
        }
    }

    fun saveUser(user: User) {
        transaction {
            if (!isUserExist(user.id))
                Users.insert {
                    it[id] = user.id
                }
            Users.update({ Users.id eq user.id }) {
                it[name] = user.name
                it[saturation] = user.saturation
                it[hydration] = user.hydration
                it[isBlocked] = user.isBlocked
                it[location] = user.location.ordinal
                it[x] = user.x
                it[y] = user.y
            }
            commit()
        }
        saveInventory(user.id, user.inventory)
    }

    fun loadUser(id: Int): User? {
        if (isUserExist(id)) {
            val user = User()
            transaction {
                Users.select { Users.id eq id }.forEach {
                    user.id = id
                    user.name = it[name]
                    user.saturation = it[saturation]
                    user.hydration = it[hydration]
                    user.isBlocked = it[isBlocked]
                    user.location = Locations.values()[it[location]]
                    user.x = it[x]
                    user.y = it[y]
                }
            }
            user.inventory = loadInventory(id)
            return user
        } else {
            return null
        }
    }

    private fun saveInventory(userId: Int, items: Map<Int, Item>) {
        transaction {
            Inventories.deleteWhere { Inventories.ownerId eq userId }
            items.forEach { (_, item) ->
                Inventories.insert {
                    it[ownerId] = userId
                    it[itemId] = item.id
                    it[count] = item.count
                }
            }
        }
    }

    private fun loadInventory(userId: Int): MutableMap<Int, Item> {
        val inventory = mutableMapOf<Int, Item>()
        transaction {
            Inventories.select { Inventories.ownerId eq userId }.forEach {
                val item = ItemFactory.getItem(it[itemId]) ?: return@forEach
                item.count = it[count]
                inventory[item.id] = item
            }
        }
        return inventory
    }

    fun isUserExist(userId: Int): Boolean {
        var count = 0L
        transaction {
            count = Users.select { Users.id eq userId }.count()
        }
        return count > 0
    }
}


object Users : Table() {
    val id = integer("id")
    val name = varchar("name", 50)
    val saturation = integer("saturation")
    val hydration = integer("hydration")
    val isBlocked = bool("isBlocked")
    val location = integer("location")
    val x = integer("x")
    val y = integer("y")
}

object Inventories : Table() {
    val ownerId = integer("id")
    val itemId = integer("itemId")
    val count = integer("count")
}
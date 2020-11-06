import Inventories.count
import Inventories.itemId
import items.Item
import items.ItemFactory
import Users.hydration
import Users.isBlocked
import Users.location
import Users.name
import Users.saturation
import eventConsumers.Locations
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

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
                    it[name] = user.name
                    it[saturation] = user.saturation
                    it[hydration] = user.hydration
                    it[isBlocked] = user.isBlocked
                    it[location] = user.location.ordinal
                }
            else
                Users.update({ Users.id eq user.id }) {
                    it[name] = user.name
                    it[saturation] = user.saturation
                    it[hydration] = user.hydration
                    it[isBlocked] = user.isBlocked
                    it[location] = user.location.ordinal
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
}

object Inventories : Table() {
    val ownerId = integer("id")
    val itemId = integer("itemId")
    val count = integer("count")
}
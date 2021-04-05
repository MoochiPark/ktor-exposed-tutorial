package io.wisoft.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object OrderItems : LongIdTable() {
    val name = text("item_name")
    val amount = long("amount")
    val price = long("price")
    val order = reference("order_id", Orders)
}

class OrderItem(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<OrderItem>(OrderItems)

    var name by OrderItems.name
    var amount by OrderItems.amount
    var price by OrderItems.price
    var order by OrderItem referencedOn OrderItems.order
}

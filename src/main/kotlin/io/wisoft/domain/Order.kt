package io.wisoft.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object Orders : LongIdTable() {
    val status = text("status")
    val orderDate = datetime("order_date").default(LocalDateTime.now())
    val customer = reference("order_customer", Customers)
}

class Order(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Order>(Customers)

    var status by Orders.status
    var orderDate by Orders.orderDate
    val customer by Customer referrersOn Orders.customer
    val items by OrderItem referrersOn OrderItems.order
}

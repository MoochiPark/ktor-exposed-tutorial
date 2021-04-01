package io.wisoft.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

// Table schema
object Customers : LongIdTable() {
    val firstName = text("first_name")
    val lastName = text("last_name")
    val email = text("email")
}

// Entity
class Customer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Customer>(Customers)

    var firstName by Customers.firstName
    var lastName by Customers.lastName
    var email by Customers.email
}
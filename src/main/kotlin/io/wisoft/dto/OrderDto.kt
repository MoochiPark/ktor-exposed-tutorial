package io.wisoft.dto

import io.wisoft.domain.Customer
import io.wisoft.domain.Customers
import io.wisoft.domain.Orders.default
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

@Serializable
data class OrderRequest(
    val status: String,
)

//@Serializable
//data class CustomerRequest(
//    val firstName: String,
//    val lastName: String,
//    val email: String
//)
//
//@Serializable
//data class CustomerResponse(
//    val id: Long,
//    val firstName: String,
//    val lastName: String,
//    val email: String
//) {
//    companion object {
//        fun of(customer: Customer) =
//            CustomerResponse(
//                id = customer.id.value,
//                firstName = customer.firstName,
//                lastName = customer.lastName,
//                email = customer.email
//            )
//    }
//}

//object Orders : LongIdTable() {
//    val status = text("status")
//    val orderDate = datetime("order_date").default(LocalDateTime.now())
//    val customer = reference("order_customer", Customers)
//}
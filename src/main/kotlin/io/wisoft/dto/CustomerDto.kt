package io.wisoft.dto

import io.wisoft.domain.Customer
import kotlinx.serialization.Serializable

@Serializable
data class CustomerRequest(
    val firstName: String,
    val lastName: String,
    val email: String
)

@Serializable
data class CustomerResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    companion object {
        fun of(customer: Customer) =
            CustomerResponse(
                id = customer.id.value,
                firstName = customer.firstName,
                lastName = customer.lastName,
                email = customer.email
            )
    }
}
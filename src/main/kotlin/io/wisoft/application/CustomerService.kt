package io.wisoft.application

import io.ktor.features.*
import io.ktor.util.*
import io.wisoft.config.query
import io.wisoft.domain.Customer
import io.wisoft.dto.CustomerRequest
import io.wisoft.dto.CustomerResponse

@KtorExperimentalAPI
class CustomerService {
    suspend fun getAll() = query {
        Customer.all().map(CustomerResponse::of).toList()
    }

    suspend fun getById(id: Long) = query {
        Customer.findById(id)?.let { CustomerResponse.of(it) }
            ?: throw NotFoundException()
    }

    suspend fun new(request: CustomerRequest) = query {
        Customer.new {
            firstName = request.firstName
            lastName = request.lastName
            email = request.email
        }
    }

    suspend fun renew(id: Long, request: CustomerRequest) = query {
        val customer = Customer.findById(id) ?: throw NotFoundException()
        customer.apply {
            firstName = request.firstName
            lastName = request.lastName
            email = request.email
        }
    }

    suspend fun delete(id: Long) = query {
        Customer.findById(id)?.delete() ?: throw NotFoundException()
    }
}
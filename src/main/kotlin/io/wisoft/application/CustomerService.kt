package io.wisoft.application

import io.ktor.features.*
import io.ktor.util.*
import io.wisoft.config.query
import io.wisoft.domain.Customer
import io.wisoft.domain.Customers
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
        checkDuplicate(request.email)
            .new {
                firstName = request.firstName
                lastName = request.lastName
                email = request.email
            }
    }

    suspend fun renew(id: Long, request: CustomerRequest) = query {
        val customer = Customer.findById(id) ?: throw NotFoundException()
//        checkDuplicate(request.email)
        customer.apply {
            firstName = request.firstName
            lastName = request.lastName
            email = request.email
        }
    }

    suspend fun delete(id: Long) = query {
        Customer.findById(id)?.delete() ?: throw NotFoundException()
    }

    private fun checkDuplicate(email: String) =
        Customer.takeIf {
            it.find { Customers.email eq email }.empty()
        } ?: throw BadRequestException("이미 사용중인 이메일입니다.")

}
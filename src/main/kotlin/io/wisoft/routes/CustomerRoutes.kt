package io.wisoft.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import io.wisoft.application.CustomerService
import io.wisoft.dto.CustomerRequest


@KtorExperimentalAPI
fun Routing.customer(service: CustomerService) {
    route("customers") {
        get {
            call.respond(service.getAll())
        }
        get("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: throw BadRequestException("Parameter id is null")
            call.respond(service.getById(id))
        }
        post {
            val request = call.receive<CustomerRequest>()
            service.new(request)
            call.response.status(HttpStatusCode.Created)
        }
        put("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: throw BadRequestException("Parameter id is null")
            val request = call.receive<CustomerRequest>()
            service.renew(id, request)
            call.response.status(HttpStatusCode.NoContent)
        }
        delete("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: throw BadRequestException("Parameter id is null")
            service.delete(id)
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}

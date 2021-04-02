package io.wisoft.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NoContent
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
                ?: return@get call.respondText(
                    "Parameter id is null",
                    status = BadRequest
                )
            call.respond(service.getById(id))
        }
        post {
            val request = call.receive<CustomerRequest>()
            service.new(request)
            call.respondText("Customer stored correctly", status = Created)
        }
        put("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@put call.respondText(
                    "Parameter id is null",
                    status = BadRequest
                )
            val request = call.receive<CustomerRequest>()
            service.renew(id, request)
            call.respond(NoContent)
        }
        delete("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@delete call.respondText(
                    "Parameter id is null",
                    status = BadRequest
                )
            service.delete(id)
            call.respond(Accepted)
        }
    }
}

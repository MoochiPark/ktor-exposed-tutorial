package io.wisoft

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.util.*
import io.wisoft.application.CustomerService
import io.wisoft.config.DatabaseInitializer
import io.wisoft.routes.customer

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@KtorExperimentalAPI
@JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging)
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
    }
    routing {
        customer(CustomerService())
    }
    DatabaseInitializer.init()
}
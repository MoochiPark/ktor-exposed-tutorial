package io.wisoft.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.wisoft.domain.Customers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseInitializer {
    fun init() {
        Database.connect(HikariDataSource(hikariConfig()))
        transaction {
            create(Customers)
        }
    }
}

private fun hikariConfig() =
    HikariConfig().apply {
        driverClassName = "org.postgresql.Driver"
        jdbcUrl = "jdbc:postgresql://arjuna.db.elephantsql.com:5432/liwpyxkm"
        maximumPoolSize = 3
        isAutoCommit = false
        username = "liwpyxkm"
        password = "J0x23x6ndPI7mRoXQVD0o870-GIsYcxW"
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }

suspend fun <T> query(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction {
        block()
    }
}
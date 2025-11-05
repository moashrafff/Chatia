package com.chatia.project.db

import com.chatia.project.db.table.UsersTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun Application.initDB() {
    val config = environment.config
    val url = config.property("database.url").getString()
    val user = config.property("database.user").getString()
    val driver = config.property("database.driver").getString()
    val password= System.getenv("password")
    val database= Database.connect(
        url=url,
        driver=driver,
        user=user,
        password=password
    )
    transaction(database){
        SchemaUtils.create(UsersTable,)
    }
}
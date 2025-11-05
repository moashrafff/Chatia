package com.chatia.project.db.table

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object UsersTable: IntIdTable() {
    val userName=varchar("user_name",255).uniqueIndex().default("")
    val email=varchar("email",255).default("")
    val phoneNumber=varchar("phone_number",255).default("")
    val password=varchar("password",255)
}
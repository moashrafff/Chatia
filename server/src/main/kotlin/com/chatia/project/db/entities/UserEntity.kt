package com.chatia.project.db.entities

import com.chatia.project.db.table.UsersTable
import com.chatia.project.domain.model.User
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class UserEntity(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UserEntity>(UsersTable)

    var userName by UsersTable.userName
    var password by UsersTable.password
    var email by UsersTable.email
    var phoneNumber by UsersTable.phoneNumber

    fun toUser()= User(
        id.value, userName = userName, email = email, phoneNumber = phoneNumber,
    )
}
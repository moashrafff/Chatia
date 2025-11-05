package com.chatia.project.data.datasource

import com.chatia.project.db.entities.UserEntity
import com.chatia.project.db.table.UsersTable
import com.chatia.project.db.utils.dbQuery
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User
import com.chatia.project.security.SecurityManger

class UserDataSourceImpl(
    private val securityManger: SecurityManger
) :UserDataSource{
    override suspend fun findUserByUsername(userName: String): User? =
        UserEntity.find { UsersTable.userName eq userName }.firstOrNull()?.toUser()



    override suspend fun createUser(request: RegisterRequest): User? =
        UserEntity.Companion.new {
            userName = request.userName!!
            email = request.email!!
            password = request.password!!
            phoneNumber = request.phoneNumber!!
            password = securityManger.hashPassword(request.password)
        }.let {
            User(
                id = it.id.value,
                userName = it.userName,
                email = it.email,
                phoneNumber = it.phoneNumber,
                password = securityManger.hashPassword(request.password!!)
            )
        }



    override suspend fun getUserById(id: Int): User?  = dbQuery {
        UserEntity.Companion.findById(id)?.toUser()
    }
}

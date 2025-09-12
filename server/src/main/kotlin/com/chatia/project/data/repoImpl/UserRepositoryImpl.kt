package com.chatia.project.data.repoImpl

import com.chatia.project.db.entities.UserEntity
import com.chatia.project.db.table.UsersTable
import com.chatia.project.db.utils.dbQuery
import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User
import com.chatia.project.domain.repo.UserRepository
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl: UserRepository {
    override suspend fun createUser(request: RegisterRequest): User? =
        dbQuery {
            val user =
                UserEntity.Companion.find { UsersTable.userName eq request.userName }.firstOrNull()
            return@dbQuery if (user != null) {
                user.toUser()
            } else {
                UserEntity.Companion .new {
                    userName = request.userName
                    email = request.email
                    password = request.password
                    phoneNumber = request.phoneNumber
                    password = hashPassword(request.password)
                }.let {
                    User(
                        id = it.id.value,
                        userName = it.userName,
                        email = it.email,
                        phoneNumber = it.phoneNumber
                    )
                }
            }
        }

    override suspend fun getUserById(id: Int): User? = dbQuery {
        UserEntity.Companion.findById(id)?.toUser()
    }

    override suspend fun authenticate(request: LoginRequest): User? =
        dbQuery {
            val user = UserEntity.Companion.find {
                UsersTable.userName eq request.userName
            }.firstOrNull()

            user?.takeIf { user ->
                verifyPassword(request.password, user.password)
            }?.let {
                User(
                    userName = request.userName, id = user.id.value,
                    email = it.email,
                    phoneNumber = it.phoneNumber
                )
            }
        }

    override fun hashPassword(password: String): String =
        BCrypt.hashpw(password, BCrypt.gensalt())

    override fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean =
        BCrypt.checkpw(plainPassword,hashedPassword)

}
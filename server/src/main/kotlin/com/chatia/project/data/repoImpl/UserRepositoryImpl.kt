package com.chatia.project.data.repoImpl

import com.chatia.project.data.datasource.UserDataSource
import com.chatia.project.db.utils.dbQuery
import com.chatia.project.domain.validator.UserDataValidator
import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User
import com.chatia.project.domain.repo.UserRepository
import com.chatia.project.domain.validator.ValidationResults
import com.chatia.project.security.SecurityManger


class UserRepositoryImpl(private val securityManger: SecurityManger,
    private val userDataSource: UserDataSource
): UserRepository {

    override suspend fun createUser(request: RegisterRequest): User? =
        dbQuery {

            val validationResult = UserDataValidator.validateUserData(
                email = request.email,
                password = request.password,
                username = request.userName
            )
            if(validationResult!= ValidationResults.VALID_USER){
                throw IllegalArgumentException(validationResult.message)
            }

            val user =userDataSource.findUserByUsername(request.userName!!)

            return@dbQuery user ?: userDataSource.createUser(request)
        }


    override suspend fun authenticate(request: LoginRequest): User? =
        dbQuery {
            val validationResult = UserDataValidator.validateLoginRequest(
                password = request.password,
                username = request.userName
            )

            if(validationResult!= ValidationResults.VALID_USER){
                throw IllegalArgumentException(validationResult.message)
            }

           val user = userDataSource.findUserByUsername(userName = request.userName!!)
            if(user == null){
                throw IllegalArgumentException("User not found")
            }

            if(securityManger.verifyPassword(request.password!!,user.password!!).not()){
                throw IllegalArgumentException("Invalid password")
            }
                return@dbQuery User(
                    userName = request.userName, id = user.id,
                    email = user.email,
                    phoneNumber = user.phoneNumber,
                    password = user.password
                )
            }

    override suspend fun getUserById(id: Int): User? =
        userDataSource.getUserById(id)
}
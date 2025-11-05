package com.chatia.project.domain.repo

import com.chatia.project.data.datasource.UserDataSource
import com.chatia.project.data.repoImpl.UserRepositoryImpl
import com.chatia.project.db.utils.dbQuery
import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User
import com.chatia.project.domain.validator.ValidationResults
import com.chatia.project.security.SecurityManger
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class UserRepositoryTest {

    lateinit var sut: UserRepository
    lateinit var securityManger: SecurityManger
    lateinit var userDataSource: UserDataSource

    @BeforeTest
    fun setup() {
        securityManger=mockk<SecurityManger>()
        userDataSource = mockk<UserDataSource>()
        sut = UserRepositoryImpl(securityManger, userDataSource)


    }

    @Test
    fun `createUser successful registration`() = runTest {
        val request = RegisterRequest(
            userName = "testUser",
            password = "secur@ePassword123",
            email = "test@example.com",
            phoneNumber = "(814)217-2069"
        )


        coEvery {
            userDataSource.createUser(any())
        } returns User(
            id = 1,
            userName = "testUser",
            email = "test@example.com",
            phoneNumber = "(814)217-2069"
        )

        coEvery { userDataSource.findUserByUsername(request.userName!!) } returns null

        val result = sut.createUser(request)

        assertEquals(result?.userName ?: "", request.userName)
        assertEquals(result?.id ?: -1, 1)
        coVerify(exactly = 1) { userDataSource.createUser(request) }
    }


    @Test
    fun `createUser return existing user`() = runTest {
        val request = RegisterRequest(
            userName = "testUser",
            password = "securePassword123@",
            email = "test@example.com",
            phoneNumber = "(814)217-2069"
        )

        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        coEvery {
            userDataSource.createUser(request)
        } returns User(
            id = 1,
            userName = "testUser",
            email = "test@example.com",
            phoneNumber = "(814)217-2069"
        )

        coEvery { userDataSource.findUserByUsername(request.userName!!) } returns
                User(
                    id = 1,
                    userName = "testUser",
                    email = "test@example.com",
                    phoneNumber = "(814)217-2069"
                )
        val result = sut.createUser(request)
        assertEquals(result?.id ?: -1, 1)
        coVerify(exactly = 0) { userDataSource.createUser(request) }
    }

    @Test
    fun `createUser with invalid email format`() = runTest {

        val request = RegisterRequest(
            userName = "testUser",
            password = "securePasswo@rd123",
            email = "test.com",
            phoneNumber = "(814)217-2069"
        )

        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        try {
            sut.createUser(request)
        } catch (e: Exception) {

            assertEquals(e.message, "Invalid email format")
            coVerify(exactly = 0) { userDataSource.createUser(request) }
        }
    }

    @Test
    fun `createUser with short username`() = runTest {
        val request = RegisterRequest(
            userName = "test",
            password = "securePassw@ord123",
            email = "test@Gg.com",
            phoneNumber = "(814)217-2069"
        )
        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        try {
            sut.createUser(request)
        } catch (e: Exception) {
            assertEquals(e.message, ValidationResults.INVALID_USERNAME_LENGTH.message)
            coVerify(exactly = 0) { userDataSource.createUser(request) }
        }
    }

    @Test
    fun `createUser with invalid password`() = runTest {
        val request = RegisterRequest(
            userName = "test",
            password = "securePassword123",
            email = "test@Gg.com",
            phoneNumber = "(814)217-2069"
        )
        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        try {
            sut.createUser(request)
        } catch (e: Exception) {
            assertEquals(
                e.message,
                "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long"
            )
            coVerify(exactly = 0) { userDataSource.createUser(request) }
        }
    }

    @Test
    fun `createUser with null request fields`() = runTest {

        val request = RegisterRequest(
            userName = "",
            password = "securePasswor@d123",
            email = "testkkkk@Gmail.com",
            phoneNumber = "(814)217-2069"
        )
        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        try {
            sut.createUser(request)
        } catch (e: Exception) {
            assertEquals(e.message, ValidationResults.ALL_FIELDS_REQUIRED.message)
            coVerify(exactly = 0) { userDataSource.createUser(request) }
        }
    }


    @Test
    fun `authenticate with valid credentials`() = runTest {
        val loginRequest = LoginRequest(
            password = "securePassword1@23",
            userName = "user11"
        )

        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        coEvery {
            userDataSource.findUserByUsername(loginRequest.userName!!)
        } returns null

        try {

            sut.authenticate(loginRequest)
        } catch (e: Exception) {
            assertEquals(e.message, ValidationResults.USER_NOT_FOUND.message)
        }
    }

    @Test
    fun `authenticate with incorrect password`() = runTest {
        val loginRequest = LoginRequest(
            password = "securePassword@123",
            userName = "userwwww"
        )

        mockkStatic("com.chatia.project.db.utils.DbQueryKt")
        coEvery { dbQuery(any<suspend () -> User>()) } coAnswers {
            val block = arg<suspend () -> User>(0)
            block()
        }

        coEvery {
            userDataSource.findUserByUsername(loginRequest.userName!!)
        } returns User(
            id = 1,
            userName = "user111",
            password = "securePassword@123",
            email = "user@example.com",
            phoneNumber = "ddd"
        )
        coEvery {
            securityManger.verifyPassword(any(), any())
        } returns false

        try {
            sut.authenticate(loginRequest)
        } catch (e: Exception) {

            assertEquals(e.message, ValidationResults.INVALID_PASSWORD.message)
        }
    }


}
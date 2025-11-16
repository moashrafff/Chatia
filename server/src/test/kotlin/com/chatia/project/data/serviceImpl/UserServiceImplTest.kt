package com.chatia.project.data.serviceImpl

import com.chatia.project.domain.model.LoginRequest
import com.chatia.project.domain.model.RegisterRequest
import com.chatia.project.domain.model.User
import com.chatia.project.domain.repo.UserRepository
import com.chatia.project.domain.service.UserService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UserServiceImplTest {
    lateinit var sut: UserService
    lateinit var repo: UserRepository

    @BeforeTest
    fun setup() {
        repo = mockk<UserRepository>()
        sut = UserServiceImpl(repo)
    }

    @Test
    fun `success register returns created user`() = runTest {
        val req = RegisterRequest(
            email = "a@b.com",
            password = "Secret123",
            userName = "Alice",
            phoneNumber = "1111"
        )
        val created = User(id = 1, email = "a@b.com", userName = "Alice", phoneNumber = "1111")

        coEvery { repo.createUser(req) } returns created

        val result = sut.registerUser(req)
        assertEquals(created, result)
        coVerify(exactly = 1) { repo.createUser(req) }
    }

    @Test
    fun `register with null response returns null`() = runTest {
        val req = RegisterRequest(
            email = "a@b.com",
            password = "Secret123",
            userName = "Alice",
            phoneNumber = "1111"
        )

        coEvery { repo.createUser(req) } returns null

        val result = sut.registerUser(req)
        assertNull(result)
        coVerify(exactly = 1) { repo.createUser(req) }
    }

    // Login User Tests
    @Test
    fun `success login returns user`() = runTest {
        val req = LoginRequest(
            userName = "Alice",
            password = "Secret123"
        )
        val user = User(id = 1, email = "a@b.com", userName = "Alice", phoneNumber = "1111")

        coEvery { repo.authenticate(req) } returns user

        val result = sut.loginUser(req)
        assertEquals(user, result)
        coVerify(exactly = 1) { repo.authenticate(req) }
    }

    @Test
    fun `login with invalid credentials returns null`() = runTest {
        val req = LoginRequest(
            userName = "Alice",
            password = "WrongPassword"
        )

        coEvery { repo.authenticate(req) } returns null

        val result = sut.loginUser(req)
        assertNull(result)
        coVerify(exactly = 1) { repo.authenticate(req) }
    }

    @Test
    fun `login with non-existent user returns null`() = runTest {
        val req = LoginRequest(
            userName = "NonExistentUser",
            password = "Secret123"
        )

        coEvery { repo.authenticate(req) } returns null

        val result = sut.loginUser(req)
        assertNull(result)
        coVerify(exactly = 1) { repo.authenticate(req) }
    }

    // Get User By ID Tests
    @Test
    fun `get user by id returns user when found`() = runTest {
        val userId = 1
        val user = User(id = 1, email = "a@b.com", userName = "Alice", phoneNumber = "1111")

        coEvery { repo.getUserById(userId) } returns user

        val result = sut.getUserById(userId)
        assertEquals(user, result)
        coVerify(exactly = 1) { repo.getUserById(userId) }
    }

    @Test
    fun `get user by id returns null when not found`() = runTest {
        val userId = 999

        coEvery { repo.getUserById(userId) } returns null

        val result = sut.getUserById(userId)
        assertNull(result)
        coVerify(exactly = 1) { repo.getUserById(userId) }
    }

    @Test
    fun `get user by id with zero id returns null`() = runTest {
        val userId = 0

        coEvery { repo.getUserById(userId) } returns null

        val result = sut.getUserById(userId)
        assertNull(result)
        coVerify(exactly = 1) { repo.getUserById(userId) }
    }

    // Edge Cases and Error Scenarios
    @Test
    fun `register with empty email returns null`() = runTest {
        val req = RegisterRequest(
            email = "",
            password = "Secret123",
            userName = "Alice",
            phoneNumber = "1111"
        )

        coEvery { repo.createUser(req) } returns null

        val result = sut.registerUser(req)
        assertNull(result)
        coVerify(exactly = 1) { repo.createUser(req) }
    }

    @Test
    fun `login with empty username returns null`() = runTest {
        val req = LoginRequest(
            userName = "",
            password = "Secret123"
        )

        coEvery { repo.authenticate(req) } returns null

        val result = sut.loginUser(req)
        assertNull(result)
        coVerify(exactly = 1) { repo.authenticate(req) }
    }

    @Test
    fun `login with empty password returns null`() = runTest {
        val req = LoginRequest(
            userName = "Alice",
            password = ""
        )

        coEvery { repo.authenticate(req) } returns null

        val result = sut.loginUser(req)
        assertNull(result)
        coVerify(exactly = 1) { repo.authenticate(req) }
    }

    @Test
    fun `get user by negative id returns null`() = runTest {
        val userId = -1

        coEvery { repo.getUserById(userId) } returns null

        val result = sut.getUserById(userId)
        assertNull(result)
        coVerify(exactly = 1) { repo.getUserById(userId) }
    }

    // Repository Interaction Verification
    @Test
    fun `register user calls repository exactly once`() = runTest {
        val req = RegisterRequest(
            email = "test@example.com",
            password = "password123",
            userName = "testuser",
            phoneNumber = "1234567890"
        )

        coEvery { repo.createUser(req) } returns null

        sut.registerUser(req)

        coVerify(exactly = 1) { repo.createUser(req) }
    }

    @Test
    fun `login user calls repository exactly once`() = runTest {
        val req = LoginRequest(
            userName = "testuser",
            password = "password123"
        )

        coEvery { repo.authenticate(req) } returns null

        sut.loginUser(req)

        coVerify(exactly = 1) { repo.authenticate(req) }
    }

    @Test
    fun `get user by id calls repository exactly once`() = runTest {
        val userId = 123

        coEvery { repo.getUserById(userId) } returns null

        sut.getUserById(userId)

        coVerify(exactly = 1) { repo.getUserById(userId) }
    }
}
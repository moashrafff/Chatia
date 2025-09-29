package com.chatia.data.source

import com.chatia.data.response.ErrorResponse
import com.chatia.data.response.toDomain
import com.chatia.data.service.Service
import com.chatia.domain.result.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@OptIn(ExperimentalCoroutinesApi::class)
class NetworkDataSourceTest {

    val dispatcher = UnconfinedTestDispatcher()
    val scope = TestScope(context = dispatcher)


    @BeforeTest
    fun setup() {
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `performRequest should handle SocketTimeoutException and return error with code 400`() = run {

        // Given
        val mockResponse = mockk<HttpResponse>()
        every { mockResponse.status } returns HttpStatusCode.BadRequest

        coEvery { mockResponse.body<ErrorResponse>() } returns ErrorResponse(errorCode = 400, errorMessage = "Connection timeout")
        val mockService = mockk<Service>()


        val networkDataSource = NetworkDataSource(mockService)
        scope.runTest {
            // When
            val result = networkDataSource.performRequest<HttpResponse, HttpResponse>(
                request = { return@performRequest mockResponse },
                onSuccess = { response, headers ->
                    Result.success(response)
                },
                onError = { errorResponse ->
                    Result.error(errorResponse.toDomain())
                },
                onEmpty = {
                    Result.empty()
                }
            )

            // Then
            assertTrue(result is Result.Error)
            assertEquals(
                400,
                result.errorMessage().code
            )
            assertEquals("Connection timeout", result.errorMessage().message)
        }
    }

    @Test
    fun `performRequest should handle generic Exception and return error with code -1 and exception message`() = runTest {
        scope.runTest {
            // Given
            val mockService = mockk<Service>()
            val networkDataSource = NetworkDataSource(mockService)

            // When
            val result = networkDataSource.performRequest<HttpResponse, HttpResponse>(
                request = {
                    throw Exception("dummy")
                    },
                onSuccess = { response, headers ->
                    Result.success(response)
                },
                onError = { errorResponse ->
                    Result.error(errorResponse.toDomain())
                },
                onEmpty = {
                    Result.empty()
                }
            )


            // Then
            assertTrue(result is Result.Error)
            assertEquals(
                -1,//generic exception
                result.errorMessage().code
            )
            assertEquals("dummy", result.errorMessage().message)
        }
    }
}

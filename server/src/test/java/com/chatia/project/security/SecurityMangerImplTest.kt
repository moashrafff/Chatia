package com.chatia.project.security

import org.junit.Test
import kotlin.test.BeforeTest

class SecurityMangerImplTest {
    lateinit var sut: SecurityManger

    @BeforeTest
    fun setup(){
        sut= SecurityMangerImpl()
    }


    @Test
    fun `hashPassword generates unique hashes for the same password`() {
        val password="passs"
        val hash=sut.hashPassword(password)

        assert(sut.verifyPassword(password,hash))
    }

    @Test
    fun `hashPassword with wrong hash`() {

        val password="passs"
        val hash=sut.hashPassword("pass")

        assert(!sut.verifyPassword(password,hash))
    }
}
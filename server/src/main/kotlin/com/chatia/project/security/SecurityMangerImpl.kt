package com.chatia.project.security

import org.mindrot.jbcrypt.BCrypt

class SecurityMangerImpl :SecurityManger{

    override fun hashPassword(password: String): String =
        BCrypt.hashpw(password, BCrypt.gensalt())

    override fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean =
        BCrypt.checkpw(plainPassword,hashedPassword)

}
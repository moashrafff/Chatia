package com.chatia.project.db.utils

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.v1.jdbc.transactions.experimental.newSuspendedTransaction

suspend fun<T: Any> dbQuery(block:suspend ()->T?):T? =
    newSuspendedTransaction(Dispatchers.IO) {
        block()
    }
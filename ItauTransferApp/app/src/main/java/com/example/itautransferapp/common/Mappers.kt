package com.example.itautransferapp.common

import com.example.itautransferapp.data.remote.model.Payment
import com.example.itautransferapp.presentation.data.BankStatementDetails

fun List<Payment>.details(): List<BankStatementDetails> {
    return map { payment ->
        BankStatementDetails(
            typeTransfer = payment.category,
            name = payment.name,
            value = payment.amount,
            date = payment.createdAt
        )
    }
}
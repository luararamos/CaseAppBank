package com.example.itautransferapp.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.itautransferapp.presentation.components.CardStatementTransaction
import com.example.itautransferapp.ui.theme.EXTRA_SMALL_PADDING
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.ZERO_DP

@Composable
fun ListBankStatementScreen() {
    val transactions = listOf(
        BankTransaction("PIX", "Alice", "300.00", "2022-07-01"),
        BankTransaction("TED", "Bob", "150.00", "2022-07-02"),
        BankTransaction("DOC", "Charlie", "450.00", "2022-07-03")
    )
    LazyColumn(
        modifier = Modifier.
        padding(ZERO_DP, MEDIUM_PADDING)
    ) {
        items(transactions) { transaction ->
            CardStatementTransaction(
                transaction
            )
        }
        item{
            Spacer(
                modifier = Modifier.padding(EXTRA_SMALL_PADDING)
            )
        }
    }
}

data class BankTransaction(
    val typeTransaction: String,
    val name: String,
    val value: String,
    val date: String
)
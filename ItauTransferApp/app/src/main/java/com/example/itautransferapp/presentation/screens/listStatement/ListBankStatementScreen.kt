package com.example.itautransferapp.presentation.screens.listStatement

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.itautransferapp.presentation.components.CardStatementTransaction
import com.example.itautransferapp.ui.theme.EXTRA_SMALL_PADDING
import org.koin.androidx.compose.getViewModel

@Composable
fun ListBankStatementScreen() {
    val viewModel: ListBankStatementViewModel = getViewModel()

    val stateList = viewModel.stateList.value
    val employees = stateList.value
    LaunchedEffect(key1 = Unit) {
        viewModel.getListBankStatement()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            items = employees
        ) {
            CardStatementTransaction(it)
        }
        item {
            Spacer(
                modifier = Modifier.padding(EXTRA_SMALL_PADDING)
            )
        }
    }
}
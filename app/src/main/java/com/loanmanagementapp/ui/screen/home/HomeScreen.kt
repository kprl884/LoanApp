package com.loanmanagementapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loanmanagementapp.data.Loan
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeUiState: HomeUIState,
    onEvent: (HomeUIEvent) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Loan Management")
                },
                actions = {
                    Button(
                        onClick = {
                            onEvent(HomeUIEvent.Logout)
                        }
                    ) {
                        Text("Logout")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "User Name:", fontSize = 16.sp)
            Button(onClick = {
                if (homeUiState.listOfLoan.isEmpty())
                    coroutineScope.launch {
                        onEvent(HomeUIEvent.UpdateLoans(context))
                    }
                else
                    homeUiState.listOfLoan = emptyList()
            }) {
                Text("Load Loans", fontSize = 16.sp)
            }
            homeUiState.listOfLoan.forEach { loan ->
                performCalculationForLoan(loan)
                Text(
                    "${loan.name} - ${loan.status}, Interest: ${loan.interestRate}%",
                    fontSize = 16.sp
                )
                performCalculation(loan)
            }
        }
    }
}

private fun performCalculation(loan: Loan) {
    repeat(1000) { _ -> sqrt(loan.interestRate) }
}

private fun performCalculationForLoan(loan: Loan) {
    val result = loan.principalAmount * loan.interestRate
}
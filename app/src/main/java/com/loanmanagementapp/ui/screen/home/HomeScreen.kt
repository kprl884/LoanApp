package com.loanmanagementapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.ui.components.LoanCard
import com.loanmanagementapp.ui.components.LoanTypeSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeUiState: HomeUIState,
    onEvent: (HomeUIEvent) -> Unit
) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Loan Calculator dropdown, select loan type please",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        LoanTypeSelector(
                            selectedType = homeUiState.selectedLoanType,
                            onTypeSelected = { loanType ->
                                onEvent(HomeUIEvent.LoanTypeSelected(loanType))
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = homeUiState.amount,
                            onValueChange = { onEvent(HomeUIEvent.AmountChanged(it)) },
                            label = { Text("Loan Amount") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = homeUiState.term,
                            onValueChange = { onEvent(HomeUIEvent.TermChanged(it)) },
                            label = { Text("Term (months)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        homeUiState.errorMessage?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        Button(
                            onClick = { onEvent(HomeUIEvent.CalculateLoan) },
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(top = 8.dp)
                        ) {
                            Text("Calculate")
                        }

                        if (homeUiState.calculatedInterest != null && homeUiState.monthlyPayment != null) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Monthly Payment",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = "$${String.format("%.2f", homeUiState.monthlyPayment)}",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                                Column {
                                    Text(
                                        text = "Total Interest",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = "$${String.format("%.2f", homeUiState.calculatedInterest)}",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = { onEvent(HomeUIEvent.UpdateLoans(context)) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Load Loans")
                }
            }

            if (homeUiState.listOfLoan.isEmpty()) {
                item {
                    Text(
                        text = "No loans available. Click 'Load Loans' to fetch data.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            } else {
                items(homeUiState.listOfLoan.size) { index ->
                    val loan = homeUiState.listOfLoan[index]
                    LoanCard(
                        loan = loan,
                        monthlyPayment = loan.principalAmount / 12,
                        calculatedInterest = loan.principalAmount * loan.interestRate / 100,
                        onClick = { /* Handle loan click */ },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

package com.loanmanagementapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.data.Loan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanCard(
    loan: Loan,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${loan.name} Loan",
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Amount",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "$${loan.principalAmount}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                
                Column {
                    Text(
                        text = "Interest Rate",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${loan.interestRate}%",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Surface(
                shape = MaterialTheme.shapes.small,
                color = when (loan.status) {
                    "APPROVED" -> MaterialTheme.colorScheme.primaryContainer
                    "PENDING" -> MaterialTheme.colorScheme.secondaryContainer
                    "REJECTED" -> MaterialTheme.colorScheme.errorContainer
                    else -> MaterialTheme.colorScheme.surfaceVariant
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = loan.status,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = when (loan.status) {
                        "APPROVED" -> MaterialTheme.colorScheme.onPrimaryContainer
                        "PENDING" -> MaterialTheme.colorScheme.onSecondaryContainer
                        "REJECTED" -> MaterialTheme.colorScheme.onErrorContainer
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
} 
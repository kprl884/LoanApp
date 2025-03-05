package com.loanmanagementapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.domain.model.LoanStatus
import java.text.NumberFormat
import java.util.Locale

@Composable
fun LoanCard(
    loan: Loan,
    monthlyPayment: Double,
    calculatedInterest: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

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
                        text = currencyFormat.format(loan.principalAmount),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Monthly Payment",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = currencyFormat.format(monthlyPayment),
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

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Total Interest",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = currencyFormat.format(calculatedInterest),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = MaterialTheme.shapes.small,
                color = when (loan.status) {
                    LoanStatus.APPROVED -> MaterialTheme.colorScheme.primaryContainer
                    LoanStatus.PENDING -> MaterialTheme.colorScheme.secondaryContainer
                    LoanStatus.REJECTED -> MaterialTheme.colorScheme.errorContainer
                    LoanStatus.OVERDUE -> MaterialTheme.colorScheme.errorContainer
                    LoanStatus.DEFAULT -> MaterialTheme.colorScheme.error.copy(alpha = 0.12f)
                    LoanStatus.PAID -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = loan.status.displayName,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = when (loan.status) {
                        LoanStatus.APPROVED -> MaterialTheme.colorScheme.onPrimaryContainer
                        LoanStatus.PENDING -> MaterialTheme.colorScheme.onSecondaryContainer
                        LoanStatus.REJECTED -> MaterialTheme.colorScheme.onErrorContainer
                        LoanStatus.OVERDUE -> MaterialTheme.colorScheme.onErrorContainer
                        LoanStatus.DEFAULT -> MaterialTheme.colorScheme.error
                        LoanStatus.PAID -> MaterialTheme.colorScheme.tertiary
                    }
                )
            }

            Text(
                text = "Due in: ${if (loan.dueIn > 0) "${loan.dueIn} days" else "Overdue"}",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = if (loan.dueIn <= 0 && loan.status != LoanStatus.PAID)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
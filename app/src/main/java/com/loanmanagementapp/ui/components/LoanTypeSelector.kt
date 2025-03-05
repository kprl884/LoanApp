package com.loanmanagementapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.loanmanagementapp.domain.model.LoanType

@Composable
fun LoanTypeSelector(
    selectedType: LoanType,
    onTypeSelected: (LoanType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column {
        Button(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Loan Type: ${selectedType.name}")
        }
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            LoanType.entries.forEach { type ->
                DropdownMenuItem(
                    text = { Text(type.name) },
                    onClick = { 
                        onTypeSelected(type)
                        expanded = false
                    }
                )
            }
        }
    }
}
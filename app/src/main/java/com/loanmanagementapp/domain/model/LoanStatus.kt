package com.loanmanagementapp.domain.model

enum class LoanStatus(val displayName: String) {
    APPROVED("Approved"),
    PENDING("Pending"),
    REJECTED("Rejected"),
    OVERDUE("Overdue"),
    DEFAULT("Default"),
    PAID("Paid");

    companion object {
        fun fromString(status: String): LoanStatus {
            return values().find {
                it.name.equals(status, ignoreCase = true) ||
                        it.displayName.equals(status, ignoreCase = true)
            } ?: PENDING
        }
    }
}


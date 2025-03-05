# Loan Management App Architecture Documentation

## Overview
The Loan Management App follows Clean Architecture principles with MVVM pattern, utilizing modern Android development practices and design patterns. The application is structured to be maintainable, testable, and scalable.

## Architecture Layers

### 1. Presentation Layer (UI)
- Implements MVVM pattern
- Components:
  - Activities/Composables: Handle UI rendering
  - ViewModels: Manage UI state and business logic
  - UI States: Immutable data classes representing screen state
  - UI Events: Sealed classes representing user actions

Example:
kotlin
data class HomeState(
val loans: List<Loan> = emptyList(),
val selectedLoanType: LoanType = LoanType.PERSONAL,
val amount: String = "",
val term: String = "",
val calculatedInterest: Double? = null
)

### 2. Domain Layer
- Contains business logic and rules
- Pure Kotlin code, no Android dependencies
- Components:
  - Models: Core business entities
  - Strategy Pattern: Loan interest calculations
  - Factory Pattern: Strategy creation

#### Strategy Pattern Implementation
kotlin
interface LoanStrategy {
fun calculateInterest(principal: Double, term: Int): Double
fun getMinTerm(): Int
fun getMaxTerm(): Int
fun getBaseInterestRate(): Double
}

Different loan types implement this interface with specific calculations:
- PersonalLoanStrategy: Higher rates, shorter terms
- AutoLoanStrategy: Medium rates, principal-based adjustments
- MortgageLoanStrategy: Lower rates, longer terms, tiered rates

### 3. Data Layer
- Handles data operations
- Components:
  - PreferencesManager: Local data storage
  - Repositories: Data access abstraction
  - Data Sources: Concrete implementations

## Design Patterns Used

### 1. Strategy Pattern
- Purpose: Encapsulate different loan calculation algorithms
- Benefits:
  - Easy to add new loan types
  - Isolates calculation logic
  - Supports runtime algorithm switching

### 2. Factory Pattern
- Purpose: Create appropriate loan strategy instances
- Implementation:
@Singleton
class LoanStrategyFactory @Inject constructor() {
fun createStrategy(type: LoanType): LoanStrategy
}

### 3. MVVM Pattern
- ViewModel: Manages UI logic and state
- State Flow: Handles reactive state updates
- Unidirectional Data Flow: Events down, state up

## Dependencies Management

### Dependency Injection
- Uses Hilt for DI
- Scoped instances for singletons
- Module-based dependency provision

### Local Storage
- SharedPreferences for user session
- Clean separation through PreferencesManager

## Testing Strategy

### Unit Tests
- Strategy Pattern Tests:
  - Interest calculations

## SOLID Principles Application

### 1. Single Responsibility
- Each strategy handles one type of loan calculation
- ViewModels manage single screen's logic
- PreferencesManager handles only local storage

### 2. Open/Closed
- New loan types can be added without modifying existing code
- Strategy pattern enables extension without modification

### 3. Interface Segregation
- Clean interfaces for strategies
- Specific event types for different screens

### 4. Dependency Inversion
- High-level modules depend on abstractions
- Factory pattern manages concrete implementations

## Data Flow
1. User Action → UI Event
2. ViewModel Processes Event
3. Strategy Calculates Interest
4. State Updates
5. UI Recomposes

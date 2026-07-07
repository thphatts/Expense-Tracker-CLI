# Expense Tracker CLI

A simple and efficient Command Line Interface (CLI) application developed in Java to manage personal finances and track daily expenses. This project is built as part of the [roadmap.sh Expense Tracker CLI Project](https://roadmap.sh/projects/expense-tracker).

## Features

- **Add Expenses**: Record new expenses with a description and amount.
- **Update Expenses**: Modify existing expenses by their unique ID.
- **Delete Expenses**: Remove expenses that are no longer needed.
- **List Expenses**: View all recorded expenses formatted cleanly in a table.
- **Expense Summary**: View the total summary of all expenses.
- **Monthly Summary**: View the total summary of expenses for a specific month.
- **Persistent Storage**: All expenses are automatically saved to and loaded from a local `expenses.csv` file.

---

## Prerequisites

- **Java Development Kit (JDK)**: Make sure you have Java 8 or higher installed on your machine.
- Verify your Java installation:
  ```bash
  java -version
  javac -version
  ```

---

## Installation & Setup

1. **Clone or Download the Repository**:

   ```bash
   git clone <https://github.com/thphatts/Expense-Tracker-CLI.git>
   cd Expense-Tracker-CLI
   ```

2. **Compile the Java Files**:
   Before running the application, compile the `.java` source files using `javac`:
   ```bash
   javac *.java
   ```

---

## Usage Guide

Run the application using the `java` command followed by the main class name `ExpenseTrackerCLI` and the desired command.

### 1. Add an Expense

Add a new expense by providing a description and an amount.

```bash
java ExpenseTrackerCLI add --description "Lunch" --amount 20
```

**Output:**

```
Expense added sucessfully (ID : 1)
```

### 2. List All Expenses

Display all recorded expenses in a tabular format.

```bash
java ExpenseTrackerCLI list
```

**Output:**

```
ID    Date            Description          Amount
1     6 thg 7 2026    lanch                $29000,00
2     6 thg 7 2026    lanch                $29000,00
3     6 thg 7 2026    lanch                $29000,00
4     6 thg 7 2026    lanch                $29000,00
5     6 thg 7 2026    lanch                $29000,00
```

### 3. Update an Expense

Update the description and amount of an existing expense using its ID.

```bash
java ExpenseTrackerCLI update --id 1 --description "Dinner" --amount 35
```

**Output:**

```
Update sucessful!
```

### 4. Delete an Expense

Remove an expense from your tracking list using its ID.

```bash
java ExpenseTrackerCLI delete --id 3
```

**Output:**

```
Remove Expense with ID 3 sucessfully!
```

### 5. View Summary of All Expenses

Calculate and display the total amount of all recorded expenses.

```bash
java ExpenseTrackerCLI summary
```

**Output:**

```
Total expenses: 145000.0
```

### 6. View Monthly Summary

Calculate and display the total expenses for a specific month (1-12). For example, to view summary for July (month 7):

```bash
java ExpenseTrackerCLI summary --month 7
```

**Output:**

```
Total expenses for July : $145000.0
```

---

## Data Storage

The application stores all expense records in a local comma-separated values file named `expenses.csv` in the root directory. This ensures that your data persists across different terminal sessions and application restarts.

---

## 📝 License

This project is open-source and available under the MIT License.

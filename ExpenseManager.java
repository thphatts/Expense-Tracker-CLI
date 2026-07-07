import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseManager {
    private static final String FILE_NAME = "expenses.csv";
    private static List<Expense> expenses;

    public ExpenseManager() {
        ExpenseManager.expenses = loadExpense();
    }

    public static void saveExpense() {

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                printWriter.write(e.toCSV());
                printWriter.println();
            }
        } catch (IOException e) {
            System.out.println("Error saving expense: " + e.getMessage());
        }
    }

    public List<Expense> loadExpense() {
        expenses = new ArrayList<>();
        Expense.resetLastId();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue;
                }
                int id = Integer.parseInt(parts[0].trim());
                String description = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                LocalDateTime createdAt = LocalDateTime.parse(parts[3].trim());
                LocalDateTime updatedAt = LocalDateTime.parse(parts[4].trim());

                expenses.add(new Expense(id, description, amount, createdAt, updatedAt));
            }
        } catch (IOException e) {
            System.out.println("Error load expense: " + e.getMessage());
        }
        return expenses;
    }

    public void addExpense(String description, double amount) {
        Expense expense = new Expense(description, amount);
        expenses.add(expense);
        System.out.println("Expense added sucessfully (ID : " + expense.getId() + ")");
    }

    public boolean findExpense(int id) {
        for (Expense e : expenses) {
            if (e.getId() == id) {
                return true;
            }
        }
        System.out.println("Expense with ID " + id + " not found, please check id of Expense!");
        return false;
    }

    public void updateExpense(int id, String description, double amount) {
        if (!findExpense(id)) {
            return;
        }
        for (Expense e : expenses) {
            if (e.getId() == id) {
                e.updateExpense(description, amount);
                saveExpense();
                System.out.println("Update sucessful!");
                return;
            }
        }
    }

    public void deleteExpense(int id) {
        if (findExpense(id) == false) {
            return;
        }
        expenses.removeIf(e -> e.getId() == id);
        saveExpense();
        System.out.println("Remove Expense with ID " + id + " sucessfully!");
        return;
    }

    public void summaryExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total expenses: " + total);
        return;
    }

    public void summaryExpenseByMonth(int monthNumber) {
        double total = 0;
        String monthName;
        try {
            Month month = Month.of(monthNumber);
            monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        } catch (Exception e) {
            System.out.println("Month must to range 1-12");
            return;
        }
        for (Expense e : expenses) {
            if (e.getCreatedAt().getMonthValue() == monthNumber) {
                total += e.getAmount();
            }
        }
        System.out.println("Total expenses for " + monthName + " : $" + total);
        return;
    }

    public void listExpense() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
        System.out.printf("%-5s %-15s %-20s %s%n", "ID", "Date", "Description", "Amount");
        for (Expense exp : expenses) {
            System.out.printf("%-5s %-15s %-20s $%.2f%n",
                    exp.getId(),
                    exp.getCreatedAt().format(formatter),
                    exp.getDescription(),
                    exp.getAmount());
        }
    }
}

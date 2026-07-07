import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private static int lastId;
    private int id;
    private double amount;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Expense(String description, double amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = ++lastId;
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Expense(String description, double amount) {
        this.id = ++lastId;
        this.description = description;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String toCSV() {
        return id + "," + description + "," + amount + "," + createdAt.format(formatter) + ","
                + updatedAt.format(formatter);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateExpense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Expense [id=" + id + ", amount=" + amount + ", description=" + description + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }
}

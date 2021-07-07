package Classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class FinancialReport implements Serializable {
    private final LocalDate date;
    private long income = 0;
    @Serial
    private static final long serialVersionUID = 1;

    public FinancialReport(LocalDate date) {
        this.date = date;
    }

    public FinancialReport(LocalDate date, long income) {
        this.date = date;
        this.income = income;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income += income;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FinancialReport{" +
                "date=" + date +
                ", income=" + income +
                '}';
    }
}

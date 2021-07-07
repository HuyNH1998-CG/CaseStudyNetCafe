package Classes;

import java.time.LocalDate;

public class Bill {
    private final LocalDate date;
    private long income = 0;

    public Bill(LocalDate date) {
        this.date = date;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "date=" + date +
                ", income=" + income +
                '}';
    }
}

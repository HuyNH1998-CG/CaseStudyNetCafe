package Classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PC implements Runnable, Serializable {
    private String CPU;
    private String GPU;
    private String RAM;
    private String HDD;
    private boolean online = false;
    private String status = "Disable";
    private boolean done;
    private long cost;
    private long servicecost = 0;
    private String elapsed;
    private List<Service> services = new ArrayList<>();
    @Serial
    private static final long serialVersionUID = 1;

    public PC(String CPU, String GPU, String RAM, String HDD) {
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
        this.HDD = HDD;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getHDD() {
        return HDD;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public boolean isOnline() {
        return online;
    }

    public String isPCOnline() {
        if (online) {
            return "online";
        } else {
            return "offline";
        }
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        if (online) {
            this.status = "Available";
        } else {
            this.status = "Disabled";
        }
    }

    public List<Service> getServices() {
        return services;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
        setOnline(true);
        setStatus();
        done = false;
    }

    public void stop() {
        done = true;
        setOnline(false);
        setStatus();
    }

    public String displayOnline() {
        return "Thời gian: " + elapsed + ", Thành tiền: " + cost;
    }

    public String displaySpec() {
        return getCPU() + "\n" +
                getGPU() + "\n" +
                getRAM() + "\n" +
                getHDD();
    }

    @Override
    public void run() {
        LocalTime start = LocalTime.now();
        while (!done) {
            LocalTime now = LocalTime.now();
            long duration = Duration.between(start, now).getSeconds();
            elapsed = String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, duration % 60);
            if ((duration % 3600) / 60 < 15) {
                cost = 2500;
            } else {
                cost = calculate(start, now);
            }
        }
        checkOut();
    }

    private void checkOut() {
        System.out.println("Tiền máy: " + cost);
        if (!services.isEmpty()) {
            for (Service service : services) {
                System.out.println(" + " + service.getName());
                servicecost += service.getPrice();
            }
        }
        System.out.println("Tiền dịch vụ: " + servicecost);
        long total = cost + servicecost;
        System.out.println("Tổng: " + total);
        Management.getFinancialRP(total);
        servicecost = 0;
        cost = 10000;
    }


    private long calculate(LocalTime start, LocalTime now) {
        Duration duration = Duration.between(start, now);
        Duration chunk = Duration.ofMinutes(15);
        int chunks = Math.toIntExact(duration.dividedBy(chunk));
        long pricePerMin = 2500;
        return pricePerMin * chunks;
    }

}

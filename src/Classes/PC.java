package Classes;

import java.time.Duration;
import java.time.LocalTime;

public class PC implements Runnable {
    private Thread thread;
    private String name;
    private boolean online = false;
    private String status = "Disable";
    private boolean done;
    private final long pricePerMin = 100;
    private long cost;
    private String elapsed;

    public PC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isOnline() {
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

    @Override
    public String toString() {
        return "PC{" +
                "name='" + name + '\'' +
                ", online=" + online +
                ", status='" + status + '\'' +
                '}';
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        done = false;
    }

    public void stop() {
        done = true;
    }

    public String displayOnline(){
        return "Thời gian: " + elapsed + ", Thành tiền: " + cost;
    }

    @Override
    public void run() {
        LocalTime start = LocalTime.now();
        while (!done) {
            LocalTime now = LocalTime.now();
            long duration = Duration.between(start, now).getSeconds();
            elapsed = String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, duration % 60);
            cost = calculate(start, now);
        }
        cost = 0;
    }

    private long calculate(LocalTime start, LocalTime now) {
        Duration duration = Duration.between(start,now);
        Duration chunk = Duration.ofMinutes(1);
        int chunks = Math.toIntExact(duration.dividedBy(chunk));
        return pricePerMin * chunks;
    }

    public long getCost() {
        return cost;
    }

    public String getElapsed() {
        return elapsed;
    }
}

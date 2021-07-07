package Classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    private static List<PC> pcList = new ArrayList<>();
    private static List<Service> serviceList = new ArrayList<>();
    private static List<FinancialReport> financialReport = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static List<FinancialReport> getFinancialReport() {
        return financialReport;
    }

    public static void addPC() {
        try {
            PC pc = makeNewPC();
            pcList = IOOperator.readPC("src/Files/PC.txt");
            pcList.add(pc);
            IOOperator.writePCFile("src/Files/PC.txt", pcList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PC makeNewPC() {
        String CPU = getHardware("Nhập CPU bạn đã lắp");
        String GPU = getHardware("Nhập card màn hình bạn đã lắp");
        String RAM = getHardware("Nhập RAM bạn đã lắp");
        String HDD = getHardware("Nhập ổ cứng bạn đã lắp");
        return new PC(CPU, GPU, RAM, HDD);
    }

    private static String getHardware(String s) {
        System.out.println(s);
        return input.nextLine();
    }

    public static void showAllPC() {
        try {
            errNoPC();
            showAll();
            showDetail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean errNoPC() {
        if (pcList.isEmpty()) {
            pcList = IOOperator.readPC("src/Files/PC.txt");
            return true;
        }
        return false;
    }

    private static void showAll() {
        for (int i = 0; i < pcList.size(); i++) {
            System.out.println("Máy Số " + (i + 1) + ":" + pcList.get(i).isPCOnline());
        }
    }

    private static void showDetail() {
        System.out.println("Bạn có muốn xem chi tiết máy không?");
        System.out.println("1. Có");
        System.out.println("2. Không");
        int choice = Integer.parseInt(input.nextLine());
        if (choice == 1) {
            showDetail2();
        }
    }

    private static void showDetail2() {
        System.out.println("1. Xem cấu hình máy");
        System.out.println("2. Xem tình trạng");
        int choice2 = Integer.parseInt(input.nextLine());
        switch (choice2) {
            case 1 -> showPCSpec();
            case 2 -> showPCStatus();
        }
    }

    private static void showPCStatus() {
        System.out.println("Nhập số của máy");
        int index = Integer.parseInt(input.nextLine());
        if (pcList.get(index - 1).isOnline()) {
            System.out.println(pcList.get(index - 1).displayOnline());
        } else {
            System.out.println(pcList.get(index - 1).isPCOnline());
        }
    }

    private static void showPCSpec() {
        System.out.println("Nhập số của máy");
        int index = Integer.parseInt(input.nextLine());
        System.out.println(pcList.get(index - 1).displaySpec());
    }

    public static void sellPC() {
        try {
            if (errNoPC()) return;
            showAll();
            System.out.println("Nhập số của máy cần bán");
            int index = Integer.parseInt(input.nextLine());
            pcList.remove(index - 1);
            System.out.println("Đã bán máy");
            IOOperator.writePCFile("src/Files/PC.txt", pcList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void upgradePC() {
        try {
            if (errNoPC()) return;
            showAll();
            System.out.println("Nhập số của máy cần nâng cấp");
            int index = Integer.parseInt(input.nextLine());
            System.out.println("Bạn muốn nâng cấp bộ phận nào");
            System.out.println("1. CPU");
            System.out.println("2. Card màn hình");
            System.out.println("3. RAM");
            System.out.println("4. Ổ cứng");
            System.out.println("5. Toàn bộ máy");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1 -> pcList.get(index - 1).setCPU(getHardware("Nhập CPU bạn đã lắp"));
                case 2 -> pcList.get(index - 1).setGPU(getHardware("Nhập card màn hình bạn đã lắp"));
                case 3 -> pcList.get(index - 1).setRAM(getHardware("Nhập RAM bạn đã lắp"));
                case 4 -> pcList.get(index - 1).setHDD(getHardware("Nhập ổ cứng bạn đã lắp"));
                case 5 -> pcList.set(index - 1, makeNewPC());
            }
            System.out.println("Nâng cấp xong");
            IOOperator.writePCFile("src/Files/PC.txt", pcList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startPC() {
        try {
            if (errNoPC()) return;
            showAll();
            System.out.println("Nhập số của máy cần bật");
            int index = Integer.parseInt(input.nextLine());
            pcList.get(index - 1).start();
            System.out.println("Máy đã được bật");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkOut() {
        try {
            if (errNoPC()) return;
            showAll();
            System.out.println("Nhập số của máy cần tính tiền");
            int index = Integer.parseInt(input.nextLine());
            pcList.get(index - 1).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFinancialRP(long total) {
        try {
            IOOperator.readIncome("src/Files/Income.txt");
            if (financialReport.isEmpty()) {
                Management.getFinancialReport().add(new FinancialReport(LocalDate.now(), total));
            } else {
                for (FinancialReport financialReport : Management.getFinancialReport()) {
                    if (financialReport.getDate().equals(LocalDate.now())) {
                        financialReport.setIncome(total);
                    } else {
                        Management.getFinancialReport().add(new FinancialReport(LocalDate.now(), total));
                    }
                }
            }
            IOOperator.writeIncome("src/Files/Income.txt", financialReport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showFinancialRP() {
        try {
            IOOperator.readIncome("src/Files/Income.txt");
            for (FinancialReport financialReport : financialReport) {
                System.out.println(financialReport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showFinancialRPInPeriod() {
        try {
            IOOperator.readIncome("src/Files/Income.txt");
            System.out.println("Nhập ngày bắt đầu (năm-tháng-ngày)");
            String start = input.nextLine();
            System.out.println("Nhập ngày kết thúc (năm-tháng-ngày)");
            String end = input.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);
            for (FinancialReport financialReport : financialReport) {
                if (financialReport.getDate().isAfter(startDate) && financialReport.getDate().isBefore(endDate)) {
                    System.out.println(financialReport);
                }
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra");
            System.out.println("Hãy đảm bảo nhập đúng như ví dụ sau: " + LocalDate.now());
        }
    }

    public static void addNewService() {
        try {
            serviceList = IOOperator.readService("src/Files/Service.txt");
            serviceList.add(createService());
            IOOperator.writeServiceFile("src/Files/Service.txt", serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Service createService() {
        System.out.println("Nhập tên dịch vụ");
        String name = input.nextLine();
        System.out.println("Nhập giá tiền của dịch vụ");
        long price = Long.parseLong(input.nextLine());
        return new Service(name, price);
    }

    public static void addService() {
        try {
            serviceList = IOOperator.readService("src/Files/Service.txt");
            if (errNoPC()) return;
            showAll();
            showAllServices();
            System.out.println("Nhập số của máy gọi dịch vụ");
            int index = Integer.parseInt(input.nextLine());
            if (!pcList.get(index - 1).isOnline()) {
                System.out.println("Máy chưa được mở");
                return;
            }
            System.out.println("Nhập dịch vụ (số thứ tự)");
            int index2 = Integer.parseInt(input.nextLine());
            pcList.get(index - 1).getServices().add(serviceList.get(index2-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showAllServices() {
        for (int i = 0; i < serviceList.size(); i++) {
            System.out.println("Số " + (i + 1) + " " + serviceList.get(i));
        }
    }

    public static void removeService() {
        try {
            serviceList = IOOperator.readService("src/Files/Service.txt");
            showAllServices();
            System.out.println("Nhập số của dịch vụ cần xóa");
            int index = Integer.parseInt(input.nextLine());
            serviceList.remove(index - 1);
            IOOperator.writeServiceFile("src/Files/Service.txt", serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editService() {
        try {
            serviceList = IOOperator.readService("src/Files/Service.txt");
            showAllServices();
            System.out.println("Nhập số của dịch vụ cần sửa");
            int index = Integer.parseInt(input.nextLine());
            serviceList.set(index - 1, createService());
            IOOperator.writeServiceFile("src/Files/Service.txt", serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewAdmin() {
        try {
            Login.setList(IOOperator.readAdmin("src/Files/Admins.txt"));
            Login.getList().add(createAdmin());
            IOOperator.writeAdminFile("src/Files/Admins.txt", Login.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Admin createAdmin() {
        System.out.println("Thêm một quản trị mới");
        System.out.println("Nhập tên");
        String username = input.nextLine();
        System.out.println("Nhập mật khẩu");
        String password = input.nextLine();
        return new Admin(username, password);
    }

    public static void removeAdmin() {
        try {
            Login.setList(IOOperator.readAdmin("src/Files/Admins.txt"));
            for (int i = 0; i < Login.getList().size(); i++) {
                System.out.println("Số " + i + 1 + " " + Login.getList().get(i).getUsername());
            }
            System.out.println("Nhập số của tài khoản cần xóa");
            int index = Integer.parseInt(input.nextLine());
            Login.getList().remove(index - 1);
            IOOperator.writeAdminFile("src/Files/Admins.txt", Login.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editAdmin() {
        try {
            Login.setList(IOOperator.readAdmin("src/Files/Admins.txt"));
            for (int i = 0; i < Login.getList().size(); i++) {
                System.out.println("Số " + (i + 1) + " " + Login.getList().get(i).getUsername());
            }
            System.out.println("Nhập số của tài khoản cần sửa");
            int index = Integer.parseInt(input.nextLine());
            Login.getList().set(index - 1, createAdmin());
            IOOperator.writeAdminFile("src/Files/Admins.txt", Login.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

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
    private static final int CPU = 1;
    private static final int GPU = 2;
    private static final int RAM = 3;
    private static final int HDD = 4;
    private static final int ALL = 5;
    private static final int yes = 1;
    private static final int spec = 1;
    private static final int status = 2;
    private static final String admin = "admin";
    private static final String cancel = "cancel";

    public static List<FinancialReport> getFinancialReport() {
        return financialReport;
    }

    public static void addPC() {
        try {
            PC pc = makeNewPC();
            if (pc == null) {
                System.out.println("Mua thiếu linh kiện hoặc chủ quán không muốn thêm máy nữa");
                return;
            }
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
        if (CPU.equals(cancel) || GPU.equals(cancel) || RAM.equals(cancel) || HDD.equals(cancel)) {
            return null;
        }
        return new PC(CPU, GPU, RAM, HDD);
    }


    private static String getHardware(String s) {
        System.out.println(s);
        String hardware = input.nextLine();
        if(hardware.matches("^\\w+")){
            return hardware;
        }
        return cancel;
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
        try {
            System.out.println("Bạn có muốn xem chi tiết máy không?");
            System.out.println("1. Có");
            System.out.println("2. Không");
            int choice = Integer.parseInt(input.nextLine());
            if (choice == yes) {
                showDetail2();
            }
        } catch (Exception e) {
            System.out.println("Quay lại màn hình chính...");
        }
    }

    private static void showDetail2() {
        try {
            System.out.println("1. Xem cấu hình máy");
            System.out.println("2. Xem tình trạng");
            int choice2 = Integer.parseInt(input.nextLine());
            switch (choice2) {
                case spec -> showPCSpec();
                case status -> showPCStatus();
            }
        } catch (Exception e) {
            System.out.println("Quay lại màn hình chính...");
        }
    }

    private static void showPCStatus() {
        try {
            System.out.println("Nhập số của máy");
            int index = Integer.parseInt(input.nextLine());
            if (pcList.get(index - 1).isOnline()) {
                System.out.println(pcList.get(index - 1).displayOnline());
            } else {
                System.out.println(pcList.get(index - 1).isPCOnline());
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy máy");
        }
    }

    private static void showPCSpec() {
        try {
            System.out.println("Nhập số của máy");
            int index = Integer.parseInt(input.nextLine());
            System.out.println(pcList.get(index - 1).displaySpec());
        } catch (Exception e) {
            System.out.println("Không tìm thấy máy");
        }
    }

    public static void sellPC() {
        try {
            if (errNoPC()) return;
            if (!Login.getUser().getUsername().equalsIgnoreCase(admin)) {
                System.out.println("Không bán được máy vì không phải chủ quán");
                return;
            }
            showAll();
            System.out.println("Nhập số của máy cần bán");
            int index = Integer.parseInt(input.nextLine());
            pcList.remove(index - 1);
            System.out.println("Đã bán máy");
            IOOperator.writePCFile("src/Files/PC.txt", pcList);
        } catch (Exception e) {
            System.out.println("Không tìm thấy máy cần bán");
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
                case CPU -> pcList.get(index - 1).setCPU(getHardware("Nhập CPU bạn đã lắp"));
                case GPU -> pcList.get(index - 1).setGPU(getHardware("Nhập card màn hình bạn đã lắp"));
                case RAM -> pcList.get(index - 1).setRAM(getHardware("Nhập RAM bạn đã lắp"));
                case HDD -> pcList.get(index - 1).setHDD(getHardware("Nhập ổ cứng bạn đã lắp"));
                case ALL -> pcList.set(index - 1, makeNewPC());
            }
            System.out.println("Nâng cấp xong");
            IOOperator.writePCFile("src/Files/PC.txt", pcList);
        } catch (Exception e) {
            System.out.println("Nâng cấp không thành công");
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
            System.out.println("Không bật được máy");
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
            System.out.println("Không tính tiền nữa");
            System.out.println("Quay lại màn hình chính....");
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
            System.out.println("Hãy đảm bảo nhập ngày tháng đúng như ví dụ sau: " + LocalDate.now());
        }
    }

    public static void addNewService() {
        try {
            serviceList = IOOperator.readService("src/Files/Service.txt");
            Service temp = createService();
            if (temp == null) {
                return;
            }
            serviceList.add(temp);
            IOOperator.writeServiceFile("src/Files/Service.txt", serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Service createService() {
        try {
            System.out.println("Nhập tên dịch vụ");
            String name = input.nextLine();
            if (name.equals(cancel)) {
                System.out.println("Hủy quá trình thêm dịch vụ");
                return null;
            }
            System.out.println("Nhập giá tiền của dịch vụ");
            long price = Long.parseLong(input.nextLine());
            return new Service(name, price);
        } catch (Exception e) {
            System.out.println("Hủy quá trình thêm dịch vụ");
        }
        return null;
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
            pcList.get(index - 1).getServices().add(serviceList.get(index2 - 1));
        } catch (Exception e) {
            System.out.println("Hủy order dịch vụ");
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
            System.out.println("Hủy quá trình thêm dịch vụ");
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
            System.out.println("Hủy quá trình sửa dịch vụ");
        }
    }

    public static void addNewAdmin() {
        try {
            Login.setList(IOOperator.readAdmin("src/Files/Admins.txt"));
            Admin temp = createAdmin();
            if (temp == null) {
                System.out.println("Tạo người dùng thất bại");
                return;
            }
            Login.getList().add(temp);
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
        if (username.equalsIgnoreCase(admin)) {
            return null;
        }
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
            System.out.println("Hủy quá trình xóa tài khoản");
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
            System.out.println("Hủy quá trình sửa tài khoản");
        }
    }
}

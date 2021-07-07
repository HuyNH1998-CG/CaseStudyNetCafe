package Classes;

import java.util.Scanner;

public class AdminMenu {
    static Scanner input = new Scanner(System.in);
    public static void menu() {

        while (true) {
            System.out.println("Hệ thống quản lý phòng net");
            System.out.println("1. Hiển thị danh sách máy");
            System.out.println("2. Thêm máy mới");
            System.out.println("3. Bán máy");
            System.out.println("4. Nâng cấp máy");
            System.out.println("5. Bật máy");
            System.out.println("6. Tính tiền máy");
            System.out.println("7. Xem doanh thu");
            System.out.println("8. Xem doanh thu trong khoảng thời gian");
            System.out.println("9. Thêm dịch vụ mới ");
            System.out.println("10. Thêm dịch vụ vào máy");
            System.out.println("11. Xóa dịch vụ");
            System.out.println("12. Sửa dịch vụ");
            System.out.println("13. Thêm tài khoản quản trị ");
            System.out.println("14. Xóa tài khoản quản trị");
            System.out.println("15. Sửa tài khoản quản trị");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1 -> Management.showAllPC();
                case 2 -> Management.addPC();
                case 3 -> Management.sellPC();
                case 4 -> Management.upgradePC();
                case 5 -> Management.startPC();
                case 6 -> Management.checkOut();
                case 7 -> Management.showFinancialRP();
                case 8 -> Management.showFinancialRPInPeriod();
                case 9 -> Management.addNewService();
                case 10 -> Management.addService();
                case 11 -> Management.removeService();
                case 12 -> Management.editService();
                case 13 -> Management.addNewAdmin();
                case 14 -> Management.removeAdmin();
                case 15 -> Management.editAdmin();
                default -> System.exit(0);
            }
        }
    }

    public static void managePCs() {
        int choice = Integer.parseInt(input.nextLine());
        switch (choice){

        }
    }

    public static void manageService() {
        int choice = Integer.parseInt(input.nextLine());
        switch (choice){

        }
    }

    public static void manageAccount() {
        int choice = Integer.parseInt(input.nextLine());
        switch (choice){

        }
    }

    public static void manageIncome() {
        int choice = Integer.parseInt(input.nextLine());
        switch (choice){

        }
    }
}

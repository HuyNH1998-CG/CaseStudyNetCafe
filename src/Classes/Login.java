package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private static List<Admin> list = new ArrayList<>();

    public static void login() {
        Scanner input = new Scanner(System.in);
        boolean loggedin = false;
        while (!loggedin) {
            list = IOOperator.readAdmin("src/Files/Admins.txt");
            System.out.println("Hệ thống quản lý phòng net");
            System.out.println("Đăng nhập vào hệ thống");
            System.out.println("Username");
            String username = input.nextLine();
            System.out.println("Password");
            String password = input.nextLine();
            if (username.equalsIgnoreCase("ADMIN") && password.equals("admin")) {
                loggedin = true;
                AdminMenu.menu();
            } else {
                for (Admin admin : list) {
                    if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                        loggedin = true;
                        AdminMenu.menu();
                    }
                }
                System.out.println("Nhập sai username hoặc password");
            }
        }
    }

    public static List<Admin> getList() {
        return list;
    }

    public static void setList(List<Admin> list) {
        Login.list = list;
    }
}

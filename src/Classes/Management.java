package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    private static List<PC> pcList = new ArrayList<>();
    private static List<Service> serviceList = new ArrayList<>();
    private static List<Bill> billList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    private static final String online = "online";
    private static final String offline = "offline";

    public static List<PC> getPcList() {
        return pcList;
    }

    public static List<Service> getServiceList() {
        return serviceList;
    }

    public static List<Bill> getBillList() {
        return billList;
    }

    public static void addPC(String name) {
        PC pc = new PC(name);
        pcList.add(pc);
    }

    public static void showAllPC() {
        showAll();
        showDetail();
    }

    private static void showAll() {
        for (int i = 0; i < pcList.size(); i++) {
            System.out.println("Máy Số " + i + ":" +pcList.get(i).getName());
        }
    }

    private static void showDetail() {
        System.out.println("Bạn có muốn xem chi tiết máy không?");
        System.out.println("1. Có");
        System.out.println("2. Không");
        int choice = Integer.parseInt(input.nextLine());
        if (choice == 1) {
            int index = Integer.parseInt(input.nextLine());
            if(pcList.get(index).isOnline().equals(online)){
                System.out.println(pcList.get(index).displayOnline());
            } else {
                System.out.println("Offline ");
            }
        }
    }
    
    public static void removePC(){
        showAll();
        System.out.println("Nhập số của máy");
        int index = Integer.parseInt(input.nextLine());
        pcList.remove(index);
    }

    public static void editPC(){

    }

    
}

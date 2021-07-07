package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOOperator {
    public static void writeAdminFile(String path, List<Admin> admins) {
        try {
            FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(admins);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writePCFile(String path, List<PC> pc) {
        try {
            FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pc);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeServiceFile(String path, List<Service> services) {
        try {
            FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(services);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeIncome(String path, List<FinancialReport> financialReports) {
        try {
            FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(financialReports);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Admin> readAdmin(String path) {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            admins = (ArrayList<Admin>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("File not found or empty");
        }
        return admins;
    }


    public static ArrayList<PC> readPC(String path) {
        ArrayList<PC> pc = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            pc = (ArrayList<PC>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("File not found or empty");
        }
        return pc;
    }

    public static ArrayList<Service> readService(String path) {
        ArrayList<Service> sv = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sv = (ArrayList<Service>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("File not found or empty");
        }
        return sv;
    }

    public static ArrayList<FinancialReport> readIncome(String path) {
        ArrayList<FinancialReport> fp = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            fp = (ArrayList<FinancialReport>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("File not found or empty");
        }
        return fp;
    }
}

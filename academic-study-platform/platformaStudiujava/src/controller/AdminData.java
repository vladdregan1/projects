package controller;

public class AdminData {
    private static String[] adminUser = new String[10];
    private static String[] adminPass = new String[10];

    static {
        adminUser[0] = "admin1";
        adminPass[0] = "platforma123";
        adminUser[1] = "admin2";
        adminPass[1] = "platforma10";
    }

    public static String[] getAdminUser() {
        return adminUser;
    }

    public static String[] getAdminPass() {
        return adminPass;
    }
}


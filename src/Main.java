import menu.Menu;
import menu.MenuManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   GROCERY STORE MANAGEMENT SYSTEM");
        System.out.println("     WITH DATABASE INTEGRATION");
        System.out.println("=========================================");

        Menu menu = new MenuManager();
        menu.run();
    }
}


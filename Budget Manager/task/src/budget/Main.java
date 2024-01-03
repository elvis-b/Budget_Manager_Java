package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        MenuController menuController = new MenuController(scanner, productManager);
        PurchaseAnalysis purchaseAnalysis = new PurchaseAnalysis(scanner, productManager);
        menuController.setPurchaseAnalysis(purchaseAnalysis);
        menuController.displayMenu();
    }
}

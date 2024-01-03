package budget;

import java.util.Scanner;

public class MenuController {

    private final Scanner scanner;
    private static final String MENU_TEXT = """
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            5) Save
            6) Load
            7) Analyze (Sort)
            0) Exit""";

    private float income;
    private float totalSum;
    private final ProductManager productManager;

    private PurchaseAnalysis purchaseAnalysis;

    public MenuController(Scanner scanner, ProductManager productManager) {
        this.scanner = scanner;
        this.productManager = productManager;
    }

    public void setPurchaseAnalysis(PurchaseAnalysis purchaseAnalysis) {
        this.purchaseAnalysis = purchaseAnalysis;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }
    public void addIncome() {
        System.out.println("Enter income:");
        income = scanner.nextFloat();
        System.out.println("Income was added!");
        scanner.nextLine();
        System.out.println();
    }

    public void addPurchase() {
        while (true) {
            System.out.println("Choose the type of purchase");
            productManager.displayCategories();
            System.out.println("5) Back");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();
            if (categoryChoice == 5) {
                System.out.println();
                displayMenu();
                return;
            }
            System.out.println();
            System.out.println("Enter purchase name:");
            String productName = scanner.nextLine();
            System.out.println("Enter its price:");
            String price = scanner.nextLine();
            productManager.addPurchase(categoryChoice, productName, Float.parseFloat(price));
            income -= Float.parseFloat(price);
            totalSum += Float.parseFloat(price);
            System.out.println("Purchase was added!");
            System.out.println();
        }
    }

    public void displayPurchases() {
        if (productManager.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
        } else {
            while (true) {
                System.out.println("Choose the type of purchases");
                productManager.displayCategories();
                System.out.println("5) All");
                System.out.println("6) Back");
                int categoryChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (categoryChoice == 6) {
                    System.out.println();
                    return;
                }
                System.out.println();

                if (categoryChoice == 5) {
                    productManager.displayAllPurchases();
                    System.out.println("Total sum: $" + productManager.calculateTotalSum());
                } else {
                    productManager.displayPurchases(categoryChoice);
                }
                System.out.println();
            }
        }
    }


    public void displayBalance() {
        System.out.println("Balance: $" + String.format("%.2f", income));
        System.out.println();
    }

    public void exit() {
        System.out.println();
        System.out.println("Bye!");
        System.exit(0);
    }



    public void displayMenu() {
        System.out.println(MENU_TEXT);
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println();
                    addIncome();
                    System.out.println(MENU_TEXT);
                    break;
                case 2:
                    System.out.println();
                    addPurchase();
                    break;
                case 3:
                    System.out.println();
                    displayPurchases();
                    System.out.println(MENU_TEXT);
                    break;
                case 4:
                    System.out.println();
                    displayBalance();
                    System.out.println(MENU_TEXT);
                    break;
                case 5:
                    System.out.println();
                    FileController.save(this,productManager.getPurchases());
                    System.out.println(MENU_TEXT);
                    break;
                case 6:
                    System.out.println();
                    FileController.load(this, productManager);
                    System.out.println(MENU_TEXT);
                    break;
                case 7:
                    System.out.println();
                    purchaseAnalysis.analyzePurchases();
                    System.out.println(MENU_TEXT);
                    break;
                case 0:
                    exit();
            }
        }
    }
}

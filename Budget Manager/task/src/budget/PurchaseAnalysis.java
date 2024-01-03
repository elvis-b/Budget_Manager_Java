package budget;

import java.util.*;

public class PurchaseAnalysis {
    private final Scanner scanner;
    private final ProductManager productManager;

    public PurchaseAnalysis(Scanner scanner, ProductManager productManager) {
        this.scanner = scanner;
        this.productManager = productManager;
    }

    public void analyzePurchases() {
        while (true) {
            System.out.println("How do you want to sort?");
            System.out.println("1) Sort all purchases");
            System.out.println("2) Sort by type");
            System.out.println("3) Sort certain type");
            System.out.println("4) Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sortAllPurchases();
                    break;
                case 2:
                    sortPurchasesByType();
                    break;
                case 3:
                    sortCertainType();
                    break;
                case 4:
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void sortAllPurchases() {
        List<String> allPurchases = new ArrayList<>();

        for (List<String> categoryPurchases : productManager.getPurchases().values()) {
            allPurchases.addAll(categoryPurchases);
        }
        if (allPurchases.isEmpty()) {
            System.out.println();
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println();
            System.out.println("All: ");
            allPurchases.sort(new PurchaseComparator());
            for (String purchase : allPurchases) {
                System.out.println(purchase);
            }
            float totalSum = productManager.calculateTotalSum();
            System.out.println("Total: $" + String.format("%.2f", totalSum));
        }
        System.out.println();
    }
    private void sortPurchasesByType() {
        System.out.println();
        System.out.println("Types:");

        float totalSum = 0;

        // Creating a list of entries sorted by total sum in descending order
        List<Map.Entry<Integer, List<String>>> sortedEntries = productManager.getPurchases().entrySet().stream()
                .filter(entry -> productManager.calculateCategoryTotalSum(entry.getValue()) > 0)
                .sorted(Comparator.comparingDouble(entry -> -productManager.calculateCategoryTotalSum(entry.getValue())))
                .toList();

        for (Map.Entry<Integer, List<String>> entry : sortedEntries) {
            int category = entry.getKey();
            List<String> categoryPurchases = entry.getValue();
            float categoryTotalSum = productManager.calculateCategoryTotalSum(categoryPurchases);

            System.out.println(productManager.getCategoryName(category) + " - $" + String.format("%.2f", categoryTotalSum));
            totalSum += categoryTotalSum;
        }
        System.out.println("Total sum: $" + String.format("%.0f", totalSum));
        System.out.println();
    }

    private void sortCertainType() {
        System.out.println();
        System.out.println("Choose the type of purchase");
        productManager.displayCategories();
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        List<String> categoryPurchases = productManager.getPurchases().get(categoryChoice);
        if (categoryPurchases.isEmpty()) {
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println(productManager.getCategoryName(categoryChoice) + ":");
            categoryPurchases.sort(new PurchaseComparator());
            for (String purchase : categoryPurchases) {
                System.out.println(purchase);
            }
            float categoryTotalSum = productManager.calculateCategoryTotalSum(categoryPurchases);
            System.out.println("Total: $" + String.format("%.2f", categoryTotalSum));
        }
        System.out.println();
    }

    private static class PurchaseComparator implements Comparator<String> {
        @Override
        public int compare(String purchase1, String purchase2) {
            String[] parts1 = purchase1.split("\\$");
            String[] parts2 = purchase2.split("\\$");

            if (parts1.length >= 2 && parts2.length >= 2) {
                float price1 = Float.parseFloat(parts1[parts1.length - 1]);
                float price2 = Float.parseFloat(parts2[parts2.length - 1]);

                if (price1 > price2) {
                    return -1;
                } else if (price1 < price2) {
                    return 1;
                } else {
                    String name1 = parts1[0].trim();
                    String name2 = parts2[0].trim();
                    return name2.compareTo(name1);
                }
            } else {
                System.out.println("Error parsing purchase information");
                return 0;
            }
        }
    }








}

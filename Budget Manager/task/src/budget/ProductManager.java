package budget;

import java.util.*;


class ProductManager {

    private final Map<Integer, String> categories;
    private final Map<Integer, List<String>> purchases;

    public ProductManager() {
        categories = new HashMap<>();
        purchases = new HashMap<>();

        categories.put(1, "Food");
        categories.put(2, "Clothes");
        categories.put(3, "Entertainment");
        categories.put(4, "Other");

        for (int i = 1; i <= 4; i++) {
            purchases.put(i, new ArrayList<>());
        }
    }

    public void addPurchase(int category, String productName, float price) {
        String formattedPrice = String.format(Locale.US, "%.2f", Double.parseDouble(String.valueOf(price)));
        purchases.get(category).add(productName + " $" + formattedPrice);
    }

    public boolean isEmpty() {
        for (List<String> categoryPurchases : purchases.values()) {
            if (!categoryPurchases.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void displayCategories() {
        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            System.out.println(entry.getKey() + ") " + entry.getValue());
        }
    }

    public void displayPurchases(int category) {
        System.out.println(categories.get(category) + ":");

        List<String> categoryPurchases = purchases.get(category);

        if (categoryPurchases.isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            for (String purchase : categoryPurchases) {
                System.out.println(purchase);
            }

            // Display total sum only if there are items in the category
            float categoryTotalSum = calculateCategoryTotalSum(categoryPurchases);
            System.out.println("Total sum: $" + categoryTotalSum);
        }
    }

    public void displayAllPurchases() {
        System.out.println("All:");

        for (List<String> categoryPurchases : purchases.values()) {
            for (String purchase : categoryPurchases) {
                System.out.println(purchase);
            }
        }
    }

    public Map<Integer, List<String>> getPurchases() {
        return purchases;
    }

    public float calculateTotalSum() {
        float sum = 0;
        for (List<String> categoryPurchases : purchases.values()) {
            sum = getSum(sum, categoryPurchases);
        }
        return sum;
    }

    private float getSum(float sum, List<String> categoryPurchases) {
        for (String purchase : categoryPurchases) {
            String[] parts = purchase.split("\\$");
            if (parts.length >= 2) {
                sum += Float.parseFloat(parts[parts.length - 1]);
            } else {
                System.out.println("Error parsing purchase information: " + purchase);
            }
        }
        return sum;
    }

    float calculateCategoryTotalSum(List<String> categoryPurchases) {
        float sum = 0;
        sum = getSum(sum, categoryPurchases);
        return sum;
    }

    public String getCategoryName(int category) {
        return categories.get(category);
    }
}

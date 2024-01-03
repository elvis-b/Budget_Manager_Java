package budget;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileController {

    private static final String FILE_NAME = "purchases.txt";

    public static void save(MenuController menuController, Map<Integer, List<String>> purchases) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(menuController.getIncome());
            for (Map.Entry<Integer, List<String>> entry : purchases.entrySet()) {
                for (String purchase : entry.getValue()) {
                    writer.println(entry.getKey() + "|" + purchase);
                }
            }
            System.out.println("Purchases were saved!");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error saving purchases to file: " + e.getMessage());
            System.out.println();
        }
    }

    public static void load(MenuController menuController, ProductManager productManager) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            float remainingIncome = Float.parseFloat(reader.readLine());
            menuController.setIncome(remainingIncome); // Set the loaded remaining income
            String line;
            while ((line = reader.readLine()) != null) {
                int lastSpaceIndex = line.lastIndexOf(' ');
                if (lastSpaceIndex != -1) {
                    int category = Integer.parseInt(line.substring(0, lastSpaceIndex).split("\\|")[0].trim());
                    String purchaseName = line.substring(0, lastSpaceIndex).split("\\|")[1].trim();
                    float purchasePrice = Float.parseFloat(line.substring(lastSpaceIndex + 1).split("\\$")[1].trim());

                    productManager.addPurchase(category, purchaseName, purchasePrice);
                } else {
                    System.out.println("Error parsing purchase information: " + line);
                }
            }
            System.out.println("Purchases were loaded!");
            System.out.println();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading purchases from file: " + e.getMessage());
            System.out.println();
        }
    }
}

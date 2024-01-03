This project is from https://hyperskill.org/projects/76?track=12

This Java program serves as a budget management tool with a simple command-line interface. It allows users to track income, expenses, and analyze purchases. The program adheres to Object-Oriented Programming (OOP) principles, utilizing classes and encapsulation to organize and structure the code. Here's an overview of its key features and design:

Budget Management:

Users can add income, add purchases, view a list of purchases, and check the balance.
The income and total expenses are tracked, providing users with an overview of their financial status.
Data Structures:

The program employs various data structures, including Map to store categories and purchases, List to manage purchases within categories, and simple arrays to manage menu options.
Encapsulation:

The code is organized into classes (MenuController, ProductManager, PurchaseAnalysis, FileController) to encapsulate related functionalities and improve code readability and maintainability.
File Handling:

The program allows users to save and load their purchase data to/from a text file (purchases.txt).
FileController class manages the file-related operations, promoting separation of concerns.
Sorting and Analysis:

The PurchaseAnalysis class provides options to sort purchases:
Sort all purchases
Sort purchases by type
Sort a specific type of purchase
Purchase Sorting Comparator:

The PurchaseComparator class is a nested class within PurchaseAnalysis, implementing Comparator<String> to sort purchases based on their prices in descending order. It considers both price and purchase name for cases where prices are equal.
Design Patterns:

The program demonstrates a basic command-line menu-driven design pattern where user actions are captured through a menu controller (MenuController).
User Interaction:

The Scanner class is used for user input, providing a simple and interactive command-line interface.

How to Use:

Run the program.

Choose actions from the displayed menu to manage your budget.
Save and load your purchase data to/from purchases.txt for persistent tracking.

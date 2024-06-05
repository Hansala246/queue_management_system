import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FoodCenter {
    private static int stock = 50;
    private static String[] q1 = new String[2];  //Queue for cashier1
    private static String[] q2 = new String[3];  //Queue for cashier2
    private static String[] q3 = new String[5];  //Queue for cashier3


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 100:
                    viewAllQueues();  // Display the status of all queues
                    break;
                case 101:
                    viewAllEmptyQueues();  //Display the empty queues
                    break;
                case 102:
                    addCustomerToQueue(scanner);  // Add a customer to a queue
                    break;
                case 103:
                    removeCustomerFromQueue(scanner);  //Remove a customer from a queue
                    break;
                case 104:
                    removeServedCustomer();  // Remove the first served customer from any queues
                    break;
                case 105:
                    viewCustomersSorted();  // All customers display in alphabetical order
                    break;
                case 106:
                    storeProgramData();  // store program data in to a file
                    break;
                case 107:
                    loadProgramData();  //load program data from a file
                    break;
                case 108:
                    viewStock();  // Display remaining burgers stock quantity
                    break;
                case 109:
                    addBurgersToStock(scanner);  //Add burgers to stock
                    break;
                case 999:
                    System.out.println("Exiting program...");  //Exit from program
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");  //Display an error message for invalid choices
                    break;
            }
        }
    }
    private static void displayMenu() {   // Display the menu
        System.out.println();
        System.out.println("Foodies Fave Food Center Menu");
        System.out.println();
        System.out.println("100. View all Queues");
        System.out.println("101. View all Empty Queues");
        System.out.println("102. Add customer to a Queue");
        System.out.println("103. Remove a customer from a Queue (From a specific location)");
        System.out.println("104. Remove a served customer");
        System.out.println("105. View Customers Sorted in alphabetical order");
        System.out.println("106. Store Program Data into file");
        System.out.println("107. Load Program Data from file");
        System.out.println("108. View Remaining burgers Stock");
        System.out.println("109. Add burgers to Stock");
        System.out.println("999. Exit the Program");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    private static void viewAllQueues() {
        System.out.println();
        System.out.println("*****************");
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");

        for (int i=0;i<q3.length;i++) {      //Display status of each queues
            if(i<2){
                chackQ(q1[i], q2[i], q3[i]);
            } else if(i == 2){
                chackQ("empty", q2[i], q3[i]);
            } else {
                chackQ("empty", "empty", q3[i]);
            }
        }
        System.out.println("X –Not Occupied O –Occupied");
    }
    private static void chackQ(String name1, String name2, String name3) {
        String temp = "";

        if(name1 == null){
            temp += "\tX";   // Displaying "o" for Not occupied spot in each queue
        } else if(name1.equalsIgnoreCase("empty")) {
            temp += "\t ";
        } else {
            temp += "\tO";  // Displaying "X" for occupied spot in each queue
        }

        if(name2 == null){
            temp += "\tX";
        } else if(name2.equalsIgnoreCase("empty")) {
            temp += "\t ";
        } else {
            temp += "\tO";
        }

        if(name3 == null){
            temp += "\tX";
        } else if(name3.equalsIgnoreCase("empty")) {
            temp += "\t ";
        } else {
            temp += "\t0";
        }
        System.out.println(temp);

    }
    private static void viewAllEmptyQueues() {
        System.out.println("Empty Queues:");

        for (int i = 0; i < 5; i++) {
            if (i < q1.length && (q1[i] == null || q1[i].isEmpty())) {
                System.out.println("Queue 1 - Position " + (i + 1));    //Displaying empty position in queue1
            }
            if (i < q2.length && (q2[i] == null || q2[i].isEmpty())) {
                System.out.println("Queue 2 - Position " + (i + 1));    //Displaying empty position in queue2
            }
            if (i < q3.length && (q3[i] == null || q3[i].isEmpty())) {
                System.out.println("Queue 3 - Position " + (i + 1));    //Displaying empty position in queue3
            }
        }
    }
    private static void addCustomerToQueue(Scanner scanner) {
        System.out.println();
        System.out.print("Enter the queue number: ");  //Adding customer to the queue

        int queueNumber;
        try {
            queueNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the queue number.");
            scanner.nextLine(); // Consume the invalid input
            return;
        }


        if (queueNumber < 1 || queueNumber > 3) {
            System.out.println("Invalid queue number. Please try again.");
            return;

        }

        int maxCustomers = getMaxCustomersForQueue(queueNumber);
        String[] selectedQueue;

        switch (queueNumber) {
            case 1:
                selectedQueue = q1;
                break;
            case 2:
                selectedQueue = q2;
                break;
            case 3:
                selectedQueue = q3;
                break;
            default:
                System.out.println("Invalid queue number. Please try again.");
                return;
        }

        System.out.print("Enter customer name: ");  //adding customer name to the queue
        String customerName = scanner.nextLine();

        for (int i = 0; i < selectedQueue.length; i++) {
            if (selectedQueue[i] == null) {
                selectedQueue[i] = customerName;
                updateStock(-5);
                return;
            }
       }
    }

    private static void removeCustomerFromQueue(Scanner scanner) {
        System.out.println();
        System.out.print("Enter the queue number: "); //Enter the removing customers queue number
        int queueNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (queueNumber < 1 || queueNumber > 3) {
            System.out.println("Invalid queue number. Please try again.");
            return;
        }

        String[] queue; // Create a new array to store removed customers
        if (queueNumber == 1) {
            queue = q1.clone();
        } else if (queueNumber == 2) {
            queue = q2.clone();
        } else if (queueNumber == 3) {
            queue = q3.clone();
        } else {
            System.out.println("Invalid queue number. Please try again.");
            return;
        }

        if (queue.length == 0) {
            System.out.println("Queue " + queueNumber + " is empty. No customers to remove.");
            return;
        }

        System.out.print("Enter the customer position (0 to " + (queue.length - 1) + "): ");
        int customerPosition = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (customerPosition < 0 || customerPosition >= queue.length) {
            System.out.println("Invalid customer position. Please try again.");
            return;
        }

        String removedCustomer = queue[customerPosition];
        queue[customerPosition] = null;
        System.out.println("Customer '" + removedCustomer + "' removed from Queue " + queueNumber);

        if (queueNumber == 1) { // update queues with removed customer
            q1 = getUpdatedQueue(q1, queue);
        } else if (queueNumber == 2) {
            q2 = getUpdatedQueue(q2, queue);
        } else if (queueNumber == 3) {
            q3 = getUpdatedQueue(q3, queue);
        }
    }

    private static String[] getUpdatedQueue(String[] original, String[] removedCustomerQueue) {
        int count = 0;
        for (String customer : removedCustomerQueue) {
            if (customer != null) {
                count++;
            }
        }
        String[] updatedQueue = new String[count];
        int index = 0;
        for (String customer : removedCustomerQueue) {
            if (customer != null) {
                updatedQueue[index++] = customer;
            }
        }
        return updatedQueue;
    }
    private static void removeServedCustomer() {
        boolean customerRemoved = false;

        if (q1[0] != null) {
            String removedCustomer = q1[0];
            System.out.println("Customer '" + removedCustomer + "' removed from Queue 1");

            // Shift the remaining customers up in Queue 1
            for (int i = 0; i < q1.length - 1; i++) {
                q1[i] = q1[i + 1];
            }
            q1[q1.length - 1] = null; // Clear the last position

            customerRemoved = true;
        }

        if (q2[0] != null) {
            String removedCustomer = q2[0];
            System.out.println("Customer '" + removedCustomer + "' removed from Queue 2");

            // Shift the remaining customers up in Queue 2
            for (int i = 0; i < q2.length - 1; i++) {
                q2[i] = q2[i + 1];
            }
            q2[q2.length - 1] = null; // Clear the last position

            customerRemoved = true;
        }

        if (q3[0] != null) {
            String removedCustomer = q3[0];
            System.out.println("Customer '" + removedCustomer + "' removed from Queue 3");

            // Shift the remaining customers up in Queue 3
            for (int i = 0; i < q3.length - 1; i++) {
                q3[i] = q3[i + 1];
            }
            q3[q3.length - 1] = null; // Clear the last position

            customerRemoved = true;
        }

        if (!customerRemoved) {
            System.out.println("All queues are empty. No customers to remove.");
        }
    }


    private static void viewCustomersSorted() {
        List<String> allCustomers = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] queue;
            if (i == 0) {
                queue = q1;
            } else if (i == 1) {
                queue = q2;
            } else {
                queue = q3;
            }
            for (String customer : queue) {
                if (customer != null) {
                    allCustomers.add(customer);
                }
            }
        }

        Collections.sort(allCustomers);

        System.out.println("Customers Sorted in alphabetical order:");

        for (String customer : allCustomers) {
            System.out.println(customer);
        }
    }

    private static void storeProgramData() {

        try {
            //creating the instance of file
            File path = new File("programdata.txt");

            //passing file instance in filewriter
            FileWriter myWriter = new FileWriter(path); //Creating a file to write data
            for (String customer : q1) {
                myWriter.write(customer+"\n");
            }

            myWriter.write("\n");
            for (String customer : q2) {
                myWriter.write(customer+"\n");
            }

            myWriter.write("\n");
            for (String customer : q3) {
                myWriter.write(customer+"\n");
            }

            myWriter.write("\n");
            myWriter.write("Burger Stock: " + stock + "\n");

            //flushing the writer
            myWriter.flush();

            //closing the writer
            myWriter.close();

            System.out.println("Program data stored successfully.");


        } catch (IOException e) {
            System.out.println("Error occurred while storing program data: " + e.getMessage());
        }

    }

    private static void loadProgramData() {

        try {
            File file = new File("programdata.txt");
            Scanner file_reader = new Scanner(file);
            while (file_reader.hasNextLine()) {
                String programdata = file_reader.nextLine();
                System.out.println(programdata);
            }
            file_reader.close();
        } catch (IOException e) {
            System.out.println("Error while reading a file.");
            e.printStackTrace();
        }
    }

    private static void viewStock() {

        if (stock <= 10) {
            System.out.println("Warning: Stock is low!");
        }

        System.out.println("Remaining burgers stock: " + stock);
    }

    private static void addBurgersToStock(Scanner scanner) {
        System.out.print("Enter the number of burgers to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (quantity <= 0) {
            System.out.println("Invalid quantity. Please enter a positive number.");
            return;
        }

        updateStock(quantity);
        System.out.println(quantity + " burgers added to stock.");
    }

    private static int getMaxCustomersForQueue(int queueNumber) {
        switch (queueNumber) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 5;
            default:
                return 0;
        }
    }
    private static void updateStock(int quantity) {
        stock += quantity;  //Updating burgers stock

    }
}


//References-https://www.w3schools.com/java/
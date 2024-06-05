import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static FoodQueue []q;
    private static int queueNumber;
    public static void main(String[] args) throws IOException {
        q=new FoodQueue[3] ;
        q[0]= new FoodQueue(2);
        q[1]= new FoodQueue(3);
        q[2]= new FoodQueue(5);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 100:
                    viewAllQueues();
                    break;
                case 101:
                    viewAllEmptyQueues();
                    break;
                case 102:
                    addACustomer(scanner);
                    break;
                case 103:
                    removeCustomer(scanner);
                    break;
                case 104:
                    removeServedCustomer();
                    break;
                case 105:
                    sortCustomers();
                    break;
                case 106:
                    storeInFile();
                    break;
                case 107:
                    loadFromFile();
                    break;
                case 108:
                    System.out.println(q[0].getStock());
                    break;
                case 109:
                    System.out.println("Enter value :");
                    int a=scanner.nextInt();
                    q[0].setStock(a);
                    break;
                case 110:
                    getIncomes();
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

    // Display the menu
    private static void displayMenu() {
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
        System.out.println("110. Income of the queues.");
        System.out.println("999. Exit the Program");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    private static void getIncomes(){
        System.out.println("Income of the Queue 1 : "+(q[0].getNoOfBurgersSold())*650);
        System.out.println("Income of the Queue 2 : "+(q[1].getNoOfBurgersSold())*650);
        System.out.println("Income of the Queue 3 : "+(q[2].getNoOfBurgersSold())*650);
    }

    private static void loadFromFile() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader("Results.txt"));
        String line;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
    }
    private static void viewAllEmptyQueues(){
        Customer [] c4=q[0].getAll();
        Customer [] c5=q[1].getAll();
        Customer [] c6=q[2].getAll();

        if(c4==null){
            System.out.println("Queue 1 is empty.");
        }
        if(c5==null){
            System.out.println("Queue 2 is empty.");
        }
        if(c6==null) {
            System.out.println("Queue 3 is empty.");
        }
    }

    private static void addACustomer(Scanner scanner){
        System.out.print("Enter the Queue number (1 or 2 or 3): ");  //Adding customer to the queue

        try {
            queueNumber = scanner.nextInt();
            while (true){
                if(queueNumber==1 || queueNumber==2 || queueNumber==3){
                    break;
                }else{
                    System.out.println("Enter a valid value (1 or 2 or 3) :");
                    queueNumber = scanner.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the queue number.");
            scanner.nextLine(); // Consume the invalid input
            return;
        }
        if(queueNumber==1){
            System.out.println(q[0].enQueue());
        }else if(queueNumber==2){
            System.out.println(q[1].enQueue());
        }else if (queueNumber==3){
            System.out.println(q[2].enQueue());
        }
        queueNumber=0;
    }

    public static void removeServedCustomer(){
        boolean remove=false;
        Customer [] c7=q[0].getAll();
        Customer [] c8=q[1].getAll();
        Customer [] c9=q[2].getAll();
        if(c7!=null && c7[0].fName!=null){
            System.out.println(c7[0].fName+" Removed.");
            c7[0]=c7[1];
            c7[1]=null;
            remove=true;
        }
        if(c8!=null&& c8[0].fName!=null){
            System.out.println(c8[0].fName+" Removed.");
            for (int i = 0; i < 1; i++) {
                c8[i]=c8[i+1];
            }
            remove=true;
        }
        if(c9!=null && c9[0].fName!=null){
            System.out.println(c9[0].fName+" Removed.");
            for (int i = 0; i < 3; i++) {
                c9[i]=c9[i+1];
            }
            remove=true;
        }
        if(!remove){
            System.out.println("All queues are empty.");
        }
    }

    private static void removeCustomer(Scanner scanner){
        System.out.println();
        System.out.print("Enter the queue number: "); //Enter the removing custormers queue number
        queueNumber = scanner.nextInt();
        while (true){
            if(queueNumber==1 || queueNumber==2 || queueNumber==3){
                break;
            }else{
                System.out.println("Enter a valid value (1 or 2 or 3) :");
                queueNumber = scanner.nextInt();
            }
        }
        if(queueNumber==1){
            System.out.println("Customer at the place "+q[0].removeFromSpecificPlace(scanner)+"in the queue 1 removed.");
        }else if(queueNumber==2){
            System.out.println("Customer at the place "+q[1].removeFromSpecificPlace(scanner)+"in the queue 2 removed.");
        }else if (queueNumber==3){
            System.out.println("Customer at the place "+q[2].removeFromSpecificPlace(scanner)+"in the queue 3 removed.");
        }
        queueNumber=0;
    }

    private static void sortCustomers(){
        Customer [] c10=q[0].getAll();
        Customer [] c11=q[1].getAll();
        Customer [] c12=q[2].getAll();
        List<String> allCustomers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if(i<2){
                if(c10!=null ){
                    try {
                        allCustomers.add(c10[i].fName);
                    }catch( Exception e){
                    }

                }
                if(c11!=null){
                    try {
                        allCustomers.add(c11[i].fName);
                    }catch( Exception e){
                    }
                }
                if(c12!=null){
                    try {
                        allCustomers.add(c12[i].fName);
                    }catch( Exception e){
                    }
                }
            }else if(i<3 ){

                if(c11!=null){
                    try {
                        allCustomers.add(c11[i].fName);
                    }catch( Exception e){
                    }
                }
                if(c12!=null){
                    try {
                        allCustomers.add(c12[i].fName);
                    }catch( Exception e){
                    }
                }
            }else {
                if(c12!=null){
                    try {
                        allCustomers.add(c12[i].fName);
                    }catch( Exception e){
                    }
                }
            }
        }
        int n=allCustomers.size();
        for (int i = 0; i < n-1; i++) {
            int minIndex=i;
            for (int j = i+1; j <n; j++) {
                if(allCustomers.get(j).compareTo(allCustomers.get(minIndex))<0){
                    minIndex=j;
                }
            }
            //swap the elements
            String temp=allCustomers.get(minIndex);
            allCustomers.set(minIndex, allCustomers.get(i));
            allCustomers.set(i,temp);
        }
        for (String name:
                allCustomers) {
            System.out.println(name);
        }
    }

    private static void storeInFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Results.txt"));
            Customer [] c13=q[0].getAll();
            Customer [] c14=q[1].getAll();
            Customer [] c15=q[2].getAll();
            for (int i = 0; i < q[0].getCurrentIndex()+1; i++) {
                try {
                    writer.write(c13[i].fName+","+c13[i].lName+","+c13[i].noOfBurgers+"\n");
                }catch (Exception e){
                    continue ;
                }
            }
            for (int i = 0; i < q[1].getCurrentIndex()+1; i++) {
                try {
                    writer.write(c14[i].fName+","+c14[i].lName+","+c14[i].noOfBurgers+"\n");
                }catch (Exception e){
                    continue ;
                }
            }
            for (int i = 0; i < q[2].getCurrentIndex()+1; i++) {
                try {
                    writer.write(c15[i].fName+","+c15[i].lName+","+c15[i].noOfBurgers+"\n");
                }catch (Exception e){
                    continue ;
                }
            }
            writer.write("Stock left :"+q[0].getStock());
            writer.close();
            System.out.println("Program data Stored Successfully");
        }catch (IOException e){
            System.out.println("an error occurred when writing the file.");
            e.printStackTrace();
        }
    }
    private static void viewAllQueues(){
        System.out.println();
        System.out.println("*****************");
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");
        Customer [] c1=q[0].getAll();
        Customer [] c2=q[1].getAll();
        Customer [] c3=q[2].getAll();
        for (int i = 0; i < 5; i++) {
            if(i<2){
                try {
                    System.out.print((c1[i].fName!=null?"O":"X")+"\t");
                }catch( Exception e){
                    System.out.print("X\t");
                }

                try {
                    System.out.print((c2[i].fName!=null?"O":"X")+"\t");
                }catch( Exception e){
                    System.out.print("X\t");
                }
                try {
                    System.out.println((c3[i].fName!=null?"O":"X")+"\t");
                }catch( Exception e){
                    System.out.println("X\t");
                }
            }else if(i<3 ){
                try{
                    System.out.print((c2[i].fName!=null?"\tO":"\tX")+"\t");
                }catch( Exception e){
                    System.out.print("\tX\t");
                }
                try {
                    System.out.println((c3[i].fName!=null?"O":"X")+"\t");
                }catch( Exception e){
                    System.out.println("X\t");
                }
            }else {
                try {
                    System.out.println((c3[i].fName!=null?"\t\tO":"\t\tX")+"\t");
                }catch( Exception e){
                    System.out.println("\t\tX");
                }
            }
        }

        System.out.println("X- Not Occupied O - Occupied.");
    }



}

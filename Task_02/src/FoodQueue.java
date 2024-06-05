import java.util.Scanner;

public class FoodQueue {
    private Customer[] customers;
    private int nextIndex;
    private int size;
    private int noOfSoldBurgers=0;
    public static int stock=50;
    FoodQueue(int size){
        this.size=size;
        customers=new Customer[size];
        nextIndex=-1;
    }
    //get size of the queue
    public int getSize(){
        return size;
    }


    //add new customer
    public String enQueue(){
        if(size==nextIndex+1){
            return "This Queue is Full";
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Customer First Name :");
        String fName=scanner.next();
        System.out.println("Enter Customer Last Name :");
        String lName=scanner.next();
        System.out.println("Enter No of Burgers Customer need :");
        int noOfBurgers=scanner.nextInt();
        if(stock<noOfBurgers){
            System.out.println("Only "+stock+" burgers left. Enter another value :");
            noOfBurgers=scanner.nextInt();
        }else{
            stock-=noOfBurgers;
            noOfSoldBurgers+=noOfBurgers;

        }

        customers[++nextIndex]=new Customer(fName,lName,noOfBurgers);
        return "Customer Added to the Queue";
    }

    public String removeFromSpecificPlace(Scanner scanner){
        System.out.println("Enter the place no :");
        int place=scanner.nextInt();
        if(place-1>size){
            System.out.println("Enter a number less than or equal to "+size+" :");
            place=scanner.nextInt();
        }
        customers[place-1]=customers[place];
        for (int i = place; i <nextIndex ; i++) {
            customers[i]=customers[i+1];
        }
        nextIndex--;
        return place+"";

    }

    public int getNoOfBurgersSold(){
        return noOfSoldBurgers;
    }

    //view all
    public Customer [] getAll(){
        if(nextIndex!=-1){
            return customers;
        }
        return null;
    }

    //return remaining stock
    public int getStock(){
        return stock;
    }

    public void setStock(int a){
        stock+=a;
    }

    public int getCurrentIndex(){
        return nextIndex;
    }
}

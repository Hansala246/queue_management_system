public class WaitingList {
    String fName; //first name
    String lName; //last name
    int noOfBurgers; // no of burgers required
    WaitingList next;

    WaitingList(String fName,String lName,int noOfBurgers){
        this.fName=fName;
        this.lName=lName;
        this.noOfBurgers=noOfBurgers;
    }
}

package task2;
import java.util.Scanner;

public class passenger {

    //initializing variables
    private static final Scanner input = new Scanner(System.in);
    String First_Name;
    String Last_Name;
    double Expenses;
    
    // No argument constructor
    public passenger() 
    {
        
    }
    
    // All argument constructor
    public passenger(String First_Name, String Last_Name, double Expenses)
    {
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Expenses = Expenses;
        
    }
    //getters
     public String getFirst_Name()
    {
        return this.First_Name ;
    }
     
    public String getLast_Name()
    {
        return this.Last_Name ;
    }
     
    public double getExpenses()
    {
        return this.Expenses ;
    }
    //setters
    public void setFirstName()
    {
        System.out.println("Enter first name of passenger :");
        this.First_Name = input.next();
    }
    
    public void setLastName()
    {
        System.out.println("Enter last name of passenger :");
        this.Last_Name = input.next();
    }
    
    public void setExpenses()
    {
        System.out.println("Enter passenger expenses :");
        if(input.hasNext())
        {
            this.Expenses = input.nextDouble();
        }
        else
        {
            System.out.println("Invalid answer.");
        }
    }
    //methods
    public void ClearPassengerInformations()
    {
        this.First_Name = null;
        this.Last_Name = null;
        this.Expenses = 0;
    }
    
    
    
}

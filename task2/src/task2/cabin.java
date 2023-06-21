package task2;

import java.util.*;


public class cabin {

       //initializing variables
    private String cname;
    private int cnumber;
    private int Cpassengers;
    private passenger[] myPassenger = new passenger[3];
    private static final Scanner input = new Scanner(System.in);

        
    // No argument constructor
    public cabin() 
    {

    }
    
    // All argument constructor
    public cabin(int cnumber, String cname, int Cpassengers)
    {
        this.cname = cname;
        this.cnumber = cnumber;
        this.Cpassengers = Cpassengers;
    }
        
    //setters
    public void setCName(String cname)
    {
        this.cname = cname;
    }
    
    public void setCNumber(int cnumber)
    {
        this.cnumber = cnumber;
    }

    public void setBookCName()
    {
        System.out.println("Please put a name for cabin " + this.cnumber +" : " ) ;
        this.cname = input.next();
        System.out.println("Named cabin " + this.cnumber + " to " + this.cname + ".");
    }
    
    public void setpassengersInCabin()
    {
        boolean loopS = true ;
        //creating passenger objects
        for(int i=0;i < this.myPassenger.length;i++)
        {
            this.myPassenger[i] = new passenger();
        }
        while(loopS)
        {
            System.out.println("Enter total number of passengers to " + this.cname + " cabin.");
            if (input.hasNextInt()) 
            {
                this.Cpassengers = input.nextInt();
                //checking user input within the array length of passenger objects
                if((this.Cpassengers > 0) & (this.Cpassengers <= this.myPassenger.length))
                {
                    //creating passenger details
                    for(int i = 0; i < this.Cpassengers; i++)
                    {
                        System.out.println("Passenger " + (i+1) + " Informations.");
                        this.myPassenger[i].setFirstName();
                        this.myPassenger[i].setLastName();
                        this.myPassenger[i].setExpenses();
                    }
                    break;    
                }
                else
                {
                    System.out.println("Sorry! Minimum 1 passengers, Maximum " + this.myPassenger.length + " passengers only in one cabin.");

                    while (true) 
                    {
                        System.out.println("Do you want to add passengers again ?  \nEnter 'y' for yes or 'n' to no:");
                        String reply = input.next().toUpperCase();
                        switch (reply) 
                        {
                            case "Y":
                                break ;
                            case "N":
                                this.cname = "unreserved";
                                this.Cpassengers = 0;
                                loopS = false;
                                break;
                            default:
                                System.out.println("Invalid answer.");
                                break;
                        }
                        break;
                    } 
                }
            } 
            else 
            {
                System.out.println("Invalid answer");
                input.next();
            }  
        }  
    }

    //getters
    public String getCName()
    {
        return this.cname ;
    }

    public int getCNumber() 
    {
        return this.cnumber;
    }
    
    public int getCpassengers()
    {
        return this.Cpassengers;
    }

    public String[] getPassengers_info()
    {
        String[] passengerDetails = new String[this.Cpassengers];
        //getting passengers details one by one and adding to a static array
        for(int x = 0; x < this.Cpassengers ; x++)
        {
            String first_name = myPassenger[x].getFirst_Name();
            String last_name = myPassenger[x].getLast_Name();
            double expenses = myPassenger[x].getExpenses();

            passengerDetails[x] = (x+1) + "." + first_name + " " + last_name  +" - $" + expenses + "\n";
        }
        return passengerDetails;
    }
    //methods
    public String[] PassengersAlphabaticalOrder()
    {
        String[] canameList = new String[this.Cpassengers];
        //temparaly rename cabin names and appending to a list
        for(int x = 0; x < this.Cpassengers ; x++)
        {
            canameList[x] = this.myPassenger[x].getFirst_Name() + " " +  this.myPassenger[x].getLast_Name() + " in cabin " + this.cnumber + " named " + this.cname + ".";
            
        }
        return canameList;
    }
    
    public void removePassenger()
    {
        boolean loopR = true;
        
        if (this.cname.equals("unreserved"))
        {
            System.out.println("Cabin " + this.cnumber + " is occupied by none. ");
        }
        else
        {
            while(loopR)
            {
                System.out.println("Which number of passengers should be removed?");
                
                if(input.hasNextInt())
                {
                    int PassengerNum = input.nextInt();
                    //checking user input within the array length of passenger objects
                    if((PassengerNum > 0) & (PassengerNum <= this.myPassenger.length))
                    {
                        this.myPassenger[PassengerNum - 1].ClearPassengerInformations();
                        this.Cpassengers -= 1;
                        //shifting below names upwards
                        for(int i = PassengerNum - 1; i <  this.Cpassengers; i ++)
                        {
                            this.myPassenger[i] = this.myPassenger[i + 1];
                        }

                        System.out.println("Removed passenger " + PassengerNum +" from the cabin "+ this.cnumber + " named " + this.cname +"." );

                        if(this.Cpassengers == 0)
                        {
                            this.cname = "unreserved";
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("Sorry! Minimum 1 passengers, Maximum " + this.myPassenger.length + " passengers only in one cabin.");
                        boolean loopQ = true;
                        while (loopQ) 
                        {
                            System.out.println("Do you want to remove passengers from the cabin once more?" +  this.cnumber +"? \nEnter 'y' for yes or 'n' to no:");
                            String reply = input.next().toUpperCase();
                            switch (reply) 
                            {
                                case "Y" :
                                    loopQ = false;
                                    break;
                                case "N" :
                                    loopQ = false;
                                    loopR = false;
                                    break;
                                default : 
                                    System.out.println("Invalid answer.");
                            }   
                        }
                    } 
                }
                else 
                {
                    System.out.println("Invalid answer");
                    input.next();
                } 
                
            }
        } 
    }
    
    public void viewCabinInformations()
    {
        if(this.cname.equals("unreserved"))
        {
            System.out.println("Cabin " + this.cnumber + " occupied by none.");
        }
        else
        {
            System.out.println("Cabin " + this.cnumber + " named " + this.cname + " occupied by " + this.Cpassengers + " passengers. Passengers names are," );
            //view passengers details
            for(int i = 0; i < this.Cpassengers; i++)
            {
                String first_name = this.myPassenger[i].getFirst_Name();
                String last_name = this.myPassenger[i].getLast_Name();
                System.out.println((i+1) + "." + first_name + " " + last_name);
            }  
        }
    }
    public void ClearCabinInformations()
    {
        if (this.cname.equals("unreserved"))
        {
            System.out.println("Cabin " + this.cnumber + " is occupied by none.");
        }
        else
        {
            this.cname ="unreserved"; 
            for (int x = 0;x < this.Cpassengers; x++)
            {
                this.myPassenger[x].ClearPassengerInformations();
            }
           
            this.Cpassengers = 0;
            System.out.println("Clear the cabin "+ this.cnumber +".");
        }
        
    }
    
    public int FindPassengerByName(String Passenger_name)
    { 
        int noName = 0;
        for(int i = 0; i < this.Cpassengers ; i++)
        {
            String first_name = myPassenger[i].getFirst_Name();
            String last_name = myPassenger[i].getLast_Name();
            if(first_name.equalsIgnoreCase(Passenger_name) | last_name.equalsIgnoreCase(Passenger_name))
            {
                System.out.println((i+1) + ". " + Passenger_name + " - cabin number is " + this.cnumber + ".");
                noName += 1;
            }
        }
        return noName;
    }
    
    public double ExpensesOfPassengers()
    {
        double Total = 0;
        int loopE = 0;
        
        for(int i = 0; i < this.Cpassengers ; i++)
        {
            String first_name = myPassenger[i].getFirst_Name();
            String last_name = myPassenger[i].getLast_Name();
            double expenses = myPassenger[i].getExpenses();
            
            if(loopE == 0)
            {
                System.out.println("Cabin " + this.cnumber + " named " + this.cname + ". Expenses per passenger.");
                loopE +=1;
            }
            
            System.out.println((i+1) + "." + first_name + " " + last_name  +" - $" + expenses);
            
            Total += expenses;
            
        }
        return Total;
    }
    public void setpassager()
    {
        for (int x=0;x<(this.Cpassengers);x++)
        {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Passenger " +(x+1));
            this.myPassenger[x].setFirstName();
            this.myPassenger[x].setLastName();
            this.myPassenger[x].setExpenses();
        }
    }

}

package task2;

import java.util.*;
import java.io.FileWriter;              // Import the FileWriter class
import java.io.IOException;             // Import the IOException class to handle errors
import java.io.File;                    // Import the File class
import java.io.FileNotFoundException;   // Import this class to handle errors
import java.util.Date;


public class Task2 {
    public static Scanner input = new Scanner(System.in);

   
    public static void main(String[] args) {
        cabin[] ship = new cabin[12];
        
        //initialise
        queue queue = new queue(5);
        initialise(ship); 
        int arrlen = ship.length;
        String answer;
        int cabinNum = 0;
        int passengers_total = 0;
        boolean loop=true;
        
        Scanner input = new Scanner(System.in); 
        
        while (loop){
            String select;
            String loop_input;

            while (true){
                System.out.println("-------------Welcome to our boarding system-------------");
            
                System.out.println("Select one of the options : ");
               
                System.out.println(" Enter 'A' to Add a customer to a cabin"); 
                
                System.out.println(" Enter 'V' to View all cabins");
                
                System.out.println(" Enter 'E' to Display Empty cabins");
               
                System.out.println(" Enter 'D' to Delete customer from cabin");
               
                System.out.println(" Enter 'F' to Find cabin from customer name");
              
                System.out.println(" Enter 'S' to Store program data into File");
                
                System.out.println(" Enter 'L' to Load program data from File");
               
                System.out.println(" Enter 'O' to View passengers Ordered Alphabetically by name.");
                
                System.out.println(" Enter 'T' to  print the expenses per passenger and total expenses of all passengers.");
                
                System.out.println("-------------------------------------------------------------");


                select=input.next().toUpperCase();
                //selection
                if(select.equals("A")||select.equals("V")||select.equals("E")||select.equals("D")||select.equals("F")||select.equals("S")||select.equals("L")||select.equals("O")||select.equals("T")){
                    break;
                }else{
                    System.out.println("Invalid selection, Please try again!");
                }


            }
            //performing each of the options above that the user has chosen
            switch(select)
            {
                case "A" : 
                    //customers are assigned to cabins and given a name
                    while (cabinNum != arrlen)
                    {
                        int loopz = 0 ;
                        for(int i = 0;i < arrlen; i++)
                        {
                            if(ship[i].getCName().equalsIgnoreCase("unreserved"))
                            {
                                break;
                            }
                            else
                            {
                                loopz += 1;
                            }
                        }
                        if(loopz == arrlen)
                        {
                            //allocating passengers to queue
                            System.out.println("Enter name for a cabin to add to the queue ") ;
                            String cabin_name = input.next();
                            System.out.println("Enter total number of passengers in the cabin") ;
                            int total_customers = input.nextInt();
                            cabin temp = new cabin(0, cabin_name, total_customers);
                            queue.enqueue(temp);
                        }
                        
                        System.out.println();
                        //choosing a specific cabin
                        while (true)
                        {
                            System.out.println("Enter cabin number you want to book (0-" + (arrlen-1) + ") or " + arrlen + " to stop booking: " );
                            if (input.hasNextInt()) 
                            {
                                cabinNum = input.nextInt();
                                //Verify if the cabin number entered by the user is within the cabin object array's length.
                                if ((cabinNum >= 0) & (cabinNum <= arrlen))
                                {
                                    break;
                                } 
                                else 
                                {
                                    System.out.println("Sorry! out of limits.");	
                                }
                            } 
                            else 
                            {
                                System.out.println("Invalid answer");
                                input.next();
                            } 
                        }
                        break;                           
                    }
                    if (cabinNum!=12) 
                    {
                        //customers are assigned to cabins
                        if(ship[cabinNum].getCName().equals("unreserved"))
                        {
                            ship[cabinNum].setBookCName(); 
                            ship[cabinNum].setpassengersInCabin();

                            int passengersInCabin = ship[cabinNum].getCpassengers();
                            passengers_total += passengersInCabin;
                        }
                        else 
                        {
                            System.out.println("Cabin "+ ship[cabinNum].getCNumber() + " is already occupied.");
                        }
                    }
                    else
                    {
                        break;
                    }
                    break;
                        
                case "V" : 
                    //view all cabins
                    for (int x = 0; x < arrlen; x++) 
                    { 
                        ship[x].viewCabinInformations();
                    } 
                    break;
                        
                case "E" :
                    //display empty cabins 
                    for (int x = 0; x < arrlen; x++ ) 
                    { 
                        if (ship[x].getCName().equals("unreserved"))
                        {
                            System.out.println("Cabin " + x + " is empty"); 
                        }
                    } 
                    break;
                        
                case "D" :
                    //delete customer from cabin 
                    while(true)
                    {
                        System.out.println("select a option.");
                        System.out.println("Enter 1 - All passengers in the cabin should be removed..");
                        System.out.println("Enter 2 - Specific passengers should be removed from the cabin.");
                        
                        if(input.hasNextInt())
                        {
                            int option = input.nextInt();
                            switch (option) 
                            {
                                case 1:
                                    while(true)
                                    {
                                        System.out.println("Enter cabin number to remove:");
                                        if(input.hasNextInt())
                                        {
                                            cabinNum = input.nextInt();
                                            //check user entered cabin number is within the array length of cabin objects
                                            if ((cabinNum >= 0) & (cabinNum <= arrlen)) 
                                            {
                                                ship[cabinNum].ClearCabinInformations();
                                                ship[cabinNum] = queue.dequeue();
                                                ship[cabinNum].setCNumber(cabinNum);
                                                break;
                                            }
                                            else 
                                            {
                                                System.out.println("Sorry! out of limits.");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Invalid answer");
                                            input.next();
                                        }
                                        
                                    }
                                    break;
                                case 2:
                                    while(true)
                                    {
                                        System.out.println("Enter the cabin number from which the passenger should be removed.:");
                                        if(input.hasNextInt())
                                        {
                                            cabinNum = input.nextInt();
                                            //check user entered cabin number is within the array length of cabin objects
                                            if ((cabinNum >= 0) & (cabinNum <= arrlen)) 
                                            {
                                                ship[cabinNum].removePassenger();
                                                break;
                                            }
                                            else 
                                            {
                                                System.out.println("Sorry! out of limits.");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Invalid answer");
                                            input.next();
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid answer. Enter 1 or 2");
                                    continue;
                            }
                        break;
                        }
                        else 
                        {
                            System.out.println("Invalid answer");
                            input.next();
                        } 
                    }
                    break;

                        
                case "F" :
                    //Find cabin from customer name
                    String pName;
                    int noName=0;

                    System.out.println("Enter name to Search for cabin:" );
                    pName = input.next(); 
                    
                    for(int i =0; i < arrlen; i++)
                    {
                        noName += ship[i].FindPassengerByName(pName); 
                    }
                    if (noName == 0)
                    {
                        System.out.println("Entered name can't find.");
                    }
                    
                    break;
                        
                case "T" :
                    //All passengers expences and expences per passenger   
                    double AllEx = 0;
                    for(int i = 0; i < arrlen; i++)
                    {
                        double Expenses = ship[i].ExpensesOfPassengers();
                        AllEx += Expenses;
                    }
                    System.out.println( "$."+ AllEx+ " is the all " + passengers_total + " passengers Expenses. ");
                        
                    break;
                        
                case "S" :
                    //Store program data into file
                    
                    try 
                    {
                        FileWriter cData = new FileWriter("store.txt");
                        Date date = new Date();
                        cData.write(String.format("Store updated Date/Time : %tc", date )+"\n");
                        
                        for(int i = 0; i < arrlen; i++)
                        {                           
                            String[] passengerDetails = new String[ship[i].getCpassengers()];
                            passengerDetails = ship[i].getPassengers_info();
                            
                            
                            cData.write("Cabin "+ i + " - " + ship[i].getCName() + "\n");
                            
                            for (int x = 0; x < ship[i].getCpassengers() ; x++)
                            {
                                cData.write(passengerDetails[x]);
                            }
                        }
                        cData.close();
                    }
                    catch (IOException e) 
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    
                    System.out.println("Successfully wrote to the file.");
                    break;
                        
                case "L" : 
                    //Load program data from file
                    try 
                    {
                        File cData = new File("store.txt");
                        Scanner myReader = new Scanner(cData);

                        while (myReader.hasNextLine()) 
                        {
                            String data = myReader.nextLine();
                            System.out.println(data);
                        }
                        myReader.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;
                        
                case "O" :
                    //View passengers Ordered alphabetically by name
                    boolean isordered;
                    String[] AllPassengersDetails = new String[passengers_total];
                    int loopA = 0;

                    System.out.println("The names are listed in alphabetical order :");
                    
                    //cabin names renamed on a temporary basis
                    for (int x = 0;x < arrlen; x++)
                    {
                        //static array renames on atempary basis to get passenger details 
                        String[] passengersDetailsACabin = new String[ship[x].getCpassengers()];
                        
                        passengersDetailsACabin = ship[x].PassengersAlphabaticalOrder();
                        
                        for(int i = 0; i < ship[x].getCpassengers(); i++)
                        {
                            AllPassengersDetails[loopA] = passengersDetailsACabin[i];
                            loopA += 1;
                        }  
                    }
                    //sorting and renaming the cabin for the time being
                    for(int k = 0; k < arrlen - 1; k++)
                    {
                        isordered = false;
                        for(int j = 0;j < passengers_total - 1; j++)
                        {
                            if ( (AllPassengersDetails[j].compareTo(AllPassengersDetails[j +1] ) ) > 0)
                            {
                                String temp2 = AllPassengersDetails[j];
                                AllPassengersDetails[j] = AllPassengersDetails[j + 1];
                                AllPassengersDetails[j + 1] = temp2;
                                isordered = true;
                            }
                        }

                        if (isordered == false)
                        {
                            break;
                        }
                    }
                    //view sorted cabin names
                    for (int i = 0; i < passengers_total; i++) 
                    {
                        if(!(AllPassengersDetails[i].contains("unreserved")))
                        {
                            System.out.println(AllPassengersDetails[i]);
                        }	
                    }
                    break;         
            }
            
            OUTER:
            while (true) 
            {
                System.out.println();
                System.out.println("Do you want to try again ? \nEnter 'y' for yes or 'n' to no:");
                answer = input.next().toUpperCase();
                switch (answer) 
                {
                    case "Y":
                        break OUTER;
                    case "N":
                        System.exit(0);
                    default:
                        System.out.println("Invalid answer.");
                        break;
                }
            }
        } 
    }
    //methods
    private static void initialise(cabin hotelRef[] ) 
    { 
        for(int i = 0; i < hotelRef.length; i++)
        {
            hotelRef[i] = new cabin();
        }
        for (int x = 0; x < hotelRef.length; x++ )
        {
            hotelRef[x].setCNumber(x);
            hotelRef[x].setCName("unreserved"); 
        }
    } 
}
        

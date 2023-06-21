
package task1;

import java.util.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.File;
import java.io.FileNotFoundException;


public class Task1 {
    public static Scanner input = new Scanner(System.in);

    
    public static void main(String[] args) {
        String[] ship = new String[12];
        //initialise
        initialise(ship); 
        boolean loop=true;
        
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
                
                System.out.println("-------------------------------------------------------------");


                select=input.next().toUpperCase();
                //selection
                if(select.equals("A")||select.equals("V")||select.equals("E")||select.equals("D")||select.equals("F")||select.equals("S")||select.equals("L")||select.equals("O")){
                    break;
                }else{
                    System.out.println("Invalid selection, Please try again!");
                }


            }

            //performing each of the options above that the user has chosen
            switch (select) {
                case "A":
                    AddsCustomerToCabin(ship);
                    break;
                case "V":
                    ViewsAllCabins(ship);
                    break;
                case "D":
                    DeleteCustomeFromCabin(ship);
                    break;
                case "E":
                    DisplayEmptyCabins(ship);
                    break;
                case "F":
                    FindCabinFromCustomerName(ship);
                    break;
                case "S":
                    StoreProgramDataIntoFile(ship);
                    break;
                case "L":
                    LoadProgramDataFromFile(ship);
                    break;
                case "O":
                    ViewPassengersOrderedAlphabeticallyByName(ship);
                    break;
                default:
                    break;
            }
            
            OUTER:
            while (true) {
                System.out.println("Do you want another selection? \nEnter 'y' for yes or 'n' to no:");
                loop_input=input.next().toUpperCase();
                switch (loop_input) {
                    case "Y":
                        loop=true;
                        break OUTER;
                    case "N":
                        System.out.println("Thank you for joining us.");
                        loop=false;
                        break OUTER;
                    default:
                        System.out.println("Invalid answer");
                        break;
                }
            }
        }
    }
    
    //methods
    private static void initialise( String shipRef[]){
        for (int x = 0; x < 12; x++ )
            shipRef[x] = "unreserved";
        System.out.println( "initilise ");
    }
        //view all cabins
    private static void ViewsAllCabins(String[] ship) {
        for (int x = 0; x < 12; x++) {
            //print all cabins
            System.out.println("cabin " + x + " occupied by none");
        }
    }

        //add customer to cabin
    private static String[] AddsCustomerToCabin(String[] ship) {
        int cabinNum=0;
        String cabinName;
        while (cabinNum<12)
        {  
            while (true){
            System.out.println("Enter cabin number (0-11) or 12 to stop:");
                if (input.hasNextInt()) {
                    cabinNum = input.nextInt();
                    if ((cabinNum>=0) && (cabinNum<=12)) {
                        break;
                    } else {
                        System.out.println("Sorry! out of range cablin numbers");	
                    }
                } else {
                    System.out.println("Invalid answer");
                    input.next();
                }
            }
            
            if(cabinNum !=12)
            {
                System.out.println("Enter name for cabin " + cabinNum + " :");
                cabinName = input.next();
                ship[cabinNum]=cabinName;

            }
        }
        return ship;
    }
    
        //display empty cabins
    private static void DisplayEmptyCabins(String[] ship) {
        for (int x = 0; x < 12; x++) {
            if (ship[x].equals("unreserved"))
                //print empty cabins
            System.out.println("cabin " + x + " is empty");
        }

    }
    
        //delete customer from cabin
    private static void DeleteCustomeFromCabin(String[] ship) {
        int delete;
        
        while (true){
            //asking for input
            System.out.println("Enter cabin number to delete(0-11)");
                if (input.hasNextInt()) {
                    delete = input.nextInt();
                    if ((delete>=0) && (delete<=12)) {
                        break;
                    } else {
                        System.out.println("Sorry! out of range cablin numbers");	
                    }
                } else {
                    System.out.println("Wrong input");
                    input.nextLine();
                }
        }
        ship[delete]="unreserved";

    }
    
        //find cabin from customer name
    private static void FindCabinFromCustomerName(String[] ship) {
        Scanner input = new Scanner(System.in);
        String cabinName;
        System.out.println("Enter name to Search for cabin:");
        cabinName = input.next();
        int x;
        boolean Checker = false;
        for (x = 0; x < ship.length; x++) {
            if (cabinName.equals(ship[x])) {
                System.out.println("That Name matches Cabin number " + x);
                Checker = true;
            }
        }
        if (Checker == false) {
            System.out.println("There are no Cabins Booked with that name ");
        }

    }    
    
        //store program data into file
    private static void StoreProgramDataIntoFile(String[] ship) {
  
        try {  
            FileWriter cData = new FileWriter("store.txt");
            for (int x = 0; x < 12; x++ ){
                cData.write("Cabin number "+String.valueOf(x)+" "+ship[x]+"\n");
            }
            cData.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    

    }
    
        //load program data from file
    private static void LoadProgramDataFromFile(String[] ship) {
        try {
            File cData = new File("store.txt");
            Scanner myReader = new Scanner(cData);  
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        } 

    }
    
        //view passengers ordered alphetically by name
    private static String[] ViewPassengersOrderedAlphabeticallyByName(String[] ship) {
        
        boolean isordered;
        int elements=ship.length;
        String[] temp=ship.clone();
        //Merge  number with name 
        for (int x=0;x<elements;x++){
                ship[x]=ship[x]+" cabin "+x;
        }
        //sorting part
        for(int i=0;i<(elements-1);i++){
            isordered=false;
            for(int j=0;j<(elements-1);j++){
                if ( (ship[j].compareTo(ship[j+1])) > 0) {
                    String temp_=ship[j];
                    ship[j]=ship[j+1];
                    ship[j+1]=temp_;
                    isordered=true;
                }
            }
            //When all sorted loop break
            if (isordered==false){
                    break;
            }
        }
        // print view passengers Ordered alphabetically by name
        for (String element: ship) {
            if(!(element.contains("unreserved"))){
                System.out.println(element);
            }	
        }
        ship=temp;
        return ship;

    }

}

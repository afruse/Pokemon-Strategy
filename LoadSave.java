import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

import java.util.Scanner;
import java.util.ArrayList;

import java.util.NoSuchElementException;

// for Files
// Need some sort of main method or smtng to call on the addData method

import java.io.File;

import java.io.FileNotFoundException;
public class LoadSave
{
    // instance variables - replace the example below with your own
    private ArrayList<Data> memoryList;

    public LoadSave() {
        memoryList = new ArrayList<>();
    }
    

    public void addData(String currentWorld, String coordinates, String HP, String pokemon) {
        String[] adding = {currentWorld, coordinates, HP, pokemon};
        Data guest = new Data(adding);
        memoryList.add(guest);
        
    }

    

    public void viewList() {
        if (memoryList.isEmpty()) {
            System.out.println("The data is empty.");
        } else {
            System.out.println("Recent Data:");
            for (Data data : memoryList) {
                System.out.println("World: " + data.getCurrentWorld());
                System.out.println("HP: " + data.getHP());
                System.out.println("Current Coordinates: " + data.getCoordinateX() + ", " + data.getCoordinateY());
                //System.out.println("Pokemon: " +  data.getPokemon1());
            }
        }
    }

    public void resetList() {
        memoryList.clear();
        
    }

    public void saveListToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            for (Data guest : memoryList) {
                writer.println(guest.getCurrentWorld() + "," + guest.getHP() + "," + guest.getCoordinateX() + "," + guest.getCoordinateY() + "," + guest.getPokemon1() + guest.getPokemon2());
            }
            writer.close();
            //
        } catch (IOException e) {
            System.out.println("Error occurred while saving ");
        }
    }

    
    
    public static String fileGenerator() {
        
        int HP;
        String currentWorld, pokemon, coordinates;
        
        String fileName = "x";
        System.out.println("What would you like to name the file? (include extension)");
        //scan.nextLine();
        //fileName = scan.nextLine();
        // Somehow taking data and assigning it to these variables
        // If more than one set, use a for loop to constantly write to file
        

       
        return fileName;
    }

    
    
    
}


        
        
    

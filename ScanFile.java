 
/**
 * Write a description of class ScanCredit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// for Scanning
import java.util.Scanner;
import java.util.NoSuchElementException;
import greenfoot.World;

// for Files
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class ScanFile
{
    /**
       * A class of world that represents the world the player moves around in to challenge enemies
       * 
       * @author (Affan H(Mainly)and Gajan S(a little bit)
       * @version 1.0
     */
    private Scanner scanFile;
    private String fileString = "";
    public ArrayList<String[]> arrayTokens;
    private int lines = 0;
    private boolean run = true;
    /**
       * This is the constructor for our file scanning using the tokenizer
       * @ param file   This takes in a file
     */
    public ScanFile(String file)
    {
        arrayTokens = new ArrayList<String[]>();
        try{
            scanFile = new Scanner(new File(file));
            while(true){
                if(scanFile.hasNextLine()){
                    arrayTokens.add(getTokens(scanFile.nextLine()));
                }
                if(!scanFile.hasNextLine()){
                    run = false;
                    break;
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(NullPointerException f){
            System.out.println("E");
        }
        /**
         * printing help from Aous
         */
        /*
        for (int i = 0; i < arrayTokens.size(); i++)
        {
            for (int j = 0; j < arrayTokens.get(i).length; j++)
            {
                System.out.print(arrayTokens.get(i)[j] + " ");
            }
            System.out.println();
        }*/
    }
    public void run(){
    }
    /**
     * This method gets the size of the arraylist
     * @ return int     This returns the size of the arraylist
     */
    public int getTransactions(){
        return arrayTokens.size();
    }
    public ArrayList<String[]> getInfo(){
        return arrayTokens;
    }
    /**
     * To analyze the awkward tokens in Visa's CSV files:
     * - Comma-separated but ..
     * - Sometimes there are comma's in the name, within quotes
     * @param input     This is the input for analyzing tokens with commas in between in awkward places
     */
    private String[] getTokens (String input) {
        String[] tokens = new String[5];
        for (int i = 0; i < tokens.length; i++){
            tokens[i] = "";
        }

        int index = 0;
        int pointer = 0;
        boolean inQuotes = false;
        while (true){
            if (pointer == input.length()){
                break;
            }
            if (input.charAt(pointer) == '\"'){
                if (inQuotes){      
                    inQuotes = false;
                } else {
                    inQuotes = true;
                }
            }
            else if (input.charAt(pointer) == ','){
                if (inQuotes){
                    tokens[index] += input.charAt(pointer);
                } else {
                    index++;
                }
            }
            else {
                tokens[index] += input.charAt(pointer);
            }
            pointer++;
        }
        for (int i = 0; i < tokens.length; i++){
            //System.out.println(i + ": " + tokens[i]);
        }
        return tokens;
    }
}
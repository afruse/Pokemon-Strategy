
/**
 * Write a description of class ScanCredit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// for Scanning
import java.util.Scanner;
import java.util.NoSuchElementException;
// for Files
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class LoadSave
{
    private Scanner scanFile;
    private String fileString = "";
    public ArrayList<String[]> arrayTokens;
    private int lines = 0;
    private boolean run = true;
    public LoadSave(String file)
    {
        arrayTokens = new ArrayList<String[]>();
        try{
            scanFile = new Scanner(new File(file));
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        while(true){
            if(scanFile.hasNextLine()){
                arrayTokens.add(getTokens(scanFile.nextLine()));
            }
            if(!scanFile.hasNextLine()){
                run = false;
                break;
            }
        }
        /**
         * printing help from Aous
         */
        for (int i = 0; i < arrayTokens.size(); i++)
        {
            for (int j = 0; j < arrayTokens.get(i).length; j++)
            {
                System.out.print(arrayTokens.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
    public void run(){
    }
    /*public int getTransaction(){
        return arrayTokens.size();
    }*/
    public ArrayList<String[]> getInfo(){
        return arrayTokens;
    }
    
    /**
     * To analyze the awkward tokens in Visa's CSV files:
     * - Comma-separated but ..
     * - Sometimes there are comma's in the name, within quotes
     * -
     */
    private String[] getTokens (String input) {
        String[] tokens = new String[4];
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
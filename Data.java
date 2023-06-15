
import java.util.ArrayList;
import java.util.NoSuchElementException;

// for Files

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

/**
 * A class of world that represents the world the player moves around in to challenge enemies
 * 
 * @author (Affan H and Gajan S) 
 * @version 1.0
 */


public class Data {
    private ArrayList<String[]> values;
    private String currentWorldX;
    private String currentWorldY;
    private String coordinateX, coordinateY;
    private String beatBoss;
    private String pokemon1;
    private String pokemon2;
    private String HP, lvl,XP,XP_needed, HP2, lvl2, XP2, XP_needed2;
    private ScanFile scan;
    /**
     * The constructer where it creates the pokemon and sets where it spawns in the grid
     * @param fileName     Represents the x Index of the 2dArray of the grid
     */
    public Data(String fileName) {
        scan = new ScanFile(fileName);
        this.values = scan.getInfo();
        this.currentWorldX = values.get(0)[0];
        this.currentWorldY = values.get(0)[1];
        this.coordinateX = values.get(0)[2];
        this.coordinateY = values.get(0)[3];
        this.beatBoss = values.get(0)[4];
        this.lvl = values.get(1)[0];
        this.XP = values.get(1)[1];
        this.XP_needed = values.get(1)[2];
        this.lvl2 = values.get(2)[0];
        this.XP2 = values.get(2)[1];
        this.XP_needed2 = values.get(2)[2];
    }
    public Data() {
    }
    /**
     * A method which updates the changes to the players data
     * @param values    This represents all key player data, including the currentWorld, coordinates and the level
     * 
     */
    public void updateChanges(ArrayList<String []> values){
        this.values = values;
    }
    /**
     * A method which saves the changes from the player data and writes it into a file
     * @param fileName    This represents the filename
     * @param values      This represents the player data in the form of an arraylist
     * 
     */
    public void saveChanges(String fileName, ArrayList<String []> values){
        
        this.values = values;
        ArrayList<String[]> waitList = values;
            try{
                FileWriter out = new FileWriter(fileName);
                PrintWriter output = new PrintWriter(out);
                for (int i = 0; i < waitList.size(); i++)
                {
                    for (int j = 0; j < waitList.get(i).length; j++)
                    {
                        output.print(waitList.get(i)[j] + ",");
                    }
                    output.println();
                }
                output.close();
            }
            catch(IOException e){
                System.out.println("Error: " + e);
            }
    }
    /**
     * A method which gets current world x value representation
     * @return int  This returns the integer representing a specific world x value representation
     */
    public int getCurrentWorldX() {
        return Integer.parseInt(currentWorldX);
    }
    /**
     * A method which gets current y value of current world
     * @return int  This returns the y value
     */
    public int getCurrentWorldY() {
        return Integer.parseInt(currentWorldY);
    }
    /**
     * A method which gets current x 
     * @return int  This returns the x coordinate
     */
    public int getCoordinateX() {
        return Integer.parseInt(coordinateX);
    }
    /**
     * A method which gets current y 
     * @return int  This returns the y coordinate
     */
    public int getCoordinateY() {
        return Integer.parseInt(coordinateY);
    }
    /**
     * A method which determines if bos is beaten 
     * @return boolean  This returns if boss is beaten
     */
    public boolean getBeatBoss() {
        if(beatBoss.equals("true")){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * A method which gets current HP
     * @return int  This returns the HP value
     */
    public int getHp() {
        return Integer.parseInt(HP);
    }
    public int getLvl()
    {
        return Integer.parseInt(lvl);
    }
    public String getPokemon1(){
        return pokemon1;
    }
    public String getPokemon2(){
        return pokemon2;
    }
    public int getXp() {
        return Integer.parseInt(XP);
    }
    public int getXpNeeded() {
        return Integer.parseInt(XP_needed);
    }
    public int getHp2()
    {
        return Integer.parseInt(HP2);
    }
    public int getLvl2(){
        return Integer.parseInt(lvl2);
    }
    public int getXp2()
    {
        return Integer.parseInt(XP2);
    }
    public int getXpNeeded2()
    {
        return Integer.parseInt(XP_needed2);
    }
}
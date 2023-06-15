
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
    public void updateChanges(ArrayList<String []> values){
        this.values = values;
    }
    public void saveChanges(String fileName, ArrayList<String []> values){
        System.out.println(fileName);
        System.out.println("bru");
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
    public int getCurrentWorldX() {
        return Integer.parseInt(currentWorldX);
    }
    public int getCurrentWorldY() {
        return Integer.parseInt(currentWorldY);
    }
    public int getCoordinateX() {
        return Integer.parseInt(coordinateX);
    }
    public int getCoordinateY() {
        return Integer.parseInt(coordinateY);
    }
    public boolean getBeatBoss() {
        if(beatBoss.equals("true")){
            return true;
        }
        else{
            return false;
        }
    }
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
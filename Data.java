
import java.util.Scanner;
import java.util.ArrayList;

import java.util.NoSuchElementException;

// for Files

import java.io.File;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class Data {
    private String currentWorld;
    private String coordinateX, coordinateY;
    private String beatBoss;
    private String pokemon1;
    private String pokemon2;
    private String HP, lvl,XP,XP_needed, HP2, lvl2, XP2, XP_needed2;

    public Data(String [] values) {
        this.currentWorld = values[0];
        this.coordinateX = values[1];
        this.coordinateY = values[2];
        this.beatBoss = values[3];
        this.pokemon1 = values[4];
        this.HP = values[5];
        this.lvl = values[6];
        this.XP = values[7];
        this.XP_needed = values[8];
        this.pokemon2 = values[9];
        this.HP2 = values[10];
        this.lvl2 = values[11];
        this.XP2 = values[12];
        this.XP_needed2 = values[13];
    }

    public String getCurrentWorld() {
        return currentWorld;
    }

    public String getCoordinateX() {
        return coordinateX;
    }
    
    public String getCoordinateY() {
        return coordinateY;
    }

    public String getBeatBoss() {
        return beatBoss;
    }

    public String getHP() {
        return HP;
    }
    public String getLvl()
    {
    
        return lvl;
    }
    public String getXP() {
        return XP;
    }
    public String getXPNeeded() {
        return XP_needed;
    }
    
    public String getHP2()
    {
    
        return HP2;
    }
    public String getXP2()
    {
    
        return XP2;
    }
    
    public String getXP_needed1()
    {
    
        return XP_needed2;
    }
}
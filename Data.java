
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
    private String HP, lvl,XP,XP_needed, HP2, XP2, XP_needed2;

    public Data(String [] values) {
        this.currentWorld = values[0];
        this.coordinateX = values[1];
        this.coordinateY = values[2];
        this.beatBoss = values[3];
        this.HP = values[4];
        this.lvl = values[5];
        this.XP = values[6];
        this.XP_needed = values[7];
        this.HP2 = values[8];
        this.XP2 = values[9];
        this.XP_needed2 = values[10];
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
    
        return XP_needed2;;
    }
}
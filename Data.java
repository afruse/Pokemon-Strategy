
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
    private String coordinates;
    private int HP;
    private String pokemon;

    public Data(String currentWorld, String coordinates, int HP, String pokemon) {
        this.currentWorld = currentWorld;
        this.coordinates = coordinates;
        this.HP = HP;
        this.pokemon = pokemon;
    }

    public String getCurrentWorld() {
        return currentWorld;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public int getHP() {
        return HP;
    }

    public String getPokemon() {
        return pokemon;
    }
}

import java.util.Scanner;
import java.util.ArrayList;

import java.util.NoSuchElementException;

// for Files

import java.io.File;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class Data {
    private String firstName;
    private String lastName;
    private int partySize;
    private String specialRequest;

    public Data(/*String firstName, String lastName, int partySize, String specialRequest*/) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.partySize = partySize;
        this.specialRequest = specialRequest;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPartySize() {
        return partySize;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }
}
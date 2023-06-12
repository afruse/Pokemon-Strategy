import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.List;
/**
 * Write a description of class TypingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TypingWorld extends World
{

    /**
     * Constructor for objects of class TypingWorld.
     * 
     */
    private String entry = "";
    private GreenfootImage[] images = new GreenfootImage[26];
    private int spaceNumber = 1;
    private HashMap<String, GreenfootImage> characters = new HashMap<String, GreenfootImage>();
    public TypingWorld(boolean newFile)
    {    
        super(700, 600, 1, false);
        GreenfootImage image = new GreenfootImage("EnterFileName.png");
        image.scale(700, 600);
        setBackground(image);
        for(int i = 0; i < images.length; i++){
            images[i] = new GreenfootImage("Alphabet" + (i+1) + ".png");
        }
    }
    public void act(){
        for(int i = 0; i < 26; i++){
            characters.put(String.valueOf((char)(97 + i)), images[i]);
        }
        if(spaceNumber == 1){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 294, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(spaceNumber == 2){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 317, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(spaceNumber == 3){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 340, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(spaceNumber == 4){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 363, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(spaceNumber == 5){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 386, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(spaceNumber == 6){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 409, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(spaceNumber == 7){
            String key = Greenfoot.getKey();
            entry = entry + key;
            addObject(new Alphabet(characters.get(key)), 432, 220);
            if(key != null){
                spaceNumber++;
            }
        }
        if(Greenfoot.getKey() == "ENTER"){
            //call file with name entry + .txt
        }
        System.out.println(spaceNumber);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
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
    private GreenfootImage[] images = new GreenfootImage[26];
    private HashMap<String, GreenfootImage> characters = new HashMap<String, GreenfootImage>();
    public TypingWorld()
    {    
        super(700, 600, 1, false);
        GreenfootImage image = new GreenfootImage("EnterFileName.png");
        image.scale(700, 600);
        setBackground(image);
        for(int i = 0; i < images.length; i++){
            images[i] = new GreenfootImage("Character" + i + ".png");
        }
    }
    public void act(){
        for(int i = 0; i < 26; i++){
            characters.put(String.valueOf((char)(65 + i)), images[i]);
        }
        Greenfoot.getKey();
    }
}

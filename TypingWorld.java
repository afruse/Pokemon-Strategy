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
    private int spaceNumber = 0;
    private HashMap<String, GreenfootImage> characters = new HashMap<String, GreenfootImage>();
    private SimpleTimer timer = new SimpleTimer();
    private Alphabet[] container = new Alphabet[7];
    public TypingWorld(boolean newFile)
    {    
        super(700, 600, 1, false);
        GreenfootImage image = new GreenfootImage("EnterFileName.png");
        image.scale(700, 600);
        setBackground(image);
        for(int i = 0; i < images.length; i++){
            images[i] = new GreenfootImage("Alphabet" + (i+1) + ".png");
        }
        for(int i = 0; i < 26; i++){
            characters.put(String.valueOf((char)(97 + i)), images[i]);
        }
        timer.mark();
    }
    public void act(){
        if(timer.millisElapsed() > 300){
        //String nonsense = Greenfoot.getKey();
        if(Greenfoot.isKeyDown("ENTER")){
            //call file with name entry + .txt
        }
        else if("BACKSPACE".equals(Greenfoot.getKey())){
            spaceNumber--;
            removeObject(container[spaceNumber-1]);
        }
        else if(spaceNumber == 0){
            String key = Greenfoot.getKey();
            if(key != null){
                spaceNumber++;
            }
        }
        else if(spaceNumber == 1){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet a = new Alphabet(characters.get(key)); 
            addObject(a, 294, 220);
            if(key != null){
                container[0] = a;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 2){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet b = new Alphabet(characters.get(key)); 
            addObject(b, 317, 220);
            if(key != null){
                container[1] = b;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 3){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet c = new Alphabet(characters.get(key));
            addObject(c, 340, 220);
            if(key != null){
                container[2] = c;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 4){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet d = new Alphabet(characters.get(key));
            addObject(d, 363, 220);
            if(key != null){
                container[3] = d;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 5){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet e = new Alphabet(characters.get(key));
            addObject(e, 386, 220);
            if(key != null){
                container[4] = e;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 6){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet f = new Alphabet(characters.get(key));
            addObject(f, 409, 220);
            if(key != null){
                container[5] = f;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 7){
            String key = Greenfoot.getKey();
            entry = entry + key;
            Alphabet g = new Alphabet(characters.get(key));
            addObject(g, 432, 220);
            if(key != null){
                container[6] = g;
                spaceNumber++;
            }
        }
        }
    }
}

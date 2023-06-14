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
    private boolean newFile;
    private Alphabet[] alph = new Alphabet[7];
    private int actCount = 0;
    private int lastRunAct = actCount;
    private boolean inRow = false;
    public TypingWorld(boolean newFile)
    {    
        super(700, 600, 1, false);
        this.newFile = newFile;
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
        if(Greenfoot.isKeyDown("BACKSPACE")){
            if((lastRunAct + 15) < actCount && spaceNumber > 1){
                lastRunAct = actCount;
                spaceNumber--;
                System.out.println(spaceNumber);
                removeObject(alph[spaceNumber - 1]);
            }
        }
        else if(Greenfoot.isKeyDown("ENTER")){
            if(!newFile){
                Data d = new Data(entry + ".csv");
                GymWorld w = new GymWorld(d);
                Greenfoot.setWorld(w);
            }else{
                World w = new GymWorld();
                Greenfoot.setWorld(w);
            }
        }
        else if(spaceNumber == 0){
            String key = Greenfoot.getKey();
            if(key != null){
                spaceNumber++;
            }
        }
        else if(spaceNumber == 1){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[0] = new Alphabet(characters.get(key));
                addObject(alph[0], 294, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 2){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[1] = new Alphabet(characters.get(key));
                addObject(alph[1], 317, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 3){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[2] = new Alphabet(characters.get(key));
                addObject(alph[2], 340, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 4){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[3] = new Alphabet(characters.get(key));
                addObject(alph[3], 363, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 5){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[4] = new Alphabet(characters.get(key));
                addObject(alph[4], 386, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 6){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[5] = new Alphabet(characters.get(key));
                addObject(alph[5], 409, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        else if(spaceNumber == 7){
            String key = Greenfoot.getKey();
            if(key != null && characters.get(key) != null){
                alph[6] = new Alphabet(characters.get(key));
                addObject(alph[6], 432, 220);
                entry = entry + key;
                spaceNumber++;
            }
        }
        actCount++;
        }
    }
}

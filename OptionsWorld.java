import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.InterruptedException;
/**
 * Write a description of class OptionsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OptionsWorld extends World
{

    /**
     * Constructor for objects of class OptionsWorld.
     * 
     */
    private SelectAni selectUpLeft;
    private SelectAni selectUpRight;
    private boolean newFile;
    private SelectAni selectDownLeft;
    private SelectAni selectDownRight;
    private String key;
    private SimpleTimer timer = new SimpleTimer();
    public OptionsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1);
        selectUpLeft = new SelectAni(false, false);
        selectDownLeft = new SelectAni(true, true);
        selectUpRight = new SelectAni(false, true);
        selectDownRight = new SelectAni(true, false);
        GreenfootImage image = new GreenfootImage("Options.png");
        image.scale(700, 600);
        setBackground(image);
        addObject(selectUpLeft, 280, 125);
        addObject(selectDownLeft, 280, 155);
        addObject(selectUpRight, 434, 125);
        addObject(selectDownRight, 434, 155);
        timer.mark();
    }
    public void act(){
        if(Greenfoot.isKeyDown("UP")){
            selectUpLeft.setLocation(280, 125);
            selectDownLeft.setLocation(280, 155);
            selectUpRight.setLocation(434, 125);
            selectDownRight.setLocation(434, 155);
            newFile = false;
        }
        if(Greenfoot.isKeyDown("DOWN")){
            selectUpLeft.setLocation(245, 307);
            selectDownLeft.setLocation(245, 337);
            selectUpRight.setLocation(470, 307);
            selectDownRight.setLocation(470, 337);
            newFile = true;
        }
        if(Greenfoot.isKeyDown("ENTER") && timer.millisElapsed() > 300){
            World w = new TypingWorld(newFile);
            Greenfoot.setWorld(w);
        }
    }
}

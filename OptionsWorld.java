import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private SelectAni selectUp;
    private boolean newFile;
    private SelectAni selectDown;
    public OptionsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(480, 320, 1);
        selectUp = new SelectAni(false);
        selectDown = new SelectAni(true);
        GreenfootImage image = new GreenfootImage("Options.png");
        image.scale(480, 320);
        setBackground(image);
        addObject(selectUp, 244, 65);
        addObject(selectDown, 244, 85);
    }
    public void act(){
        if(Greenfoot.isKeyDown("UP")){
            selectUp.setLocation(244, 65);
            selectDown.setLocation(244, 85);
            newFile = false;
        }
        if(Greenfoot.isKeyDown("DOWN")){
            selectUp.setLocation(244, 160);
            selectDown.setLocation(244, 180);
            newFile = true;
        }
        if(Greenfoot.isKeyDown("ENTER")){
            World w = new TypingWorld(newFile);
            Greenfoot.setWorld(w);
        }
    }
}

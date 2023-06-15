
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{

    /**
     * Constructor for objects of class IntroWorld.
     * 
     */
    private GreenfootSound introWorldSound;
    private GifIntroWorldBackground backGroundAni;
    public IntroWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1, false); 
        backGroundAni = new GifIntroWorldBackground();
        addObject(backGroundAni, -400, -300);
        addObject(new GifOverlay(), 350, 300);
        setBackground(backGroundAni.getImage());
        introWorldSound = new GreenfootSound("intro.mp3");
    }
    public void started()
    {
        introWorldSound.playLoop();
    }
    
    public void stopped()
    {
        introWorldSound.stop();
    }
    /**
     * Checks to see if user wants to start
     */
    public void act()
    {
       if(Greenfoot.isKeyDown("ENTER")){
           //GymWorld gw = new GymWorld();
           OptionsWorld w = new OptionsWorld();
           stopped();
           Greenfoot.setWorld(w);
       }
    }
}

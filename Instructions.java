import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The instructions class is simply a world that displays how to play the game,
 * as well as tips and what features there are in the game.
 * 
 * @author Jacob Omdara
 * @version June 2023
 */
public class Instructions extends World
{
    GreenfootImage background; 
    OptionsWorld w = new OptionsWorld();
    /**
     * Constructor for objects of class Instructions.
     * 
     */
    public Instructions()
    {    
        super(700, 600, 1); 
        background = new GreenfootImage("instructions.png");
        setBackground(background);
    }
    /**
     * This allows the player to move to the next screen.
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("E")){
            Greenfoot.setWorld(w);
        }
    }
}

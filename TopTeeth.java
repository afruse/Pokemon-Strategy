import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TopTeeth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TopTeeth extends Teeth
{
    /**
     * Act - do whatever the TopTeeth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TopTeeth()
    {
        this.getImage().scale(170, 80);
        this.getImage().setTransparency(5);
        direction = 1;
    }
    
    public void act()
    {
        animate();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BottomTeeth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BottomTeeth extends Teeth
{
    /**
     * Act - do whatever the BottomTeeth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BottomTeeth()
    {
        this.getImage().scale(180, 80);
        this.getImage().setTransparency(5);
        direction = -1;
    }
    
    public void act()
    {
        animate();
    }
}

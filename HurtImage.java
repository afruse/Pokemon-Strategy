import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HurtImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HurtImage extends Attack
{
    protected int timer = 255;
    /**
     * Act - do whatever the HurtImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        timer -= 15;
        if (this.getImage().getTransparency() > 15)
        {
            this.getImage().setTransparency(timer);
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}

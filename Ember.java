import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ember here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ember extends Attack
{
    protected int timer = 255;
    protected BattleOrderActionBlock b;
    
    /**
     * Act - do whatever the Ember wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Ember(BattleOrderActionBlock b)
    {
        GreenfootImage image = new GreenfootImage("ember2.png");
        this.setImage(image);
        this.getImage().scale(100, 200);
        this.getImage().setTransparency(200);
        this.b = b;
    }
    
    public void act()
    {
        timer--;
        
        if (timer > 200)
        {
            this.getImage().mirrorHorizontally();
        }
        else if (timer < 5)
        {
            getWorld().removeObject(this);
        }
        else
        {
            timer -= 4;
            this.getImage().mirrorHorizontally();
            this.getImage().setTransparency(timer);
        }
    }
}

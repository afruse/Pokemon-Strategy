import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spite extends Attack
{
    protected int timer = 0;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    protected int originalX;
    protected int originalY;
    protected int originalAWidth;
    protected int originalAHeight;
    protected int growthRate = 8;
    protected int shake = 20;
    protected GreenfootImage imageA;
    protected GreenfootImage imageB;
    
    public Spite(BattleOrderActionBlock a, BattleOrderActionBlock b)
    {
        this.a = a;
        this.b = b;
        this.originalX = a.getX();
        this.originalY = a.getY();
        this.originalAWidth = a.getImage().getWidth();
        this.originalAHeight = a.getImage().getHeight();
    }
    /**
     * Act - do whatever the Spite wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        timer++;
        
        // Shake
        if (timer < 30)
        {
            a.setLocation(a.getX() + Greenfoot.getRandomNumber(shake) - shake/2, a.getY() + Greenfoot.getRandomNumber(shake) - shake/2);
        }
        else if (timer == 30)
        {
            a.setLocation(originalX, originalY);
        }
        // Add clones
        if (timer > 45 && timer < 57)
        {
            Clone c = new Clone(b, b.getImage());
            getWorld().addObject(c, b.getX(), b.getY());
        }
        
        // (At same time) Scale up
        if (timer < 15)
        {
            a.getImage().scale(a.getImage().getWidth() + growthRate, a.getImage().getHeight() + growthRate);
        }
        // Scale down
        else if (timer < 30)
        {
            a.getImage().scale(a.getImage().getWidth() - growthRate, a.getImage().getHeight() - growthRate);
        }
        else if (timer < 45)
        {
            a.getImage().scale(originalAWidth, originalAHeight);
        }
        // Scale down
        else if (timer < 47)
        {
            b.setLocation(b.getX() + shake, b.getY());
        }
        else if (timer < 51)
        {
            b.setLocation(b.getX() - shake, b.getY());
        }
        else if (timer < 55)
        {
            b.setLocation(b.getX() + shake, b.getY());
        }
        else if (timer < 57)
        {
            b.setLocation(b.getX() - shake, b.getY());
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}

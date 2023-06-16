import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Confusion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Confusion extends Attack
{
    protected int timer = 0;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    protected int shake = 10;
    protected int duration = 5;
    protected int originalAX;
    protected int originalAY;
    protected int originalBX;
    protected int originalBY;
    protected int originalWidth;
    protected int originalHeight;
    protected GreenfootImage savedImage;    
    public Confusion(BattleOrderActionBlock a, BattleOrderActionBlock b)
    {
        this.originalAX = a.getX();
        this.originalAY = a.getY();
        this.originalBX = b.getX();
        this.originalBY = b.getY();
        this.originalWidth = b.getImage().getWidth();
        this.originalHeight = b.getImage().getHeight();
        this.a = a;
        this.b = b;
        savedImage = new GreenfootImage(b.getImage());         
    }
    
    /**
     * Act - do whatever the Confusion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        timer++;
        
        // Shake A
        if (timer < duration)
        {
            a.setLocation(a.getX() - shake, a.getY() + shake);
        }
        else if (timer < duration * 2)
        {
            a.setLocation(a.getX() + shake, a.getY() + shake);
        }
        else if (timer < duration * 3)
        {
            a.setLocation(a.getX() + shake, a.getY() - shake);
        }
        else if (timer < duration * 4)
        {
            a.setLocation(a.getX() - shake, a.getY() - shake);
        }
        else if (timer == duration * 4)
        {
            a.setLocation(originalAX, originalAY);
        }
        // Shake B, scale B
        else if (timer < duration * 5)
        {
            b.setLocation(b.getX() - shake, b.getY() + shake);
            b.getImage().scale(b.getImage().getWidth() + shake, b.getImage().getHeight() + shake);
        }
        else if (timer < duration * 6)
        {
            b.setLocation(b.getX() + shake, b.getY() + shake);
            b.getImage().scale(b.getImage().getWidth() + shake, b.getImage().getHeight() + shake);
        }
        else if (timer < duration * 7)
        {
            b.setLocation(b.getX() + shake, b.getY() - shake);
            b.getImage().scale(b.getImage().getWidth() - shake, b.getImage().getHeight() - shake);
        }
        else if (timer < duration * 8)
        {
            b.setLocation(b.getX() - shake, b.getY() - shake);
            b.getImage().scale(b.getImage().getWidth() - shake, b.getImage().getHeight() - shake);
        }
        // Finish
        else
        {
            b.setLocation(originalBX, originalBY);
            b.setImage(savedImage);
            getWorld().removeObject(this);
        } 
    }
}

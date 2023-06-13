import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bolt2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bolt extends Attack
{
    protected int timer = 255;
    protected boolean bigBolt;
    protected int targetX;
    protected int targetY;
    
    /**
     * Act - do whatever the bolt2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Bolt(boolean bigBolt, int targetX, int targetY)
    {
        this.bigBolt = bigBolt;
        this.targetX = targetX;
        this.targetY = targetY;
        // True for big, false for small
        if (bigBolt)
        {
            this.getImage().scale(100,200);            
        }
        else
        {
            this.getImage().scale(20,20);
        }
        
        int random = Greenfoot.getRandomNumber(3);
        if (random == 0)
        {
            this.getImage().mirrorHorizontally();
        }
        else if (random == 1)
        {
            this.getImage().mirrorVertically();
        }
    }
    
    public void act()
    {   
        // Big bolt
        if (bigBolt)
        {
            if (timer < 6)
            {
                ((AttackAnimation)getWorld()).setThundering(false);
                // Small bolts
                Bolt b = new Bolt(false, targetX, targetY);
                int randomX = Greenfoot.getRandomNumber(60) - 30;
                int randomY = Greenfoot.getRandomNumber(60) - 30;
                int rotation = Greenfoot.getRandomNumber(360);
                b.getImage().rotate(rotation);
                getWorld().addObject(b, targetX + randomX, targetY + randomY);
                
                getWorld().removeObject(this);
            }
            else if (timer < 230)
            {
                timer -= 5;
                this.getImage().setTransparency(timer);
            }
            timer--;   
        }
        // Small bolt
        else
        {
            // Shake
            int randomX = Greenfoot.getRandomNumber(7) - 3;
            int randomY = Greenfoot.getRandomNumber(7) - 3;
            int shake = Greenfoot.getRandomNumber(20) - 10;
            
            this.getImage().rotate(shake);
            setLocation(getX() + randomX, getY() + randomY);
            // Fade
            if (timer < 6)
            {
                getWorld().removeObject(this);
            }
            else if (timer < 200)
            {
                timer -= 5;
                this.getImage().setTransparency(timer);
            }
            timer--;   
        }
    }
}

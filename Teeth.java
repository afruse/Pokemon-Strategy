import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class teeth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teeth extends Attack
{
    protected int speed = 14;
    protected int fade = 0;
    protected int timer = 0;
    protected int direction;
    protected int actions = 0;
    
    /**
     * Act - do whatever the teeth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public void animate()
    {
        // Movement
        if (speed > 0)
        {
            // Move up or down depending on direction
            setLocation(this.getX(), this.getY() + (speed * direction) );
            speed--;
        }
        
        // 0 2 6 12 20 30 42 56 
        // Fade in and out
        if (actions <= 20)
        {
            this.getImage().setTransparency( this.getImage().getTransparency() + fade );
            setLocation(this.getX(), this.getY() + (1 * direction) );
            if (fade < 10)
            {
                fade += 2;
            }
            actions++;
        }
        else if (actions == 15)
        {
            fade = 0;
        }
        else if (this.getImage().getTransparency() - fade < 0)
        {
            getWorld().removeObject(this);
        }
        else
        {
            this.getImage().setTransparency( this.getImage().getTransparency() - fade );
            setLocation(this.getX(), this.getY() + (1 * direction) );
            if (fade < 10)
            {
                fade += 2;
            }
        }
    }
}

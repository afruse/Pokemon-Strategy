import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class human here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Human extends Actor
{
    protected boolean hurt = false;
    protected int timer = 0;
    protected int shake = 8;
    
    public Human()
    {
        this.getImage().scale(100, 100);
    }
    
    /**
     * Act - do whatever the human wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (hurt)
        {
            timer++;
            getHit();
        }
    }
    
    public void getHit()
    {
        if (timer < 3)
        {
            setLocation(getX() - shake, getY());
        }
        else if (timer < 6)
        {
            setLocation(getX() + shake, getY());
        }
        else if (timer < 9)
        {
            setLocation(getX() - shake, getY());
        }
        else if (timer < 12)
        {
            setLocation(getX() + shake, getY());
        }
        else if (timer < 25)
        {
            // pause
        }
        else if (timer < 44)
        {
            if (timer % 4 == 0)
            {
                this.getImage().setTransparency(0);
            }
            else
            {
                this.getImage().setTransparency(255);
            }
        }
        else 
        {
            hurt = false;
            timer = 0;
        }
    }
    
    public void setHurt(boolean b)
    {
        hurt = b;
    }
}

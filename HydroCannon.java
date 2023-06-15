import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class waterGun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HydroCannon extends Attack
{
    protected int timer = 0;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    protected boolean attacking = true;
    
    /**
     * Act - do whatever the waterGun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HydroCannon(BattleOrderActionBlock a, BattleOrderActionBlock b)
    {
        GreenfootImage image = new GreenfootImage("watergun0.png");
        this.setImage(image);
        this.getImage().setTransparency(0);
        this.getImage().scale(450, 140);
        this.a = a;
        this.b = b;
    }
    
    public void act()
    {
        turnTowards(b.getX(), b.getY());
        
        if (timer == 450)
        {
            attacking = false;
            timer = 0;
        }
        else if (attacking)
        {
            timer += 30;
            this.getImage().setTransparency(255);
            this.getImage().scale(timer,140);
            move(15);
        }
        else if (!attacking && 255 - timer > 0)
        {
            this.getImage().setTransparency(255 - timer);
            timer += 5;
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}

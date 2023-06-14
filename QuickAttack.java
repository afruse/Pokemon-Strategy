import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class quickAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuickAttack extends Attack
{
    protected int timer = 0;
    protected GreenfootImage image;
    protected int originalDirection;
    protected int originalX;
    protected int originalY;
    protected int speed = 40;
    protected int chargeUpTime = 40;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    
    /**
     * Act - do whatever the quickAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public QuickAttack(BattleOrderActionBlock a, BattleOrderActionBlock b)
    {
        this.image = image;
        this.originalDirection = getRotation();
        this.originalX = a.getX();
        this.originalY = a.getY();
        this.a = a;
        this.b = b;
    }
    
    public void act()
    {
        timer++;
        
        a.turnTowards(b.getX(), b.getY());
        // Turning Around
        if (timer == chargeUpTime + 5 || timer == chargeUpTime + 15)
        {
            speed *= -1;
        }
        
        // After images / trail
        if (timer >= chargeUpTime)
        {
            Clone c = new Clone(a, a.getImage());
            getWorld().addObject(c, a.getX(), a.getY());
        }
        
        if (timer == chargeUpTime)
        {
            HurtImage h =  new HurtImage();
            getWorld().addObject(h, b.getX(), b.getY());
        }
        
        if (timer < chargeUpTime)
        {
            // wait
        }
        else if (timer >= chargeUpTime && timer < chargeUpTime + 8)
        {
            a.move(speed);
        }
        else if (timer < chargeUpTime + 18)
        {
            a.move( speed/2 );
        }
        else
        {
            // Return A to original state
            a.setRotation(originalDirection);
            a.setLocation(originalX, originalY);
            getWorld().removeObject(this);
        }
    }
}

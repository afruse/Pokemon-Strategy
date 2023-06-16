import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class quickAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tackle extends Attack
{
    protected int timer = 0;
    protected GreenfootImage image;
    protected int originalDirection;
    protected int originalX;
    protected int originalY;
    protected int speed = 1;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    
    /**
     * Act - do whatever the quickAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tackle(BattleOrderActionBlock a, BattleOrderActionBlock b)
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
        if (timer < 5)
        {
            a.turnTowards(b.getX(), b.getY());
        }
        
        if (timer == 30)
        {
            speed *= -1;
        }
        if (timer < 15)
        {
            a.move(speed);
            speed += 4;
        }
        else if (timer < 30)
        {
            a.move(-speed);
            speed -= 4;
        }
        // Turning Around
        else if (timer >= 30)
        {
            // Return A to original state
            a.setRotation(originalDirection);
            a.setLocation(originalX, originalY);
            getWorld().removeObject(this);
        }
    }
}

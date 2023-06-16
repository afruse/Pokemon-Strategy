import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ember here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Outrage extends Attack
{
    protected int timer = 0;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    protected boolean primary;
    protected int direction;
    protected int speed = 20;
    protected int randomX = Greenfoot.getRandomNumber(1000);
    protected int randomY = Greenfoot.getRandomNumber(800);
    protected boolean turned = false;
    protected int distance = 0;
    
    /**
     * Act - do whatever the Ember wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Outrage(BattleOrderActionBlock a, boolean primary)
    {
        GreenfootImage image = new GreenfootImage("AttackSprites/ember2.png");
        this.setImage(image);
        this.getImage().scale(100, 200);
        this.getImage().setTransparency(200);
        this.a = a;
        this.primary = primary;
        this.getImage().rotate(270);
        this.direction = Greenfoot.getRandomNumber(360);
    }
    
    public void act()
    {
        // Two types
        if (!turned)
        {
            turnTowards(randomX, randomY);
            turned = true;
        }
        if (primary)
        {
            // If out of bounds
            if (distance > 600)
            {
                getWorld().removeObject(this);
            }
            else
            {
                timer++;
                move(speed);
                distance += speed;
                if (timer % 3 == 0)
                {
                    Outrage o = new Outrage(a, false);
                    getWorld().addObject(o, a.getX(), a.getY());
                }
            }
        }
        else
        {
            move(speed);
            distance += speed;
            if (distance > 400)
            {
                getWorld().removeObject(this);
            }
        }
    }
}

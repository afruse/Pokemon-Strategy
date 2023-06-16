import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stomp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stomp extends Attack
{
    protected int timer = 255;
    protected int timer2 = 255;
    protected BattleOrderActionBlock b;
    protected int speed = 30;
    
    public Stomp(BattleOrderActionBlock b)
    {
        GreenfootImage image = new GreenfootImage("AttackSprites/stomp.png");
        this.setImage(image);
        this.getImage().scale(100, 125);
        this.b = b;
    }
    
    /**
     * Act - do whatever the Stomp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (timer > speed)
        {
            setLocation(this.getX(), this.getY() + speed);
            timer -= speed;
        }
        else if (timer > -5)
        {
            setLocation(b.getX(), b.getY());
            HurtImage h = new HurtImage();
            int randomX = Greenfoot.getRandomNumber(140) - 70;
            int randomY = Greenfoot.getRandomNumber(140) - 70;
            getWorld().addObject(h, b.getX() + randomX, b.getY() + randomY);
            timer--;
            this.getImage().setTransparency(timer2);
            timer2 -= 10;
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}

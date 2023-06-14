import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gust here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gust extends Attack
{
    protected BattleOrderActionBlock b;
    protected boolean original;
    protected int originalTimer = 0;
    protected int timer = 255;
    protected int shakeX = 12;
    protected int shakeY = shakeX / 2;
    
    /**
     * Act - do whatever the Gust wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Gust(BattleOrderActionBlock b, boolean original)
    {
        image = new GreenfootImage("AttackSprites/wingattackorwhirlwind (1).png");
        setImage(image);
        this.b = b;
        this.original = original;
        this.getImage().scale(70, 160);
        this.getImage().setTransparency(200);
    }
    
    public void act()
    {
        timer--;
        originalTimer++;
        setLocation(this.getX() + (Greenfoot.getRandomNumber(shakeX*2 + 1) - shakeX), this.getY() + (Greenfoot.getRandomNumber(shakeY*2 + 1) - shakeY));
        
        if (timer % 2 == 0)
        {
            this.getImage().mirrorHorizontally();
        }
        
        if (original)
        {
            if (originalTimer < 20)
            {
                if (originalTimer % 5 == 0)
                {
                    Gust g = new Gust(b, false);
                    getWorld().addObject(g, b.getX() + (Greenfoot.getRandomNumber(200) - 100), b.getY() + (Greenfoot.getRandomNumber(60) - 30));
                }
            }
        }
        
        if (timer >160)
        {
            // nothing
        }
        else if (timer > 4)
        {
            timer -= 4;
            this.getImage().setTransparency(timer);
        }
        else
        {
            getWorld().removeObject(this);  
        }
    }
}

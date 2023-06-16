import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gust here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WingAttack extends Attack
{
    protected BattleOrderActionBlock a;
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
    public WingAttack(BattleOrderActionBlock a, BattleOrderActionBlock b, boolean original)
    {
        GreenfootImage image = new GreenfootImage("AttackSprites/wingattackorwhirlwind (1).png");
        this.setImage(image);
        this.a = a;
        this.b = b;
        this.original = original;
        this.getImage().scale(70, 160);
        this.getImage().setTransparency(200);
    }
    
    public void act()
    {
        if (timer > 240)
        {
            turnTowards(b.getX(), b.getY());
        }
        timer--;
        originalTimer++;
        move(Greenfoot.getRandomNumber(shakeX));
        
        if (timer % 2 == 0)
        {
            this.getImage().mirrorHorizontally();
        }
        
        if (original)
        {
            if (originalTimer < 20)
            {
                if (originalTimer % 10 == 0)
                {
                    WingAttack w = new WingAttack(a, b, false);
                    getWorld().addObject(w, a.getX() + (Greenfoot.getRandomNumber(200) - 100), a.getY() + (Greenfoot.getRandomNumber(60) - 30));
                }
            }
        }
        
        if (timer > 160)
        {
            // nothing
        }
        else if (timer > 4)
        {
            timer -= 4;
            shakeX = 1;
            this.getImage().setTransparency(timer);
        }
        else
        {
            getWorld().removeObject(this);  
        }
    }
}

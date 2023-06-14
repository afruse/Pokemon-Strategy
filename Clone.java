import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class clone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Clone extends Attack
{
    protected BattleOrderActionBlock a;
    protected int timer = 90;
    
    /**
     * Act - do whatever the clone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Clone(BattleOrderActionBlock a, GreenfootImage image)
    {
        this.a = a;
        GreenfootImage cloneImage = new GreenfootImage(image);
        this.setImage(cloneImage);
        cloneImage.setTransparency(timer);
        cloneImage.scale(100, 100);
    }
    
    public void act()
    {
        // Don't fade until a bit later
        if (timer > 80)
        {
            //nothing
        }
        else if (this.getImage().getTransparency() > 0)
        {
            this.getImage().setTransparency(timer);
        }
        else
        {
            getWorld().removeObject(this);
        }
        timer -= 2;
    }
}

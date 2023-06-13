import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleOrderActionBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleOrderActionBlock extends Actor
{
    String role;
    protected boolean hurt = false;
    protected int timer = 0;
    protected int shake = 8;
    boolean isAfterMath = false;
    int afterMathTime = 0;
    /**
     * Act - do whatever the BattleOrderActionBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BattleOrderActionBlock(GreenfootImage image){
        setImage(image);
    }

    public void act()
    {
        if(isTouching(Attack.class) && role.equals("victim")){
            isAfterMath = true;
            setHurt(true);
        }
        else if(isAfterMath && afterMathTime < 60){
            afterMathTime++;
            
        }
        else{
            setHurt(false);
        }
        if (hurt)
        {
            timer++;
            getHit();
        }
    }
    
    public void setRole(String role){
        this.role = role;
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

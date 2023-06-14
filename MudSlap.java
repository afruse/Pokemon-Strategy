import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class sandAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MudSlap extends Attack
{
    protected double originX;
    protected double originY;
    protected double targetX;
    protected double targetY;
    protected int speed;
    protected double distance;
    protected double travelled = 0;
    protected boolean turned = false;
    protected boolean original;
    protected int rotation;
    protected BattleOrderActionBlock a;
    protected BattleOrderActionBlock b;
    
    /**
     * Act - do whatever the sandAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public MudSlap(BattleOrderActionBlock a, BattleOrderActionBlock b, boolean original)
    {
        image = new GreenfootImage("AttackSprites/mudslap.png");
        setImage(image);
        
        originX = a.getX();
        originY = a.getY();
        targetX = b.getX();
        targetY = b.getY();
        this.a = a;
        this.b = b;
        this.original = original;

        this.getImage().setTransparency(255);
        this.speed = 15;
        this.getImage().scale(40, 40);
        
        // Pythagorean theorem for distance
        distance = Math.sqrt( (targetY-originY)*(targetY-originY) + (targetX-originX)*(targetX-originX) );
    }
    
    public MudSlap(BattleOrderActionBlock a, BattleOrderActionBlock b, boolean original, int rotation)
    {
        originX = a.getX();
        originY = a.getY();
        targetX = b.getX();
        targetY = b.getY();
        this.rotation = rotation;
        original = false;
        
        this.getImage().setTransparency( Greenfoot.getRandomNumber(200) + 50);
        this.speed = Greenfoot.getRandomNumber(10) + 10;
        this.getImage().scale( Greenfoot.getRandomNumber(50) + 30, Greenfoot.getRandomNumber(50) + 30 );
        
        // Pythagorean theorem for distance
        distance = Math.sqrt( (targetY-originY)*(targetY-originY) + (targetX-originX)*(targetX-originX) );
    }
    
    public void act()
    {
        if (original && !turned)
        {
            turnTowards((int)targetX, (int)targetY);
            turned = true;
            rotation = getRotation();
            for (int i = 0; i < 80; i++)
            {
                int randomX = Greenfoot.getRandomNumber(200) - 75;
                int randomY = Greenfoot.getRandomNumber(110) - 55;
                MudSlap m = new MudSlap(a, b, false, rotation);
                getWorld().addObject(m, a.getX() + randomX, a.getY() + randomY);
            }
        }
        else
        {
            setRotation(rotation);
        }
        
        if (travelled < distance)
        {
            move(speed);
            travelled += speed;
        }
        else
        {
            this.getImage().setTransparency(speed);
            speed -= 2;
            move(speed);
            if (speed < 1)
            {
                getWorld().removeObject(this);
            }
        }
    }
}

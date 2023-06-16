import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class sandAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AuroraBeam extends Attack
{
    protected int timer = 0;
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
    public AuroraBeam(BattleOrderActionBlock a, BattleOrderActionBlock b, boolean original)
    {
        GreenfootImage image = new GreenfootImage("AttackSprites/aurorabeam.png");
        this.setImage(image);
        originX = a.getX();
        originY = a.getY();
        targetX = b.getX();
        targetY = b.getY();
        this.a = a;
        this.b = b;
        this.original = original;

        this.getImage().setTransparency(0);
        this.speed = 6;
        this.getImage().scale(30, 80);
        this.getImage().rotate(270);
        // Pythagorean theorem for distance
        distance = Math.sqrt( (targetY-originY)*(targetY-originY) + (targetX-originX)*(targetX-originX) );
    }
    
    public AuroraBeam(BattleOrderActionBlock a, BattleOrderActionBlock b, boolean original, int rotation)
    {
        GreenfootImage image = new GreenfootImage("AttackSprites/aurorabeam.png");
        this.setImage(image);
        originX = a.getX();
        originY = a.getY();
        targetX = b.getX();
        targetY = b.getY();
        this.rotation = rotation;
        original = false;
        this.getImage().setTransparency( Greenfoot.getRandomNumber(200) + 50);
        this.speed = 25;
        this.getImage().scale( Greenfoot.getRandomNumber(20) + 75, Greenfoot.getRandomNumber(20) + 90 );
        this.getImage().rotate(270);
        // Pythagorean theorem for distance
        distance = Math.sqrt( (targetY-originY)*(targetY-originY) + (targetX-originX)*(targetX-originX) );
    }
    
    public void act()
    {
        timer++;
        if (original)
        {
            if (!turned)
            {
                turnTowards((int)targetX, (int)targetY);
                turned = true;
                rotation = getRotation();
            }
            if (timer % 1 == 0)
            {
                int randomX = Greenfoot.getRandomNumber(50) - 25;
                int randomY = Greenfoot.getRandomNumber(8) - 4;
                AuroraBeam beam = new AuroraBeam(a, b, false, rotation);
                getWorld().addObject(beam, a.getX() + randomX, a.getY() + randomY);
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

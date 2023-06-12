import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    Human h = new Human();
    Human h2 = new Human();
    private int acts = 0;
    private boolean thundering = false;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1);
        addObject(h, 500, 400);
        addObject(h2, 800, 600);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("down"))
        {
            h.setHurt(true);
        }
        else if (Greenfoot.isKeyDown("up"))
        {
            bite(h);
        }
        else if (Greenfoot.isKeyDown("left"))
        {
            thundering = true;
            thunderbolt(h);
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            sandAttack(h2, h);
        }
        else if (Greenfoot.isKeyDown("space"))
        {
            
        }
    }
    
    public void bite(Human human)
    {
        Teeth top = new TopTeeth();
        Teeth bottom = new BottomTeeth();
        addObject(top, human.getX(), human.getY() - 170);
        addObject(bottom, human.getX(), human.getY() + 150);
    }
    
    public void thunderbolt(Human human)
    {
        // Big bolts
        for (int i = 0; i < 5; i++)
        {
            Bolt b = new Bolt(true, human.getX(), human.getY());
            int randomX = Greenfoot.getRandomNumber(100) - 50;
            int randomY = Greenfoot.getRandomNumber(60) - 30;
            addObject(b, human.getX() + randomX, human.getY() - 100 + randomY);
        }
    }
    
    // A is origin, B is target
    public void sandAttack(Human a, Human b)
    {
        //Sand sand = new Sand(a, b, true);
        //addObject(sand, a.getX(), a.getY());
    }
    
    public void setThundering(boolean b)
    {
        thundering = b;
    }
}

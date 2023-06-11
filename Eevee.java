import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eevee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eevee extends MoveablePokemon
{

    /**
     * Act - do whatever the Eevee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int cAttackRange = 1;
    protected int vAttackRange = 2;
    public Eevee(int mapIndexX, int mapIndexY, boolean isPlayer){
        super(mapIndexX,mapIndexY, isPlayer);
        GreenfootImage image = new GreenfootImage("images/Pokemon/eevee.png");
        setImage(image);
        int speed = 1;
    }

    public void act()
    {
        // Add your action code here.
       
        doSomething();
    }
}

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
   
    public Eevee(int mapIndexX, int mapIndexY, boolean isPlayer){
        super(mapIndexX,mapIndexY, isPlayer);
        cAttackString = "Bite";
        vAttackString = "Sand Attack";
        image = new GreenfootImage("images/Pokemon/eevee.png");
        animationImage = new GreenfootImage("images/Pokemon/eevee.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);
        int speed = 1;
        cAttackRange = 1;
        vAttackRange = 2;
    }

    public void act()
    {
        // Add your action code here.
       
        doSomething();
    }
    
}

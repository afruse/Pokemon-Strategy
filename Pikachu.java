import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends MoveablePokemon
{
    /**
     * Act - do whatever the Pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Pikachu(int mapIndexX, int mapIndexY, boolean isPlayer){
        super(mapIndexX,mapIndexY, isPlayer);
        image = new GreenfootImage("images/Pokemon/pika.png");
        animationImage = new GreenfootImage("images/Pokemon/pika.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);
        int speed = 2;
        cAttackRange = 1;
        vAttackRange = 2;
        cAttackString = "Quick Attack";
        vAttackString = "Thunder Shock";
    }

    public void act()
    {
        // Add your action code here.
        doSomething();

    }
}

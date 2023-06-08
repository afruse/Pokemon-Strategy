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
    public Pikachu(int mapIndexX, int mapIndexY){
        super(mapIndexX,mapIndexY);
        GreenfootImage image = new GreenfootImage("images/Pokemon/Pikachu.png");
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
        checkKeyPress();
    }
}

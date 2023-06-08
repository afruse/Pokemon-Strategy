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
        GreenfootImage image = new GreenfootImage("images/Pokemon/pika.png");
        setImage(image);
        int speed = 2;
    }

    public void act()
    {
        // Add your action code here.
        
         if(Greenfoot.mouseClicked(this)){
            isClickedOn = true;
        }
        if(isPlayer && isClickedOn){
            checkKeyPress();
        }
        else{
            //Do some algorithim crap
        }
    }
}

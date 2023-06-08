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
        GreenfootImage image = new GreenfootImage("images/Pokemon/eevee.png");
        setImage(image);
        int speed = 1;
    }

    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            isClickedOn = true;
            checkKeyPress();
        }
        if(isPlayer && isClickedOn){
            //checkKeyPress();
        }
        else{
            //Do some algorithim crap
        }
        if(isFling){
            setRotation(getRotation()+5);
            setLocation(getX()+5, getY()+5);
        }
    }
}

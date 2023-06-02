import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoveableCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveableCharacter extends Actor
{
    /**
     * Act - do whatever the MoveableCharacter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int mapIndexX;
    protected int mapIndexY;
    public MoveableCharacter(int mapIndexX, int mapIndexY){
        this.mapIndexX = mapIndexX;
        this.mapIndexY = mapIndexY;
    }

    public void act()
    {
        // Add your action code here.
        GymWorld w = (GymWorld)getWorld();
        String key = Greenfoot.getKey();
        if (key != null)
        {
            if (key.equals("W"))
            {
                w.moveCharacter(this, mapIndexX--, mapIndexY);
            }
            else if (key.equals("S"))
            {
                w.moveCharacter(this, mapIndexX++, mapIndexY);
            }
            if (key.equals("A"))
            {
                w.moveCharacter(this, mapIndexX, mapIndexY--);
            }
            if (key.equals("D"))
            {
                w.moveCharacter(this, mapIndexX, mapIndexY++);
            }

        }
    }

}

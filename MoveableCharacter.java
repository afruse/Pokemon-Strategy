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
    protected int previousMapIndexX;
    protected int previousMapIndexY;
    protected boolean sucessfulMovement;
    public MoveableCharacter(int mapIndexX, int mapIndexY){
        this.mapIndexX = mapIndexX;
        this.mapIndexY = mapIndexY;
        previousMapIndexX = mapIndexX;
        previousMapIndexY = mapIndexY;
    }

    public void act()
    {
        // Add your action code here.
        GymWorld w = (GymWorld)getWorld();
        String key = Greenfoot.getKey();
        if (key != null)
        {
            previousMapIndexX = mapIndexX;
            previousMapIndexY = mapIndexY;
            if (key.equals("w"))
            {
                //System.out.println("w");
                sucessfulMovement = w.moveCharacter(this, --mapIndexX, mapIndexY);
            }
            else if (key.equals("s"))
            {
                sucessfulMovement = w.moveCharacter(this, ++mapIndexX, mapIndexY);
            }
            else if (key.equals("a"))
            {
                sucessfulMovement = w.moveCharacter(this, mapIndexX, --mapIndexY);
            }
            else if (key.equals("d"))
            {
                sucessfulMovement = w.moveCharacter(this, mapIndexX, ++mapIndexY);

            }
            if(!sucessfulMovement){
                mapIndexX = previousMapIndexX;
                mapIndexY = previousMapIndexY;
            }
        }
    }

}

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
            if (key.equals("w"))
            {
                //System.out.println("w");
                w.moveCharacter(this, --mapIndexX, mapIndexY);
            }
            else if (key.equals("s"))
            {
                w.moveCharacter(this, ++mapIndexX, mapIndexY);
            }
            else if (key.equals("a"))
            {
                w.moveCharacter(this, mapIndexX, --mapIndexY);
            }
            else if (key.equals("d"))
            {
                System.out.println("X COORD " + mapIndexX + " Y COORD " + mapIndexY);
                w.moveCharacter(this, mapIndexX, ++mapIndexY);
                System.out.println("NEW X COORD " + mapIndexX + " Y COORD " + mapIndexY);

            }
        }
    }

}

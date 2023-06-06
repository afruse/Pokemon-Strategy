import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ObstructionChecker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObstructionChecker extends Actor
{
    /**
     * Act - do whatever the ObstructionChecker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int tileHeight;
    protected int tileLength;
    public ObstructionChecker(){
        GymWorld w = (GymWorld)getWorld();
        tileHeight = w.getTileHeight();
        tileLength = w.getTileLength();
    }

    public void act()
    {
        // Add your action code here.
    }

    protected boolean checkObstruction(){
        Obstruction top = (Obstruction)this.getOneObjectAtOffset(getX(), getY()-(tileHeight/2), Obstruction.class);
        Obstruction bottom = (Obstruction)this.getOneObjectAtOffset(getX(), getY()+(tileHeight/2), Obstruction.class);
        Obstruction left = (Obstruction)this.getOneObjectAtOffset(getX()-(tileHeight/2), getY(), Obstruction.class);
        Obstruction right = (Obstruction)this.getOneObjectAtOffset(getX()+(tileHeight/2), getY(), Obstruction.class);
        if(top == null && bottom == null && left == null && right == null){
            return false;
        }
        return true;
    }
}

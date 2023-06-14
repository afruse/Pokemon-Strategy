import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moveable Character is a class that is used to represent the player in the world with the trainer's and different rooms
 * 
 * @author (Daniel Tan) 
 * @version (1.0)
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
    String previousKey;
    GreenfootImage left = new GreenfootImage("left.png");
    GreenfootImage right = new GreenfootImage("left.png");
    GreenfootImage back = new GreenfootImage("back.png");
    GreenfootImage front = new GreenfootImage("front.png");
    GreenfootImage curImage;
    protected World world;
    /**
     * Constructer that takes in the index of the 2dArray in Gymworld to set position
     * @param mapIndexX     An int that represents the x Index of the 2dArray
     * @param mapIndexY     An int that represents the y Index of the 2dArray
     */
    public MoveableCharacter(int mapIndexX, int mapIndexY){
        this.mapIndexX = mapIndexX;
        this.mapIndexY = mapIndexY;
        previousMapIndexX = mapIndexX;
        previousMapIndexY = mapIndexY;
        int height = 60;
        int width = 90;
        left.scale(height, width);
        front.scale(height,width);
        back.scale(height,width);
        right.mirrorHorizontally();
        right.scale(height,width);
        setImage(front);
        curImage = front;
    }
    /**
     * The act method constantly checks if the player has pressed any of the game conctrols and acts accordingly toward it (WASD, Enter)
     */
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
                if(!key.equals(previousKey)){
                    setImage(back);
                    curImage = back;
                }
                else{
                    sucessfulMovement = w.moveCharacter(this, --mapIndexX, mapIndexY);
                }
            }
            else if (key.equals("s"))
            {
                if(!key.equals(previousKey)){
                    setImage(front);
                    curImage = front;
                }
                else{
                    sucessfulMovement = w.moveCharacter(this, ++mapIndexX, mapIndexY);
                }
            }
            else if (key.equals("a"))
            {
                if(!key.equals(previousKey)){
                    setImage(left);
                    curImage = left;
                }
                else{
                    sucessfulMovement = w.moveCharacter(this, mapIndexX, --mapIndexY);
                }
            }
            else if (key.equals("d"))
            {
                if(!key.equals(previousKey)){
                    
                    setImage(right);
                    curImage = right;
                }
                else{
                    sucessfulMovement = w.moveCharacter(this, mapIndexX, ++mapIndexY);
                }
            }
            else if (key.equals("enter")){
                w.checkEnemy(this);
            }
            if(!sucessfulMovement){
                mapIndexX = previousMapIndexX;
                mapIndexY = previousMapIndexY;
            }
            else{
            }
            previousKey = key;
        }
    }
    
    /**
     * This method sets the xIndex of the 2dArray to change positions
     * @param x     An integer which represents the new x Value of the 2dArray
     */
    protected void setMapIndexX(int x){
        mapIndexX = x;
    }
    /**
     * This method sets the yIndex of the 2dArray to change positions
     * @param y     An integer which represents the new y Value of the 2dArray
     */
    protected void setMapIndexY(int y){
        mapIndexY = y;
    }
   
    /**
     * This method returns the x Index of the 2dArray the player is on
     * @return int      Returns the x Index of the 2d Array
     */ 
    protected int getMapIndexX(){
        return mapIndexX;
    }
    
    /**
     * This method returns the y Index of the 2dArray the player is on
     * @return int      Returns the y Index of the 2d Array
     */ 
    protected int getMapIndexY(){
        return mapIndexY;
    }
}

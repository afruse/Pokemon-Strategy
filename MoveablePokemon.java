import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoveableCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveablePokemon extends Actor
{
    /**
     * Act - do whatever the MoveableCharacter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected boolean atLocation = false;
    protected boolean isPlayer;
    protected int speed;
    protected int health = 100;
    protected boolean isTurn = false;

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
    public MoveablePokemon(int mapIndexX, int mapIndexY){
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

    public void act()
    {
        // Add your action code here.

        checkKeyPress();
    }

    protected void checkKeyPress(){
        BattleWorld w = (BattleWorld)getWorld();
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
            else if (key.equals("space")){

            }
            if(!sucessfulMovement){
                mapIndexX = previousMapIndexX;
                mapIndexY = previousMapIndexY;
            }
            previousKey = key;
        }
    }

    protected void setMapIndexX(int x){
        mapIndexX = x;
    }

    protected void setMapIndexY(int y){
        mapIndexY = y;
    }

    public boolean interact(){
        return true;
    }

    public boolean getIsPlayer(){
        return isPlayer;
    }

    public int getSpeed(){
        return speed;
    }

    public int getHealth(){
        return health;
    }

    public void flipTurn(){
        if(isTurn){
            isTurn = false;
        }
        else{
            isTurn = true;
        }
    }

    public boolean getIsTurn(){
        return isTurn;
    }
}

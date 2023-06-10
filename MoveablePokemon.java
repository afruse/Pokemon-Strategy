import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
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

    protected boolean gettingAttacked = false;;

    protected boolean isFling = false;
    protected boolean atLocation = false;
    protected boolean isPlayer;
    protected int speed;
    protected int health = 100;
    protected boolean isTurn = false;
    protected boolean attacking = false;

    protected int mapIndexX;
    protected int mapIndexY;
    protected int previousMapIndexX;
    protected int previousMapIndexY;
    protected boolean sucessfulMovement;

    protected boolean isClickedOn = false;

    protected boolean didAction = false;

    String previousKey;
    GreenfootImage left = new GreenfootImage("left.png");
    GreenfootImage right = new GreenfootImage("left.png");
    GreenfootImage back = new GreenfootImage("back.png");
    GreenfootImage front = new GreenfootImage("front.png");
    GreenfootImage curImage;
    protected World world;
    public MoveablePokemon(int mapIndexX, int mapIndexY, boolean isPlayer){
        this.isPlayer = isPlayer;
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

    }

    protected boolean checkKeyPress(){
        BattleWorld w = (BattleWorld)getWorld();
        String key = Greenfoot.getKey();

        if (key != null)
        {
            //System.out.println(key);
            previousMapIndexX = mapIndexX;
            previousMapIndexY = mapIndexY;
            if (key.equals("w"))
            {

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

            previousKey = key;
            isClickedOn = false;

            if(!sucessfulMovement){
                mapIndexX = previousMapIndexX;
                mapIndexY = previousMapIndexY;
                return false;
            }
            else{

                return true;
            }
        }
        return false;
    }

    public boolean checkAttack(){
        String key = Greenfoot.getKey();
        if (key != null)
        {
            if (key.equals("space")){
                //Queue<MoveablePokemon> battleOrder = w.getBattleOrder();
                //ATTACK FOOL
                //Check top of battleOrder to see if the player is close

                //Do some attack

                sucessfulMovement = true;
                return true;
            }
        }
        return false;
    }

    /*
     * for(MoveablePokemon item: battleOrder){
    if(!item.getIsPlayer()){
    System.out.println("RAN");
    item.setFling();
    }
    }
     */

    protected boolean getDidAction(){
        return didAction;
    }

    protected int getMapIndexX(){
        return mapIndexX;
    }

    protected int getMapIndexY(){
        return mapIndexY;
    }

    protected boolean checkValidAttack(){

        //Check if the player is hitting ally or not
        //Check top of the battle order
        //Check if the current attacker is close enough

        return true;
    }

    protected boolean attack(){
        //Do soemthing
        return false;
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
            didAction = false;
            isTurn = false;
        }
        else{
            isTurn = true;
        }
    }

    public boolean getIsTurn(){
        return isTurn;
    }

    public void setFling(){
        isFling = true;
    }
    
    
    public double getMouseDistance(MoveablePokemon p){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int x = mouse.getX();
        int y = mouse.getY();
        return Math.sqrt(Math.pow(p.getX()-x, 2) + Math.pow(p.getY()-y,2));
    }
    
    
    
}

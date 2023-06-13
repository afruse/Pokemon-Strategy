import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovementDecider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovementDecider extends Actor
{
    /**
     * Act - do whatever the MovementDecider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int acts = 0;
    protected int continuedActs = 0;
    protected int curMovements;
    protected boolean changingFrames = false;
    protected GreenfootImage[] images = new GreenfootImage[4];
    protected int savedImage;
    public MovementDecider(){
        for(int i = 1; i < 5; i++){
            images[i-1] = new GreenfootImage("Numbers/" + i + ".png");
        }
    }

    public void act()
    {
        // Add your action code here.
        BattleWorld bw = (BattleWorld)getWorld();
        if(changingFrames){

            setImage(images[1+ Greenfoot.getRandomNumber(3)]);
            acts++;
        }
        if(acts == 180){
            changingFrames = false;
            setImage(images[savedImage-1]);
            continuedActs++;
        }
        if(continuedActs == 60){
            bw.getCurChar().unSetWait();
            acts = 0;
            continuedActs = 0;
        }
    }

    protected int decideMovements(){
        changingFrames = true;
        savedImage = 1+ Greenfoot.getRandomNumber(3);
        BattleWorld bw = (BattleWorld)getWorld();
        bw.getCurChar().setWait();

        return savedImage;

    }

}

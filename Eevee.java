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
        if(isTurn){

            if(isPlayer){
                if(checkKeyPress()){
                    didAction = true;
                }
            }
            else if(!isPlayer){
                //Do some algorithim crap
                if(checkKeyPress()){
                    didAction = true;
                }
            }

        }
        else if(Greenfoot.mouseClicked(this)){
            if(gettingAttacked){
                gettingAttacked = false;
            }
            else{
                gettingAttacked = true;
            }
        }
        if(gettingAttacked){
            String key = Greenfoot.getKey();
            if(key != null){
                if(key.equals("space")){
                    System.out.println("Attacked");
                    //Get cur person's turn and end their turn
                    gettingAttacked = false; 
                }

            }
        }

    }
}

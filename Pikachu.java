import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends MoveablePokemon
{
    /**
     * Act - do whatever the Pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Pikachu(int mapIndexX, int mapIndexY, boolean isPlayer){
        super(mapIndexX,mapIndexY, isPlayer);
        GreenfootImage image = new GreenfootImage("images/Pokemon/pika.png");
        setImage(image);
        int speed = 2;
    }

    public void act()
    {
        // Add your action code here.
        if(isTurn){
            didAction = false;

            if(Greenfoot.mouseClicked(MoveablePokemon.class)){
                if(getMouseDistance(this) > this.getImage().getWidth()){
                    attacking = true;
                }
                //get distance from mouse x,y & compare if not close to cur turn
                //Do attack
            }
            if(isPlayer){
                if(checkKeyPress()){
                    didAction = true;
                    isClickedOn = false;
                }
            }
            else if(attacking){
                if(checkAttack()){
                    didAction = true;
                    attacking = false;
                }
                //Do some algorithim crap
            }
        }
        else if(Greenfoot.mouseClicked(this) && !isTurn){
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
                    gettingAttacked = false; 
                }
            }

        }

    }
}

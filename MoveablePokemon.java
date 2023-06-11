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
    protected boolean triedAttack = false;
    protected String enemyAttackType;
    protected int cAttackRange;
    protected int vAttackRange;

    protected boolean gettingAttacked = false;;
    protected boolean isTurnEnd = true;
    protected boolean isFirstRun = true;

    protected int scenario;

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

    protected boolean didMove = false;

    String previousKey;
    GreenfootImage left = new GreenfootImage("left.png");
    GreenfootImage right = new GreenfootImage("left.png");
    GreenfootImage back = new GreenfootImage("back.png");
    GreenfootImage front = new GreenfootImage("front.png");
    GreenfootImage curImage;
    protected World world;

    MoveablePokemon victim;
    protected boolean enemyHit = false;
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

    public void doSomething(){
        if(isTurn && getWorld().getClass() == BattleWorld.class){

            if(isPlayer){
                if(isFirstRun){
                    didMove = false;
                    isTurnEnd = false;
                    isFirstRun = false;
                    victim = null;

                }
                if(!didMove && !attacking){
                    if(checkKeyPress("")){
                        didMove = true;
                    }
                }
                if(attacking){
                    if(isPlayer){
                        scenario = 1;
                    }
                    else{
                        scenario = 2;
                    }
                    String key = Greenfoot.getKey();

                    if (key != null)
                    {
                        //Check if it will error comparing a 0 length stirng with "c" or "v"
                        if (key.equals("c")){
                            didMove = true;
                            //System.out.println("c ATTACK " );
                            //check if close enough
                            if(checkValidHit(cAttackRange, this, victim)){
                                attackAnimationSwitch(this, victim, scenario);

                            }
                            else{
                                System.out.println("TOO SHORT");
                            }

                        }
                        else if(key.equals("v")){
                            didMove = true;
                            //System.out.println("v ATTACK " );

                            //check if close enough
                            if(checkValidHit(vAttackRange, this, victim)){
                                attackAnimationSwitch(this, victim, scenario);

                            }
                            else{
                                System.out.println("TOO SHORT");
                            }

                        }
                    }

                    //Do some algorithim crap
                }
                checkExit();
            }
            else{
                //If its enemy/AI [not ai just algorithin :(]
                if(isFirstRun){
                    didMove = false;
                    isTurnEnd = false;
                    isFirstRun = false;
                    triedAttack = false;
                    attacking = false;
                    victim = null;
                }
                if(!didMove){
                    if((Math.abs(getClosestPlayer().getMapIndexX() - this.getMapIndexX()) + Math.abs(getClosestPlayer().getMapIndexY() - this.getMapIndexY())) > 1 && checkKeyPress(getRandomDirectionKey())){
                        didMove = true;
                    }
                    else{
                        didMove = true;
                    }
                    //System.out.println("AAAA");
                    attacking = enemyCheckVictim();
                    //System.out.println("Is Attacking? " + attacking);

                }
                if(attacking){
                    if(isPlayer){
                        scenario = 1;
                    }
                    else{
                        scenario = 2;
                    }
                    enemyAttackType = getEnemyAttack();
                    //fix
                    // loop through list of players
                    // get the closest player
                    //check if it is close enough to hit
                    if (enemyAttackType.equals("c")){
                        didMove = true;
                        //System.out.println("c ATTACK " + cAttackRange);

                        //check if close enough
                        attackAnimationSwitch(this, victim, scenario);
                        /**
                        if(checkValidHit(cAttackRange, this, victim)){

                        }
                        else{
                        System.out.println("TOO SHORT");
                        }
                         */

                    }
                    else if(enemyAttackType.equals("v")){
                        didMove = true;
                        //System.out.println("v ATTACK " + vAttackRange);

                        attackAnimationSwitch(this, victim, scenario);

                        //check if close enough
                        /**
                        if(checkValidHit(vAttackRange, this, victim)){

                        }
                        else{
                        System.out.println("TOO SHORT");
                        }
                         */
                    }

                    //Do some algorithim crap
                }
                if(didMove && !attacking){
                    setExit();

                }
            }

        }
        if(getWorld().getClass() == BattleWorld.class){
            BattleWorld bw = (BattleWorld)getWorld();
            MoveablePokemon curChar = bw.getCurChar();
            if(Greenfoot.mouseClicked(this) && !isTurn && curChar.getVictim() == null){

                curChar.enemyHit(this);

                //get distance from mouse x,y & compare if not close to cur turn
                //Do attack
            }
            else if(Greenfoot.mouseClicked(this) && !isTurn && curChar.getVictim() != null && curChar.getVictim().getX() != this.getX() && curChar.getVictim().getY() != this.getY()){
                curChar.enemyHit(this);

            }
            else if(Greenfoot.mouseClicked(this) && !isTurn && curChar.getVictim() != null && curChar.getVictim().getX() == this.getX() && curChar.getVictim().getY() == this.getY()){
                curChar.enemyUnHit();
            }
        }

    }

    protected boolean checkKeyPress(String option){
        BattleWorld w = (BattleWorld)getWorld();
        if(option.length() == 0){
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
        }
        else{
            previousMapIndexX = mapIndexX;
            previousMapIndexY = mapIndexY;
            if (option.equals("w"))
            {

                sucessfulMovement = w.moveCharacter(this, --mapIndexX, mapIndexY);
                //System.out.println(option);

            }
            else if (option.equals("s"))
            {

                sucessfulMovement = w.moveCharacter(this, ++mapIndexX, mapIndexY);
                //System.out.println(option);

            }
            else if (option.equals("a"))
            {
                sucessfulMovement = w.moveCharacter(this, mapIndexX, --mapIndexY);
                //System.out.println(option);
            }
            else if (option.equals("d"))
            {

                sucessfulMovement = w.moveCharacter(this, mapIndexX, ++mapIndexY);
                //System.out.println(option);

            }

            previousKey = option;

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

    public boolean enemyCheckVictim(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        for(MoveablePokemon p : list){
            //System.out.println((this.getMapIndexX() != p.getMapIndexX() && this.getMapIndexY() != this.getMapIndexY()) + " AND THIS");
            System.out.println(cAttackRange + "             A  A A A TTACK ARANGE");
            if(checkValidHit(cAttackRange, this, p) && p.getIsPlayer()){
                victim = p;
                return true;
            }
        }
        for(MoveablePokemon p : list){
            //System.out.println(vAttackRange + "             A  A A A TTACK ARANGE");

            if(checkValidHit(vAttackRange, this, p) && p.getIsPlayer()){
                victim = p;
                return true;
            }
        }
        return false;

    }

    public String getEnemyAttack(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        for(MoveablePokemon p : list){
            if(checkValidHit(cAttackRange, this, p)){
                return "c";
            }
        }
        for(MoveablePokemon p : list){
            if(checkValidHit(vAttackRange, this, p)){
                return "v";
            }
        }
        return "";
    }

    public boolean checkExit(){
        String key = Greenfoot.getKey();
        if (key != null){
            if(key.equals("end")){
                isTurnEnd = true;
                isFirstRun = true;
            }
        }
        return false;
    }

    public void setExit(){
        isTurnEnd = true;
        isFirstRun = true;
        attacking = false;
    }

    public String checkAttack(){
        String key = Greenfoot.getKey();
        if (key != null)
        {
            //Check if it will error comparing a 0 length stirng with "c" or "v"
            if (key.equals("c")){
                //Queue<MoveablePokemon> battleOrder = w.getBattleOrder();
                //ATTACK FOOL
                //Check top of battleOrder to see if the player is close

                //Do some attack
                return "c";
            }
            else if(key.equals("v")){
                return "v";
            }
        }
        return "";
    }

    /*
     * for(MoveablePokemon item: battleOrder){
    if(!item.getIsPlayer()){
    System.out.println("RAN");
    item.setFling();
    }
    }
     */
    public void attackAnimationSwitch(MoveablePokemon attacker, MoveablePokemon victim, int scenario){

        BattleWorld bw = (BattleWorld)getWorld();
        Greenfoot.setWorld(new AttackAnimation(bw, attacker, victim, scenario));
    }

    protected boolean getIsTurnEnd(){
        return isTurnEnd;
    }

    protected boolean getDidMove(){
        return didMove;
    }

    protected int getMapIndexX(){
        return mapIndexX;
    }

    protected int getMapIndexY(){
        return mapIndexY;
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
            didMove = false;
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

    public double getMouseDistance(MoveablePokemon p, int mouseX, int mouseY){
        return Math.sqrt(Math.pow(p.getX()-mouseX, 2) + Math.pow(p.getY()-mouseY,2));
    }

    public int getMouseDistanceX(MoveablePokemon p, int mouseX){
        return Math.abs(p.getX()-mouseX);
    }

    public int getMouseDistanceY(MoveablePokemon p, int mouseY){
        return Math.abs(p.getY()-mouseY);
    }

    public boolean isPokemonOnSpace(int mouseX, int mouseY, MoveablePokemon caller){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        for(MoveablePokemon p : list){
            if(getMouseDistanceX(p, mouseX) < w.getTileLength()/2 && getMouseDistanceY(p,mouseY) < w.getTileHeight()/2 && (caller.getX() != p.getX() && caller.getY() != p.getY())){
                return true;
            }
        }
        return false;
    }

    public MoveablePokemon getPokemonOnSpace(int mouseX, int mouseY){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        for(MoveablePokemon p : list){
            if(getMouseDistanceX(p, mouseX) < w.getTileLength()/2 && getMouseDistanceY(p,mouseY) < w.getTileHeight()/2){
                return p;
            }
        }
        return null;
    }

    protected void a(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int mouseX = mouse.getX();
        int mouseY = mouse.getY();
        if(isPokemonOnSpace(mouseX, mouseY, this)){
            victim = getPokemonOnSpace(mouseX, mouseY);
            attacking = true;
            // Make a variable whichs stores the key of the last pressed key(c , v) and use that as type of attack

            //Get the actor the player clicked on
            //get all the actors in the world
            //check if the distance between each actor the the mouse coords is less by 1 coord
            //return the actor

            //check if the move can hit far enough to hit the enmy to proceed
        }
    }

    public void enemyHit(MoveablePokemon p){
        System.out.println("ENEMY SET");
        victim = p;
        attacking = true;
    }

    protected MoveablePokemon getVictim(){
        return victim;
    }

    public void enemyUnHit(){
        attacking = false;
        victim = null;
    }

    public String getRandomDirectionKey(){
        MoveablePokemon closestPokeman = getClosestPlayer();
        int savedDifY = Math.abs(this.getMapIndexY()- closestPokeman.getMapIndexY());
        int savedDifX = Math.abs(this.getMapIndexX() - closestPokeman.getMapIndexX());
        if(savedDifX >= savedDifY){
            if(closestPokeman.getMapIndexX() < this.getMapIndexX()){
                return "w";
            }
            else{
                return "s";
            }
        }
        else{
            if(closestPokeman.getMapIndexY() < this.getMapIndexY()){
                return "a";
            }
            else{
                //System.out.println("left");
                return "d";
            }
        }

    }

    public MoveablePokemon getClosestPlayer(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        int gridDistance = 1000000000;

        MoveablePokemon closestPokeman = null;
        for(MoveablePokemon p : list){
            int difY = Math.abs(this.getMapIndexY()- p.getMapIndexY());
            int difX = Math.abs(this.getMapIndexX() - p.getMapIndexX());
            if((difX + difY) < gridDistance && p.getIsPlayer()){
                gridDistance = difX + difY;
                closestPokeman = p;
            }
        }
        //System.out.println(closestPokeman.getClass());
        return closestPokeman;
    }

    protected boolean checkValidHit(int hitRange, MoveablePokemon attacker, MoveablePokemon victim){
        int difY = Math.abs(attacker.getMapIndexY()- victim.getMapIndexY());
        int difX = Math.abs(attacker.getMapIndexX() - victim.getMapIndexX());
        /*
        
        System.out.println("X " + attacker.getMapIndexX() + "            " + victim.getMapIndexX());
        System.out.println("Y " + attacker.getMapIndexY() + "            " + victim.getMapIndexY());

        System.out.println("HIT RANGE " + hitRange); 
        System.out.println(difX + " DIF X");
        System.out.println(difY + " DIF Y");
        System.out.println(((difY + difX) <= hitRange) + " AND " + (difY+difY != 0));
        System.out.println("DIF X + DIF Y = " + (difX + difY) + "             " + (difX+difY != 0));
         */
        if((difY + difX) <= hitRange && ((difX+difY) != 0)){
            //System.out.println(attacker.getClass() + " AND " + victim.getClass());

            return true;
        }

        return false;
    }

    protected void setAttackingFalse(){
        attacking = false;
    }
}

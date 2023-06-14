import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 * This is the superclass which all of the pokemon classes inherate from which is used in the grid battle part of the game
 * 
 * @author (Daniel Tan) 
 * @version 1.0
 */
public class MoveablePokemon extends Actor
{

    protected int movement;
    protected GreenfootImage hpLayout = new GreenfootImage("HpBar.png");
    protected SuperStatBar hpBar;
    protected SuperStatBar expBar;

    protected int xpNeeded;
    protected int curXp;

    protected int baseDef;
    protected int baseAtk;
    protected int baseHp;
    protected int baseSpeed;

    protected int cPower;
    protected int vPower;

    protected int maxHp;
    protected int def;
    protected int atk;
    protected int hp;
    protected int speed;

    GreenfootImage animationImage;
    GreenfootImage image;
    protected int imageMulti = 2;
    protected boolean alreadyAttacked = false;

    protected String cAttackString;
    protected String vAttackString;

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

    protected int lvl;
    String previousKey;
    protected boolean waiting = false;
    GreenfootImage curImage;
    protected World world;
    protected String attackKey = "c";
    MoveablePokemon victim;
    protected boolean enemyHit = false;
    protected boolean enemySet;
    protected boolean settingKeyAttackKey;
    /**
     * The constructer where it creates the pokemon and sets where it spawns in the grid
     * @param mapIndexX     Represents the x Index of the 2dArray of the grid
     * @param mapIndexY     Represents the y Index of the 2dArray of the grid
     * @param isPlayer      Represents if object is a player or an enemy(true is player and false is enemy)
     * @param lvl           Represents the lvl of the pokemon
     * @param curXp         Represents the current amount of xp the pokemon has
     * @param xpNeeded      Represents the xp needed for the pokemon to level up
     */
    public MoveablePokemon(int mapIndexX, int mapIndexY, boolean isPlayer, int lvl, int curXp, int xpNeeded){
        this.curXp = curXp;
        this.xpNeeded = xpNeeded;
        this.lvl = lvl;
        this.isPlayer = isPlayer;
        this.mapIndexX = mapIndexX;
        this.mapIndexY = mapIndexY;
        previousMapIndexX = mapIndexX;
        previousMapIndexY = mapIndexY;
        int height = 60;
        int width = 90;

    }
    public void setMaxXp(int xpNeeded){
        this.xpNeeded = xpNeeded;
    }
    public void setXp(int xp){
        this.curXp = xp;
    }
    public void setLevel(int level){
        this.lvl = level;
    }
    public int getXp(){
        return curXp;
    }
    public int getXpNeeded(){
        return xpNeeded;
    }
    /**
     * A method which returns the enlarged image version of the pokemon
     * @return GreenfootImage       returns the enlarged version of the pokemon as an image 
     */
    public GreenfootImage getAnimationImage(){
        return animationImage;
    }

    /**
     * A method which spawns a stat bar of xp and health
     * @param x     Represents the x coordinate of the statBar
     * @param y     Represents the y coordinatre of the statBar
     */
    public void spawnStatBar(int x, int y){
        int length = 80;
        int height = 5;
        hpBar = new SuperStatBar(maxHp, hp, null, length, height, 0, Color.GREEN, Color.GRAY, false,Color.BLACK, 1);
        HpBarLayout hpLayout= new HpBarLayout();
        HpBarIcon icon= new HpBarIcon(this.getImage());

        expBar = new SuperStatBar(xpNeeded, curXp, null, length, height-2,0,Color.BLUE, Color.GRAY, false, Color.BLACK,1);

        getWorld().addObject(hpLayout, x, y);
        getWorld().addObject(hpBar, x-10, y);
        getWorld().addObject(icon, x+55, y);
        getWorld().addObject(expBar, x-10, y+10);

    }

    /**
     * A method which updates the healthBar when called
     */
    public void updateStatBar(){
        hpBar.update(hp);
    }

    /**
     * A method that updates the xpBar when called
     */
    public void updateStatExpBar(){
        if(curXp > xpNeeded){
            while(curXp> xpNeeded){
                int xpDif = curXp-xpNeeded;
                curXp += 5;
                curXp = xpDif;
                lvl++;
            }
            updateStates();
        }
        hpBar.update(curXp);
    }

    /**
     * A method which updates the pokemon's stats when called based off lvl changes
     */
    public void updateStates(){
        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;
    }

    /**
     * A method which sets the pokemon's health to full when called
     */
    public void healToFull(){
        hp = maxHp;
    }

    /**
     * The method which decides what each player and pokemon can do
     */
    public void doSomething(){

        if(isTurn && getWorld().getClass() == BattleWorld.class){
            BattleWorld bw = (BattleWorld)getWorld();
            if(isFirstRun && isPlayer){
                movement = bw.getMovement();
                didMove = false;
                isTurnEnd = false;
                isFirstRun = false;
                victim = null;
                alreadyAttacked = false;
            }
            if(isFirstRun && !isPlayer){
                movement = bw.getMovement();

                didMove = false;
                isTurnEnd = false;
                isFirstRun = false;
                victim = null;

                attacking = false;
                triedAttack = false;
            }
            if(waiting){

            }
            else if(isPlayer){

                if(movement == 0 && alreadyAttacked){
                    System.out.println("This");
                    isTurnEnd = true;
                    didMove = true;
                    isFirstRun = true;
                }
                if(Greenfoot.isKeyDown("tab")){
                    setAttackOutline();
                }
                else if(bw.getObjects(SelectTile.class).size() != 0){
                    removeAllSelectTiles();
                }

                if(Greenfoot.isKeyDown("c")){
                    attackKey = "c";
                    enemyUnHit();
                }
                else if(Greenfoot.isKeyDown("v")){
                    attackKey = "v";
                    enemyUnHit();
                }

                if(!attacking && movement > 0){
                    if(checkKeyPress("") && movement > 0){
                        movement--;
                        if(movement == 0){
                            didMove = true;
                        }
                    }
                }

                if(attacking && !alreadyAttacked){
                    if(isPlayer){
                        scenario = 1;
                    }
                    else{
                        scenario = 2;
                    }
                    String key = Greenfoot.getKey();

                    if (attackKey.equals("c")){
                        attacking = false;
                        attackAnimationSwitch(this, victim, scenario, this.getCAttack(), this.getCPower());

                    }
                    else if(attackKey.equals("v")){
                        attacking = false;
                        attackAnimationSwitch(this, victim, scenario, this.getVAttack(), this.getVPower());

                    }
                    setAlreadyAttackedTrue();

                }
                checkExit();
            }
            else{
                //If its enemy/AI not ai just algorithin :(]

                if(movement > 0){
                    if((Math.abs(getClosestPlayer().getMapIndexX() - this.getMapIndexX()) + Math.abs(getClosestPlayer().getMapIndexY() - this.getMapIndexY())) > movement && checkKeyPress(getRandomDirectionKey())){
                        movement--;

                    }
                    else{
                        didMove = true;
                    }
                    attacking = enemyCheckVictim();

                }
                else{
                    didMove = true;
                }
                if(attacking){

                    if(isPlayer){
                        scenario = 1;
                    }
                    else{
                        scenario = 2;
                    }
                    enemyAttackType = getEnemyAttack();

                    bw.endCharTurn();
                    if (enemyAttackType.equals("c")){
                        didMove = true;

                        attackAnimationSwitch(this, victim, scenario, this.getCAttack(), this.getCPower());

                    }
                    else if(enemyAttackType.equals("v")){
                        didMove = true;

                        setExit();

                        attackAnimationSwitch(this, victim, scenario, this.getVAttack(), this.getVPower());

                    }
                }
                if(didMove && !attacking){
                    setExit();

                }
            }

        }
        if(getWorld().getClass() == BattleWorld.class){
            BattleWorld bw = (BattleWorld)getWorld();
            MoveablePokemon curChar = bw.getCurChar();
            if(Greenfoot.mouseClicked(this) && !curChar.getAlreadyAttacked() && !isTurn && curChar.getVictim() == null){
                curChar.enemyHit(this);

            }
            else if(Greenfoot.mouseClicked(this) && !curChar.getAlreadyAttacked() && !isTurn && curChar.getVictim() != null && curChar.getVictim().getX() != this.getX() && curChar.getVictim().getY() != this.getY()){

                curChar.enemyHit(this);
            }
            else if(Greenfoot.mouseClicked(this) && !curChar.getAlreadyAttacked() && !isTurn && curChar.getVictim() != null && curChar.getVictim().getX() == this.getX() && curChar.getVictim().getY() == this.getY()){

                curChar.readyToAttack();
            }
        }

    }

    /**
     * A method that moves the pokemon on the grid based on the key/String entered
     * @param option        A string which represents the choice to move mainly used for enemy and empty string means its for player and ignores
     * @return boolean      Returns true if the pokemon has sucessfuly moved and false if it couldn't due to obstructions/restrictions
     */
    protected boolean checkKeyPress(String option){
        sucessfulMovement = false;
        BattleWorld w = (BattleWorld)getWorld();
        if(option.length() == 0){
            String key = Greenfoot.getKey();
            if (key != null)
            {
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

            }
            else if (option.equals("s"))
            {

                sucessfulMovement = w.moveCharacter(this, ++mapIndexX, mapIndexY);

            }
            else if (option.equals("a"))
            {
                sucessfulMovement = w.moveCharacter(this, mapIndexX, --mapIndexY);
            }
            else if (option.equals("d"))
            {

                sucessfulMovement = w.moveCharacter(this, mapIndexX, ++mapIndexY);

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

    /**
     *  A method which spawns the pokemon's attack range based on the type of attack it has selected
     * 
     * Diagram:
     * 
     *    x
     *  x x x
     *x x 0 x x
     *  x x x
     *    x
     * 
     *  x 
     *x 0 x
     *  x
     * 
     */

    public void setAttackOutline(){
        BattleWorld bw = (BattleWorld)getWorld();

        bw.addObject(new SelectTile(), this.getX()+ bw.getTileLength(), this.getY());
        bw.addObject(new SelectTile(), this.getX()- bw.getTileLength(), this.getY());
        bw.addObject(new SelectTile(), this.getX(), this.getY()- bw.getTileHeight());
        bw.addObject(new SelectTile(), this.getX(), this.getY()+ bw.getTileHeight());
        if(attackKey.equals("v")){
            bw.addObject(new SelectTile(), this.getX()+ 2*bw.getTileLength(), this.getY());
            bw.addObject(new SelectTile(), this.getX()- 2*bw.getTileLength(), this.getY());
            bw.addObject(new SelectTile(), this.getX(), this.getY()- 2*bw.getTileHeight());
            bw.addObject(new SelectTile(), this.getX(), this.getY()+ 2*bw.getTileHeight());
            bw.addObject(new SelectTile(), this.getX()+ bw.getTileLength(), this.getY() + bw.getTileHeight());
            bw.addObject(new SelectTile(), this.getX()- bw.getTileLength(), this.getY() + bw.getTileHeight());
            bw.addObject(new SelectTile(), this.getX()+ bw.getTileLength(), this.getY() - bw.getTileHeight());
            bw.addObject(new SelectTile(), this.getX()- bw.getTileLength(), this.getY() - bw.getTileHeight());

        }

    }

    /**
     * A method that returns the amount of tiles the current pokemon's turn
     * @return int      Returns the number of tiles it can move
     */
    protected int getMovement(){
        return movement;
    }

    /**
     * A method that removes any of the SelectTile class objects from the world which is used for displaying attack ranged
     */
    public void removeAllSelectTiles(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<SelectTile> selectTileList = (ArrayList<SelectTile>)w.getObjects(SelectTile.class);
        for(SelectTile p: selectTileList){
            w.removeObject(p);
        }
    }

    /**
     * A method used for enemy pokemon to check if it is possible to attack the pokemon with it's given range and targets andy possible victims
     * @return boolean      Returns true if it can attack a player pokemon and false otherwise
     */
    public boolean enemyCheckVictim(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        for(MoveablePokemon p : list){

            if(checkValidHit(cAttackRange, this, p) && p.getIsPlayer()){
                victim = p;
                return true;
            }
        }
        for(MoveablePokemon p : list){

            if(checkValidHit(vAttackRange, this, p) && p.getIsPlayer()){
                victim = p;
                return true;
            }
        }
        return false;

    }

    /**
     * A method which gets the type of attack the enemy will use based off which pokemon are in it's attack range
     * @return String       A String which represents the type of attack(v,c);
     */
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

    /**
     * A method which checks if the player wants to end their turn
     * @return boolean      Returns false if not clicked
     */
    public boolean checkExit(){
        if(Greenfoot.isKeyDown("p")){
            isTurnEnd = true;
            isFirstRun = true;
            didMove = true;
        }
        return false;
    }

    /**
     * A method which sets the caller's turn to end
     */
    public void setExit(){
        isTurnEnd = true;
        isFirstRun = true;
        attacking = false;
    }

    /**
     * A method which gets the key the player clicks on and returns the type of attack based on that key
     * @return String       Returns the type of attack in a string(c, or, v)
     */
    public String checkAttack(){
        String key = Greenfoot.getKey();
        if (key != null)
        {

            if (key.equals("c")){

                return "c";
            }
            else if(key.equals("v")){
                return "v";
            }
        }
        return "";
    }

    /**
     * A method that switches world to a cutscene where the pokemon attacks the victim of it's choice
     * @param attacker      Rep the pokemon that is doing the attacking
     * @param victim        Rep the pokemon that is being attacked
     * @param scenario      An int that decides on the type of scenario(player being attacked, enemy being attacked)
     * @param attack        A String which represents the type of attack the caller is using
     * @param movePower     An int that represents the strength of the move
     */
    public void attackAnimationSwitch(MoveablePokemon attacker, MoveablePokemon victim, int scenario, String attack, int movePower){
        BattleWorld bw = (BattleWorld)getWorld();
        doDamage(victim.getDef(), movePower);
        Queue<MoveablePokemon> battleOrder = bw.getBattleOrder();
        Greenfoot.setWorld(new AttackAnimation(bw, attacker.getAnimationImage(), victim.getAnimationImage(), scenario, attack));

    }

    /**
     * A method which the attacker uses to deal damage to it's target
     * @param victimDef     An int which rep the defence of the victim
     * @param movePower     An int which rep the strength of the attack
     */
    public void doDamage(int victimDef, int movePower){
        int damage = (((2*lvl/5)+2 * movePower * this.getAtk()/victimDef)/50)+2;
        victim.setHp(victim.getHp() - damage);
        victim.updateStatBar();
    }

    /**
     * A method which returns if the current pokemon's turn is over or not
     * @return boolean       Returns true if turn did end and false if not
     */

    protected boolean getIsTurnEnd(){
        return isTurnEnd;
    }

    /**
     * A method which returns if the pokemon has done a "move" yet
     * @return boolean      Returns true if they did and false if not
     */
    protected boolean getDidMove(){
        return didMove;
    }

    /**
     * A method that returns the x Index of the pokemon in the 2dArray of the grid
     * @return int      Returns the x Index of the 2dArray 
     */
    protected int getMapIndexX(){
        return mapIndexX;
    }

    /**
     * A method that returns the y Index of the pokemon in the 2dArray of the grid
     * @return int      Returns the y Index of the 2dArray 
     */
    protected int getMapIndexY(){
        return mapIndexY;
    }

    /**
     * A method that sets the xIndex of the pokemon in the 2dArray
     * @param x        The index they want to set it to
     */
    protected void setMapIndexX(int x){
        mapIndexX = x;
    }

    /**
     * A method that sets the yIndex of the pokemon in the 2dArray
     * @param y        The index they want to set it to
     */
    protected void setMapIndexY(int y){
        mapIndexY = y;
    }

    /**
     * A method that returns if the caller is a player or an enemy
     * @return boolean      Returns true if it's a player and false if enemy
     */
    public boolean getIsPlayer(){
        return isPlayer;
    }

    /**
     * A method that switches the boolean switches of didMove and isTurn when called
     */
    public void flipTurn(){
        if(isTurn){
            didMove = false;
            isTurn = false;
        }
        else{
            isTurn = true;
        }
    }

    /**
     * A method that returns if it is the caller's turn or not
     * @return boolean      Returns true if is turn and false otherwise
     */
    public boolean getIsTurn(){
        return isTurn;
    }

    /**
     * A method that returns the x distance between the mouse and the passed in pokemon
     * @param p         Rep the pokemon you want to compare the mouse to
     * @param mouseX    Rep the x value of the mouse
     * @return int      Returns the x distance between the mouse and pokemon
     */
    public int getMouseDistanceX(MoveablePokemon p, int mouseX){
        return Math.abs(p.getX()-mouseX);
    }

    /**
     * A method that returns the y distance between the mouse and the passed in pokemon
     * @param p         Rep the pokemon you want to compare the mouse to
     * @param mousey    Rep the y value of the mouse
     * @return int      Returns the y distance between the mouse and pokemon
     */
    public int getMouseDistanceY(MoveablePokemon p, int mouseY){
        return Math.abs(p.getY()-mouseY);
    }

    /**
     * Sets the target of the caller to the passed in pokemon
     * @param p     Rep the pokemon being targetted
     */
    public void enemyHit(MoveablePokemon p){
        victim = p;
        enemySet = true;
    }

    /**
     * A method which allows the caller to attack and switch to cutscene world
     */
    public void readyToAttack(){
        if(attackKey.equals("v")){
            if(checkValidHit(vAttackRange, this, victim)){
                attacking = true;
            }
            else{
            }
        }
        else if(attackKey.equals("c")){
            if(checkValidHit(cAttackRange, this, victim)){
                attacking = true;
            }
            else{
            }
        }

    }

    /**
     * A method that returns the caller's set victim
     * @return MoveablePokemon      Returns what the caller's victim is set to
     */
    protected MoveablePokemon getVictim(){
        return victim;
    }

    /**
     * A method which resets the caller's target
     */
    public void enemyUnHit(){
        attacking = false;
        victim = null;
    }

    /**
     * A method that returns if the target of the caller is set
     * @return boolean     Returns true if target is set and false otherwise
     */
    protected boolean getIsEnemySet(){
        if(enemySet == false){
            return false;
        }
        return true;
    }

    /**
     * A method that returns a key which rep direction the enemy wants to move towards to
     * @param String    Returns a string of the direction the caller wants to move into (WASD)
     */
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
                return "d";
            }
        }

    }

    /**
     * A method that gets the closest pokemon
     * @return MoveablePokemon      Returns the closest pokemon that is a player
     */
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
        return closestPokeman;
    }

    /**
     * Method which checks if the attack selected can reach the enemy
     * @param hitRange      An int which rep the tile length of the attack
     * @param attacker      Rep the attacker 
     * @param victim        Rep the victim
     * @retun boolean       True if the attack can reach and false if not
     */
    protected boolean checkValidHit(int hitRange, MoveablePokemon attacker, MoveablePokemon victim){
        int difY = Math.abs(attacker.getMapIndexY()- victim.getMapIndexY());
        int difX = Math.abs(attacker.getMapIndexX() - victim.getMapIndexX());

        if((difY + difX) <= hitRange && ((difX+difY) != 0)){

            return true;
        }

        return false;
    }

    /**
     * A method which sets the boolean switch to attack to false
     */
    protected void setAttackingFalse(){
        attacking = false;
    }

    /**
     * A method which returns the String of the v attack
     * @return String       Returns the string of attack "v"
     */
    protected String getVAttack(){
        return vAttackString;
    }

    /**
     * A method which returns the String of the c attack
     * @return String       Returns the string of attack "c"
     */
    protected String getCAttack(){
        return cAttackString;
    }

    /**
     * A method which returns the attack range of the given attack
     * @param a     Rep the type of attack (c,v)
     * @return int  Returns the hit range
     */
    protected int getAttackRange(String a){
        if(a.equals("c")){
            return cAttackRange;
        }
        else if(a.equals("v")){
            return vAttackRange;
        }
        return 0;
    }

    /**
     * A method which returns the stat defence stat
     * @return int      Returns the defence
     */
    protected int getDef(){
        return def;
    }

    /**
     * A method which returns the stat attack stat
     * @return int      Returns the attack stat
     */
    protected int getAtk(){
        return atk;
    }

    /**
     * A method which returns the stat
     * @return int      Returns the speed stat
     */
    protected int getSpeed(){
        return speed;
    }

    /**
     * A method which returns the stat
     * @return int      Returns the hp stat
     */
    protected int getHp(){
        return hp;
    }

    /**
     * A method which returns the attack power of v
     * @return int      Returns the attack power    
     */
    protected int getVPower(){
        return vPower;
    }

    /**
     * A method which returns the attack power of c
     * @return int      Returns the attack power    
     */
    protected int getCPower(){
        return cPower;
    }

    /**
     * A method which returns the caller's level
     * @return int      Returns the level of the attack
     */
    protected int getLvl(){
        return lvl;
    }

    /**
     * A method which sets the caller's hp
     * @param newHp     Rep the newHp that is being set
     */
    protected void setHp(int newHp){
        hp = newHp;
    }

    /**
     * A method which increases the caller's xp
     * @param xp        Rep the xp othat is being gained
     */
    protected void gainXp(int xp){
        curXp += xp;
        updateStatExpBar();
    }

    /**
     * A method which sets the boolean switch waiting to true
     */
    protected void setWait(){
        waiting = true;
    }

    /**
     * A method which sets the boolean switch waiting to false
     */
    protected void unSetWait(){
        waiting = false;
    }
    /**
     * A method which returns if the caller has already attacled
     * @return boolean      Returns true if already attacked and false otherwise
     */
    protected boolean getAlreadyAttacked(){
        return alreadyAttacked;
    }
    /**
     * A method that updates the caller has attacked
     */
    protected void setAlreadyAttackedTrue(){
        alreadyAttacked = true;
    }
}

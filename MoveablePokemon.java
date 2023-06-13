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

    GreenfootImage curImage;
    protected World world;
    protected String attackKey = "c";
    MoveablePokemon victim;
    protected boolean enemyHit = false;
    protected boolean enemySet;
    protected boolean settingKeyAttackKey;
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

    public GreenfootImage getAnimationImage(){
        return animationImage;
    }

    public void act()
    {
        // Add your action code here.

    }

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

    public void updateStatBar(){
        hpBar.update(hp);
    }

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

    public void updateStates(){
        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;
    }

    public void healToFull(){
        hp = maxHp;
    }

    public void doSomething(){

        if(isTurn && getWorld().getClass() == BattleWorld.class){
            if(isPlayer){
                if(Greenfoot.isKeyDown("tab")){
                    setAttackOutline();
                }
                else{
                    removeAllSelectTiles();
                }
                
                if(Greenfoot.isKeyDown("c")){
                    attackKey = "c";
                }
                else if(Greenfoot.isKeyDown("v")){
                    attackKey = "v";
                }
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

                    if (true)
                    {
                        //Check if it will error comparing a 0 length stirng with "c" or "v"
                        if (attackKey.equals("c")){
                            didMove = true;
                            //System.out.println("c ATTACK " );
                            //check if close enough
                            attacking = false;
                            attackAnimationSwitch(this, victim, scenario, this.getCAttack(), this.getCPower());

                        }
                        else if(attackKey.equals("v")){
                            didMove = true;
                            //System.out.println("v ATTACK " );
                            attacking = false;
                            attackAnimationSwitch(this, victim, scenario, this.getVAttack(), this.getVPower());
                            //check if close enough

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
                    //alreadyAttacked = false;
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
                    BattleWorld bw = (BattleWorld)getWorld();
                    bw.endCharTurn();
                    if (enemyAttackType.equals("c")){
                        didMove = true;
                        //System.out.println("c ATTACK " + cAttackRange);

                        //check if close enough
                        attackAnimationSwitch(this, victim, scenario, this.getCAttack(), this.getCPower());
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
                        setExit();
                        attackAnimationSwitch(this, victim, scenario, this.getVAttack(), this.getVPower());

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
                //If it is another actor
                curChar.enemyHit(this);
            }
            else if(Greenfoot.mouseClicked(this) && !isTurn && curChar.getVictim() != null && curChar.getVictim().getX() == this.getX() && curChar.getVictim().getY() == this.getY()){
                curChar.readyToAttack();
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

    public void setAttackOutline(){
        BattleWorld bw = (BattleWorld)getWorld();
        removeAllSelectTiles();
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
        /**
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

    }

    public void removeAllSelectTiles(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<SelectTile> selectTileList = (ArrayList<SelectTile>)w.getObjects(SelectTile.class);
        for(SelectTile p: selectTileList){
            w.removeObject(p);
        }
    }

    public boolean enemyCheckVictim(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<MoveablePokemon> list =(ArrayList<MoveablePokemon>)w.getObjects(MoveablePokemon.class);
        for(MoveablePokemon p : list){
            //System.out.println((this.getMapIndexX() != p.getMapIndexX() && this.getMapIndexY() != this.getMapIndexY()) + " AND THIS");
            //System.out.println(cAttackRange + "             A  A A A TTACK ARANGE");
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
            if(key.equals("p")){
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
    public void attackAnimationSwitch(MoveablePokemon attacker, MoveablePokemon victim, int scenario, String attack, int movePower){

        BattleWorld bw = (BattleWorld)getWorld();
        //attacker.swapToAnimationImage();
        //victim.swapToAnimationImage();
        //bw.setCurAttacker(attacker);
        //bw.setCurVictim(victim);
        //System.out.println(victim.getHp());
        doDamage(victim.getDef(), movePower);
        //System.out.println(victim.getHp());

        Queue<MoveablePokemon> battleOrder = bw.getBattleOrder();
        //setExit();
        Greenfoot.setWorld(new AttackAnimation(bw, attacker.getAnimationImage(), victim.getAnimationImage(), scenario, attack));

    }

    public void doDamage(int victimDef, int movePower){
        int damage = (((2*lvl/5)+2 * movePower * this.getAtk()/victimDef)/50)+2;
        victim.setHp(victim.getHp() - damage);
        //System.out.println(victim.getClass() + "   " + victim.getHp());
        victim.updateStatBar();
    }

    public void swapToAnimationImage(){
        setImage(animationImage);
    }

    public void swapToGridImage(){
        //System.out.println("RAN");
        setImage(image);
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
        enemySet = true;
    }

    public void readyToAttack(){
        if(attackKey.equals("v")){
            if(checkValidHit(vAttackRange, this, victim)){
                attacking = true;
            }
            else{
                System.out.println("TOO SHORT");
            }
        }
        else if(attackKey.equals("c")){
            if(checkValidHit(cAttackRange, this, victim)){
                attacking = true;
            }
            else{
                System.out.println("TOO SHORT");
            }
        }

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

    protected String getCAttack(){
        return cAttackString;
    }

    protected int getAttackRange(String a){
        if(a.equals("c")){
            return cAttackRange;
        }
        else if(a.equals("v")){
            return vAttackRange;
        }
        return 0;
    }

    protected String getVAttack(){
        return vAttackString;
    }

    protected int getDef(){
        return def;
    }

    protected int getAtk(){
        return atk;
    }

    protected int getSpeed(){
        return speed;
    }

    protected int getHp(){
        return hp;
    }

    protected int getVPower(){
        return vPower;
    }

    protected int getCPower(){
        return cPower;
    }

    protected int getLvl(){
        return lvl;
    }

    protected void setHp(int newHp){
        hp = newHp;
    }

    protected void gainXp(int xp){
        curXp += xp;
        updateStatExpBar();
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * This method represends the grid screen of when the pokemon are in battle
 * 
 * @author (Daniel) 
 * @version 1.0
 */
public class BattleWorld extends StorageWorld
{
    BattleManager b;
    ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();
    protected int tileLength;
    protected int tileHeight;

    protected MoveablePokemon[] enemyTeam;
    MoveablePokemon curAttacker;
    MoveablePokemon curVictim;
    MovementDecider decider;

    protected boolean justStarted = true;
    GymWorld gw;
    /**
     * A constructor for BattleWorld
     * @param gw    Stores the world the trainer was in before entering battle
     * @param enemyTeam     An array which represents the enemy team
     * 
     */
    public BattleWorld(GymWorld gw, MoveablePokemon[] enemyTeam)
    {    

        //Add a parameter to get an array for play party and another array for enemy party

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        decider = new MovementDecider();
        addObject(decider, getWidth()-25, 180);

        this.gw = gw;
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        b = new BattleManager(playerTeam, enemyTeam);
        curAttacker = b.getCurChar();
        curVictim = b.getCurChar();
        MoveablePokemon c = playerTeam[0];
        MoveablePokemon c1 = playerTeam[1];

        MoveablePokemon e = enemyTeam[0];
        MoveablePokemon e1 = enemyTeam[1];
        addObject(e,0,0);
        addObject(e1,0,0);

        addObject(c,0,0);
        addObject(c1,0,0);

        tileLength = 50;
        tileHeight = 50;
        int i = 0;
        for(int yStart = tileHeight/4; yStart < this.getHeight()-tileHeight/4; yStart += tileHeight){
            map.add(new ArrayList<Coordinate>());
            for(int xStart = tileLength/4; xStart < this.getWidth()-(tileLength/4)-200; xStart += tileLength){
                Coordinate curCoord = new Coordinate(xStart+15, yStart);
                map.get(i).add(curCoord);
            }
            i++;
        }
        //addObject(c, , );
        c.setLocation(map.get(map.size()-3).get(4).getXCoord(), map.get(map.size()-3).get(4).getYCoord());
        c.setMapIndexX(map.size()-3);
        c.setMapIndexY(4);

        c1.setLocation(map.get(map.size()-3).get(7).getXCoord(), map.get(map.size()-3).get(7).getYCoord());
        c1.setMapIndexX(map.size()-3);
        c1.setMapIndexY(7);

        e.setLocation(map.get(3).get(5).getXCoord(), map.get(3).get(5).getYCoord());
        e.setMapIndexX(3);
        e.setMapIndexY(5);

        e1.setLocation(map.get(3).get(8).getXCoord(), map.get(3).get(8).getYCoord());
        e1.setMapIndexX(3);
        e1.setMapIndexY(8);

        GreenfootImage backround = new GreenfootImage("GrassBackround1.png");
        setBackground(backround);
        addObject(b, 0,0);
        justStarted = false;

        c.spawnStatBar(getWidth()-100, getHeight()-50);
        e.spawnStatBar(getWidth()-100, 50);
        c1.spawnStatBar(getWidth()-100, getHeight()-110);
        e1.spawnStatBar(getWidth()-100, 110);
        started();
    }

    /**
     * Acts and check if the player has clicked on the world to reset enemy selected
     */
    public void act(){
        if(Greenfoot.mouseClicked(this) && getCurChar().getIsEnemySet()){
            getCurChar().enemyUnHit();
        }
    }

    /**
     * A getter method that calls the decider object created to get the number of tiles the pokemon can move
     */
    protected int getMovement(){
        return decider.decideMovements();
    }
    /**
     * starts music when world started
     */
    public void started()
    {
        battleWorldSound.playLoop();
    }
    /**
     * stops music when world or code stopped
     */
    public void stopped()
    {

        battleWorldSound.stop();
    }

    /**
     * Sets the current attacker of the current pokemon
     * @param p     The pokemon that's going to attack
     */
    protected void setCurAttacker(MoveablePokemon p){
        curAttacker = p;
    }

    /**
     * Sets the victim of the current attacker
     * @param p     The pokemon thats going to be set as the target
     */    
    protected void setCurVictim(MoveablePokemon p){
        curVictim = p;
    }

    protected void removeFromQueue(MoveablePokemon p){
        b.removePokemon(p);
    }

    /**
     * Gets the current queue of the pokemon that goes next
     * @return Queue<MoveablePokemon>   Returns the battle order of the pokemon in battle
     * 
     */
    protected Queue<MoveablePokemon> getBattleOrder(){
        return b.getBattleOrder();
    }

    /**
     * Returns the tileHeight of the 2d grid
     * @return int  Returns the tile height
     * 
     */
    protected int getTileHeight(){
        return  tileHeight;
    }

    /**
     * Returns tile length of the 2d grid
     * return int Returns tile length
     */
    protected int getTileLength(){
        return tileLength;
    }

    /**
     * Gets the current pokemon thats taking action
     * @return MoveablePokemon      Returns the pokemon at the front of the queue
     */
    protected MoveablePokemon getCurChar(){
        return b.getCurChar();
    }

    /**
     * Gets the 2d array of the battle world
     * @return ArrayList<ArrayList<Coordinate>>     Returns the 2d arraylist of coordinates which rep grid tiles
     */
    protected ArrayList<ArrayList<Coordinate>> getMap(){
        return map;
    }

    /**
     * Moves the pokemon on teh 2d grid
     * @param a     Represents the pokemon thats being moved
     * @param mapIndexX     An int represents the xIndex of the 2d arraylist grid
     * @param mapIndexY     An int that represents the yIndex of the 2d arraylist grid
     * @return boolean      Returns true if character was able to move an false otherwise
     */
    protected boolean moveCharacter(MoveablePokemon a, int mapIndexX, int mapIndexY){
        try{
            Coordinate coord = map.get(mapIndexX).get(mapIndexY);
            if(checkObstruction(coord) == false){
                //If there are no obstructions in the next step
                a.setLocation(coord.getXCoord(), coord.getYCoord());
                return true;
            }
            else{
                //If there is 
                return false;
            }
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    /**
     * A method that checks if there is an obsuruction in the player/enemy's wanted direction to avoid actors on the same tile
     * @param coord     Represents the coordinate that needs to be checked in the 2d arrayList
     * @return boolean   Returns true if there is no obstruction and false otherwise
     */
    protected boolean checkObstruction(Coordinate coord){
        int x = coord.getXCoord();
        int y = coord.getYCoord();
        BattleWorldChecker c = new BattleWorldChecker();
        addObject(c,0,0);
        boolean isObstruction = c.check(coord);
        removeObject(c);
        return isObstruction;
    }

    
    /**
     * A method that ends the current character's turn
     */
    protected void endCharTurn(){
        b.endTurn();
    }

    /**
     * A method that switches the world back to the player's world where they challenged the enemy
     */
    protected void switchToGymWorld(){
        stopped();
        gw.started();
        Greenfoot.setWorld(gw);
    }
}

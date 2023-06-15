import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This class acts as a storage base which declares, intitializes, and stores all the pokemon teams within the world allowing each world to pass 
 * the status of each pokemon team whether they win/lose
 * 
 * @author (Daniel) 
 * @version 1.0
 */
public class StorageWorld extends World
{
    //
    static int startXpNeeded = 20;
    //Player team
    static Pikachu pikachu = new Pikachu(0,0, true, 5, 0, startXpNeeded);

    static Eevee eevee = new Eevee(2,5,true, 5, 0, startXpNeeded);

    static MoveablePokemon[] playerTeam = {pikachu, eevee};
    
    //All enemy teams
    
    //FIRST FLOOR - EASY
    static Wooper wooper = new Wooper(2,5, false, 5, 0, startXpNeeded); //water gun (water), mud slap (ground)
    static Pidgey pidgey = new Pidgey(2,5, false, 5,0, startXpNeeded); //gust (wind), quick attack
    static MoveablePokemon[] enemyTeam_1_Left = {wooper, pidgey};

    static Rattata rattata = new Rattata(2,5, false, 5,0, startXpNeeded); // Stomp, Quick Attack
    static Zubat zubat = new Zubat(2, 5, false, 5,0, startXpNeeded); // Bite, Air Cutter
    static MoveablePokemon[] enemyTeam_1_Right = {rattata, zubat};

    //SECOND FLOOR - MEDIUM
    static Misdreavus misdreavus= new Misdreavus(2,5, false, 10,0, startXpNeeded); //confusion (psycic), spite (ghost)
    static Rapidash rapidash= new Rapidash(2,5, false, 10,0, startXpNeeded); //ember (fire), stomp (norm.)
    static MoveablePokemon[] enemyTeam_2_Left = {misdreavus, rapidash};

    static Wartortle wartortle = new Wartortle(2,5,false,10,0, startXpNeeded); //water gun, bite
    static Raticate raticate = new Raticate(2,5,false,10,0, startXpNeeded); //bite, hyper beam
    static MoveablePokemon[] enemyTeam_2_Middle = {wartortle, raticate};

    static Tauros tauros = new Tauros(2,5,false,10,0, startXpNeeded); // stomp, tackle
    static Lapras lapras = new Lapras(2,5,false,10,0, startXpNeeded); //Tackle, aurora beam
    static MoveablePokemon[] enemyTeam_2_Right = {tauros, lapras};

    
    
    //THIRD FLOOR - HARD
    static Dragonite dragonite= new Dragonite(2,5, false, 15,0, startXpNeeded); //outrage (dragon), wing attack (flying)
    static Suicune suicune= new Suicune(2,5, false, 15,0, startXpNeeded); //bubble beam (water), aurora beam (ice)
    static MoveablePokemon[] enemyTeam_3_Left = {dragonite, suicune};

    static Tyranitar tyranitar= new Tyranitar(2,5,false,15,0, startXpNeeded);// hyper  beam, bite
    static Zapdos zapdos = new Zapdos(2,5,false,15,0, startXpNeeded); //thundershock, wing attack
    static MoveablePokemon[] enemyTeam_3_Right = {tyranitar, zapdos};


    //FOURTH FLOOR- BOSS

    static Snorlax snorlax = new Snorlax(2,5, false, 20,0, startXpNeeded);
    static Rayquaza rayquaza = new Rayquaza(2,5, false, 20,0, startXpNeeded);
    static MoveablePokemon[] enemyTeam_4 = {snorlax, rayquaza};

     
    protected static RoomInfo[][] roomOrder = new RoomInfo[5][5];
    /**
     * A costructer for the class StorageWorld
     * Sets up all the rooms the player can move through
     * 
     */
    public StorageWorld()
    {    
        super(700, 600, 1, false); 
        /**
         * How it looks
         *   0  1  2  3  4
         * 0       X
         * 1    X     X
         * 2  X    X     X
         * 3    X     X
         * 4       X
         * 
         * X = Room
         */
        roomOrder[4][2] = new RoomInfo(new GreenfootImage("GymStart.PNG"), 700,600, true, true, false, false);

        roomOrder[0][2] = new RoomInfo(new GreenfootImage("GymEnd.PNG"), 700, 600, false, false, true, true);
        roomOrder[0][2].addEnemyTeam(enemyTeam_4,0,4);

        setBackground(roomOrder[4][2].getRoomImage());

        //Other Rooms
        roomOrder[3][1] = new RoomInfo(new GreenfootImage("[3][1].png"), 700, 600, true, true, false, true);
        roomOrder[3][1].addEnemyTeam(enemyTeam_1_Left,1,4);

        roomOrder[2][0] = new RoomInfo(new GreenfootImage("[2][0].png"), 700, 600, false, true, false, true);
        roomOrder[2][0].addEnemyTeam(enemyTeam_2_Left,1,4);

        roomOrder[1][1] = new RoomInfo(new GreenfootImage("[1][1].png"), 700, 600,false, true, true, true);
        roomOrder[1][1].addEnemyTeam(enemyTeam_3_Left,1,4);

        
        
        
        //Right side
        roomOrder[3][3] = new RoomInfo(new GreenfootImage("[3][3].png"), 700, 600, true, true, true, false);
        roomOrder[3][3].addEnemyTeam(enemyTeam_1_Right,1,4);

        roomOrder[2][4] = new RoomInfo(new GreenfootImage("[2][4].png"), 700, 600,true, false, true, false);
        roomOrder[2][4].addEnemyTeam(enemyTeam_2_Right,1,4);

        roomOrder[1][3] = new RoomInfo(new GreenfootImage("[1][3].png"), 700, 600, true, false, true, true);
        roomOrder[1][3].addEnemyTeam(enemyTeam_2_Right,1,4);

        //Middle
        roomOrder[2][2] = new RoomInfo(new GreenfootImage("[2][2].png"), 700, 600, true, true, true, true);
        roomOrder[2][2].addEnemyTeam(enemyTeam_2_Middle,1,4);

        
                
        
        
    }
    /**
     * This method sets the player's team from the save file when called
     */
    protected void setPokemonTeam(String[][]team){
        if(team[0][0].equals("Pikachu")){
            //Pikachu p1 = new Pikachu(0,0, true, String)
        }
    }
}

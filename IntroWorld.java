/**
 * Credits:
 * Visual:
 * Intro GIF: https://gfycat.com/keysarcasticgangesdolphin
 * Options Sprite: https://www.spriters-resource.com/fullview/3853/?source=genre
 * WASD & ARROW key pics: https://www.freepik.com/premium-vector/keyboard-button-arrow-wasd-set-icon-simple-minimal-flat-vector-app-web-design_25481867.htm
 * Pokemon move pictures/sprites: https://www.smogon.com/forums/threads/move-ability-animations-thread.3568451/
 * Leafeon sprites: https://pokemondb.net/sprites/leafeon
 * Battle Backgroun: https://pokemon-reborn.fandom.com/wiki/Grassy_Terrain
 * Gym Background: http://www.psypokes.com/emerald/gymlayouts.php
 * Pokemon pictures: https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_catch_rate
 * Pokemon move sprites: https://www.spriters-resource.com/fullview/28883/
 * HP bar picture: https://iwanrjonesproductions.wordpress.com/2019/01/11/game-dev-narrative-story/
 * Tilesets: https://reliccastle.com/resources/15/
 * Pokemon Calculations:
 * https://bulbapedia.bulbagarden.net/wiki/Stat
 * https://bulbapedia.bulbagarden.net/wiki/Damage
 * https://bulbapedia.bulbagarden.net/wiki/Thunder_Shock_(move)
 * https://bulbapedia.bulbagarden.net/wiki/Bite_(move)
 * https://bulbapedia.bulbagarden.net/wiki/Sand_Attack_(move)
 * https://pokemondb.net/pokedex/eevee
 * https://pokemondb.net/pokedex/pikachu
 * Music:
 * Intro music: https://www.youtube.com/watch?v=rg6CiPI6h2g
 * Gym world music: https://soundcloud.com/pokemon-gameboy-music/indigo-plateau-1
 * Battle World music: https://www.youtube.com/watch?v=VxFadPqMbfM
 * Code:
 * Anthony W for GifIntroWorldBackground class
 */

/**
 * Features:
 *  - Auto save
 *  - 8 unique pokemon teams
 *  - different moves for each pokemon
 *  - multiple 2D data structures
 *  - Animated intro
 *  - LOTS OF ANIMATION
 *  - used queue
 *  - custom cutscene
 *  - elite voice acting
 *  - advanced file saving and loading
 *  - advanced ish typing world and options screen
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The intro screen plays a GIF and music
 * 
 * @author Affan H 
 * @version 1.0
 * 
 * Instructions: Your goal is to beat the gymleader by going through the doors
 * and beating the pokemon trainers along the way. Although we did give the player to 
 * go past the doors and skip trainers, we recommened to fight the trainers their current level
 * trainers and to slowly progress.
 * Controls in Gym World:    
 *              Enter to enter battle
 *              W - Forward
 *              A - Left
 *              D - Right
 *              S - Back
 * This is a turn based battle where you try and defeat the enemy pokemon. 
 * Your turn is deicded on the right when your pokemon is at the top of the action bar.
 * When it is a player/enemies turn, a dice will roll from 1-4 and determine how many tiles someone can move
 * Player's team gains experience as you beat enemy trainers and level up to increase stats
 * Players are free to do the same battle again to level up if the next opponents are too dificult
 * Controls in Battle  
 *              W - Forward
 *              A - Left
 *              D - Right
 *              S - Back
 *              Tab - See player's attack range
 *              C - Sets attack to c Attack(short range with more damage)
 *              V - Sets attack to v Attack(long range with less damage)
 *              Left Click - Target you want to attack
 *              Click Again - to initiate attack
 *              P - End turn
 *              Left Click backround to reset target
 *  
 *  Notes: 
 *  - Auto saves every move. Therefore player can just exit whenever they want but will not save in battle interacctions
 *  - Did not have enough time to implement what level each pokemon is. But they start off at 5 and increment by 5 through each level of the room
 *  up to a maximum level of 20
 *  - Trainer starts at lvl 5 when creating a new file
 *  
 * 
 */

public class IntroWorld extends World
{
    private GreenfootSound introWorldSound;
    private GifIntroWorldBackground backGroundAni;
    /**
     * Constructor for objects of class IntroWorld.
     * 
     */
    public IntroWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1, false); 
        Greenfoot.setSpeed(50);
        backGroundAni = new GifIntroWorldBackground();
        addObject(backGroundAni, -400, -300);
        addObject(new GifOverlay(), 350, 300);
        setBackground(backGroundAni.getImage());
        introWorldSound = new GreenfootSound("intro.mp3");
    }
    /**
     * checks when code is started then starts playing music
     */
    public void started()
    {
        introWorldSound.playLoop();
    }
    /**
     * stops playing music when code stopped or world switched
     */
    public void stopped()
    {
        introWorldSound.stop();
    }
    /**
     * Checks to see if user wants to start
     */
    public void act()
    {
       if(Greenfoot.isKeyDown("ENTER")){
           //GymWorld gw = new GymWorld();
           LoreWorld w = new LoreWorld();
           stopped();
           Greenfoot.setWorld(w);
       }
    }
}

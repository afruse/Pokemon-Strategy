import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eevee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suicune extends MoveablePokemon
{

    /**
     * Act - do whatever the Eevee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Suicune(int mapIndexX, int mapIndexY, boolean isPlayer, int lvl){
        super(mapIndexX,mapIndexY, isPlayer, lvl);
        cAttackString = "Aurora Beam";
        vAttackString = "Bubble Beam";
        image = new GreenfootImage("images/Pokemon/suicune.png");
        animationImage = new GreenfootImage("images/Pokemon/suicune.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);

        baseHp = 100;
        baseAtk = (75 + 90)/2;
        baseDef = (115+115)/2;
        baseSpeed = 85;

        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;

        cPower = 70; 
        vPower = 65;
        cAttackRange = 1;
        vAttackRange = 2;
    }

    public void act()
    {
        // Add your action code here.

        doSomething();
    }

}

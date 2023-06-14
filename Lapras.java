import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eevee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lapras extends MoveablePokemon
{

    /**
     * Act - do whatever the Eevee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Lapras(int mapIndexX, int mapIndexY, boolean isPlayer, int lvl, int curXp, int xpNeeded){
        super(mapIndexX,mapIndexY, isPlayer, lvl, curXp, xpNeeded);
        cAttackString = "Tackle";
        vAttackString = "Aurora Beam";
        image = new GreenfootImage("images/Pokemon/lapras.png");
        animationImage = new GreenfootImage("images/Pokemon/lapras.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);

        baseHp = 130;
        baseAtk = (85 + 80)/2;
        baseDef = (80+95)/2;
        baseSpeed = 60;
        
        
        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;

        cPower = 50; 
        vPower = 45;
        cAttackRange = 1;
        vAttackRange = 2;
    }

    public void act()
    {
        // Add your action code here.

        doSomething();
    }

}

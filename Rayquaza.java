import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eevee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rayquaza extends MoveablePokemon
{

    /**
     * Act - do whatever the Eevee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Rayquaza(int mapIndexX, int mapIndexY, boolean isPlayer, int lvl, int curXp, int xpNeeded){
        super(mapIndexX,mapIndexY, isPlayer, lvl, curXp, xpNeeded);
        cAttackString = "Hyper Beam";
        vAttackString = "Outrage";
        image = new GreenfootImage("images/Pokemon/rayquaza.png");
        animationImage = new GreenfootImage("images/Pokemon/rayquaza.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);

        baseHp = 105;
        baseAtk = (150 + 150)/2;
        baseDef = (90+90)/2;
        baseSpeed = 95;
        
        
        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;

        cPower = 90; 
        vPower = 40;
        cAttackRange = 2;
        vAttackRange = 3;
    }

    public void act()
    {
        // Add your action code here.

        doSomething();
    }

}

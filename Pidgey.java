import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pidgey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pidgey extends MoveablePokemon
{
    /**
     * Act - do whatever the Eevee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Pidgey(int mapIndexX, int mapIndexY, boolean isPlayer, int lvl, int curXp, int xpNeeded){
        super(mapIndexX,mapIndexY, isPlayer, lvl, curXp, xpNeeded);
        cAttackString = "Quick Attack";
        vAttackString = "Gust";
        image = new GreenfootImage("images/Pokemon/pidgey.png");
        animationImage = new GreenfootImage("images/Pokemon/pidgey.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);

        baseHp = 40;
        baseAtk = (35 + 45)/2;
        baseDef = (45+35)/2;
        baseSpeed = 56;
        
        
        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;

        cPower = 60; 
        vPower = 30;
        cAttackRange = 40;
        vAttackRange = 35;
    }

    public void act()
    {
        // Add your action code here.

        doSomething();
    }
}

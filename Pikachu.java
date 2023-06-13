import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends MoveablePokemon
{
    /**
     * Act - do whatever the Pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Pikachu(int mapIndexX, int mapIndexY, boolean isPlayer, int lvl, int curXp, int xpNeeded){
        super(mapIndexX,mapIndexY, isPlayer, lvl, curXp, xpNeeded);
        image = new GreenfootImage("images/Pokemon/pika.png");
        animationImage = new GreenfootImage("images/Pokemon/pika.png");
        animationImage.scale(animationImage.getWidth()*imageMulti, animationImage.getHeight()*imageMulti);
        setImage(image);

        baseHp = 35;
        baseAtk = (55 + 50)/2;
        baseDef = (40+ 50)/2;
        baseSpeed = 90;
        
        cPower = 40;
        vPower = 40;
        //https://bulbapedia.bulbagarden.net/wiki/Stat
        maxHp = (int)Math.floor((baseHp*2)*lvl/100) + lvl + 10;
        hp = maxHp;
        atk = (int)Math.floor((baseAtk*2)*lvl/100) + 5;
        def = (int)Math.floor((baseDef*2)*lvl/100) + 5;
        speed = (int)Math.floor((baseSpeed*2)*lvl/100) + 5;
        
        cAttackRange = 1;
        vAttackRange = 2;
        cAttackString = "Quick Attack";
        vAttackString = "Thunder Shock";
    }
    
    public void act()
    {
        // Add your action code here.
        doSomething();

    }
}

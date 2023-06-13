import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GifOverlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GifOverlay extends Actor
{
    public GifOverlay(){
        GreenfootImage image = new GreenfootImage("GIF_Overlay.png");
        image.scale(700, 600);
        setImage(image);
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>A useful label to display game score, stats, or other texts.</p>
 * 
 * <p>This is effectively a one-line text box that should span the width of the whole world (although
 * the width can be changed if desired). It includes methods for managing and updating scores.</p> 
 * 
 * @author Jordan Cohen
 * @since November 2015 (formerly ScoreBar)
 * @version 1.1 (Nov 2021)
 */
public class SuperDisplayLabel extends Actor
{
    // Ratio of Height : FontSize
    // I.e. 1.4 means if font size is 10, height would be 14
    private static final double HEIGHT_RATIO = 2.0;
    
    private GreenfootImage image;
    private Color backColor;
    private Color foreColor;
    private Font textFont;
    private String text;
    private String lastOutput;
    private String[] labels;
    //private String[] stringValues;
    //private int[] intValues;
    private int centeredX;
    private int bottomY;
    
    public SuperDisplayLabel(Color backColor, Color foreColor, Font font, int width, String startText){
        this(width, font);
        this.backColor = backColor;
        this.foreColor = foreColor;
        update (startText, true);
    }

    public SuperDisplayLabel (int width, Font font)
    {
        image = new GreenfootImage (width, (int)(font.getSize() * HEIGHT_RATIO));
        
        bottomY = image.getHeight() - (int)((image.getHeight() - font.getSize())/1.8) - 2;
        //System.out.println(bottomY);
        // Declare colour objects for use within this class (red and white)
        this.backColor = new Color (175, 20, 23);
        this.foreColor = new Color (255, 255, 255);
        // Initialize the font - chose Courier because it's evenly spaced
        textFont = font;
        // Set the colour to red and fill the background of this rectangle
        image.setColor(backColor);
        image.fill();
        image.setFont(font);
        // Assign the image we just created to be the image representing THIS actor
        this.setImage (image);
        // Prepare the font for use within the code
        text = "";
    }

    public void setLabels (String[] labels){
        this.labels = labels;
    }

    
    /**
     * Method to update the value shown on the score board
     * 
     */
    public void update (String[] labels, int[] newValues)
    {
        this.labels = labels;
        // Arrays should be the same size, but just in case, only loop to the lower length to avoid crashing
        int loops = Math.min (labels.length, newValues.length);
        String text = "";
        for (int i = 0; i < loops; i++){
            text += labels[i] + " " + newValues[i];
            if (i < loops - 1){
                text += " "; // extra space, but not for the very last loop
            }
            
        }
        update (text, true);
    }

    /**
     * Method to update the value shown on the TextBox
     * 
     * 
     */
    public void update (int[] newValues)
    {
        update (labels, newValues);
    }

    /**
     * Takes a String and displays it centered to the screen. Note this gets called bu the other
     * update() method.
     * 
     * @param   output  A string to be output, centered on the screen.
     * @param   recenter    if true, this will recalculate and center the text. Use this
     *                      sparingly, as the recalculation is fairly demanding on the CPU.
     */
    public void update (String output, boolean recenter)
    {
        // Skip this method if the text is the same as before, to avoid extra cycles
        if (output.equals(lastOutput)){
            return;
        }
        // Refill the background with background color
        image.setColor(backColor);
        image.fill();

        // Write text over the solid background
        image.setColor(foreColor);  
        if (recenter){
            // Smart piece of code that centers text
             centeredX = getImage().getWidth()/2 - getStringWidth(textFont, output)/2;
             
             //drawCenteredText (image, output, 22);
        }
        
        // Draw the text onto the image
        image.drawString(output, centeredX, bottomY);
        
        lastOutput = output;
    }

    /**
     * <h3>Finally, draw centered text in Greenfoot!</h3>
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Use this instead of Greenfoot.drawString to center your text, or just call getStringWidth
     *    directly and draw it yourself if you prefer the control over the ease of use.</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param middleX   the x Coordinate that the text should be centered on
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int middleX, int bottomY){
        canvas.drawString (text, middleX - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

    /**
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Similar to the method above, except it always centers the text on the whole image
     *    instead of a specified x position. UNTESTED!</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int bottomY){
        canvas.drawString (text, canvas.getWidth()/2 - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

     /**
     * <h3>Mr. Cohen's Text Centering Algorithm</h3>
     * 
     * <p>Get the Width of a String, if it was printed out using the drawString command in a particular
     * Font.</p>
     * <p>There is a performance cost to this, although it is more significant on the Gallery, and 
     * especially on the Gallery when browsed on a mobile device. It is appropriate to call this in the 
     * constructor, and in most cases it is ideal NOT to call it from an act method, especially
     * every act.</p>
     * 
     * <p>In cases where values are pre-determined, it may be ideal to cache the values (save them) so 
     * you don't have to run this repeatedly on the same text. If you do this in the World constructor,
     * there is no performance cost while running.</p>
     * 
     * <h3>Performance & Compatibility:</h3>
     * <ul>
     *  <li> Locally, performance should be sufficient on any moderate computer (average call 0.1-0.2ms on my laptop)</li>
     *  <li> To be compatible with Greenfoot Gallery, removed use of getAwtImage() and replaced with getColorAt() on a GreenfootImage</li>
     *  <li> On Gallery, performance is about 10x slower than locally (4ms on Gallery via Computer). For reference, an act() should be
     *       less than 16.6ms to maintain 60 frames/acts per second. </li>
     *  <li> HUGE performance drop on Gallery via Mobile devices - not sure why, going to ignore for now. (Average update duration 34ms, more
     *       than 2 optimal acts)</li>
     * </ul>
     * 
     * @param font the GreenFoot.Font which is being used to draw text
     * @param text the actual text to be drawn
     * @return int  the width of the String text as draw in Font font, in pixels.
     * 
     * @since June 2021
     * @version December 2021 - Even more Efficiency Improvement - sub 0.06ms per update on setSpeed(100)!
     */
    public static int getStringWidth (Font font, String text){
        
        // Dividing font size by 1.2 should work for even the widest fonts, as fonts are
        // taller than wide. For example, a 24 point font is usually 24 points tall 
        // height varies by character but even a w or m should be less than 20 wide
        // 24 / 1.2 = 20
        int maxWidth = (int)(text.length() * (font.getSize()/1.20));//1000; 
        int fontSize = font.getSize();
        int marginOfError = fontSize / 6; // how many pixels can be skipped scanning vertically for pixels?
        int checkX;

        GreenfootImage temp = new GreenfootImage (maxWidth, fontSize);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);

        //int testValue = 1000;
        boolean running = true;

        checkX = maxWidth - 1;
        while(running){
            boolean found = false;
            for (int i = fontSize - 1; i >= 0 && !found; i-=marginOfError){

                if (temp.getColorAt(checkX, i).getAlpha() != 0){
                    // This lets me only look at every other pixel on the first run - check back one to the right
                    // when I find a pixel to see if I passed the first pixel or not. This should almost half the 
                    // total calls to getColorAt().
                    if (temp.getColorAt(checkX + 1, i).getAlpha() != 0){
                        checkX++;
                        if (temp.getColorAt(checkX + 1, i).getAlpha() != 0){
                            checkX++;
                        }
                    }
                    found = true;
                }
            }
            if (found){
                return checkX;
            }
            checkX-=3; // shift 3 pixels at a time in my search - above code will make sure I don't miss anything
            if (checkX <= marginOfError)
                running = false;
        }
        return 0;

    }
}

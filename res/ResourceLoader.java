
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rcorn
 */
public class ResourceLoader {
    static ResourceLoader rl = new ResourceLoader();
    
    public static BufferedImage getBufferedImage(String fileName){
     BufferedImage b = null; //Create the BufferedImage that we wish to return
     try{ //Our method could throw an Exception so we use try/catch
          b = ImageIO.read(rl.getClass().getResource("images/" + fileName )); //Make sure to update the fileName parameter to make it work with your directory setup
     }catch(Exception e){
          e.printStackTrace(System.err);
     }
     //Return the BufferedImage we have loaded
     return b;
}
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rcorn
 */

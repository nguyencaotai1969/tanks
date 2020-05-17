import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    static Image getImage(String name){
     ImageIcon icon = new ImageIcon(
         new ImageLoader()
         .getClass()
         .getResource( "/image/" +name)
            );
     return icon.getImage();
    }
}

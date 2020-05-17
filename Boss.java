import java.awt.*;
import java.util.Random;

public class Boss extends Tank {
    Random rd = new Random();
    public Boss(int x , int y){
        super(x,y,DOWN);
        images = new Image[4];
        images[LEFT]  = ImageLoader.getImage("bossyellow_left.png");
        images[RIGHT]  = ImageLoader.getImage("bossyellow_left.png");
        images[UP]  = ImageLoader.getImage("bossyellow_up.png");
        images[DOWN]  = ImageLoader.getImage("bossyellow_down.png");
    }
    void createOrient(){
        int percent = rd.nextInt(101);
        if(percent >= 100){
            orient = rd.nextInt(4);
        }
    }
}

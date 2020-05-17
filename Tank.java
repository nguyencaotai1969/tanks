
import java.awt.*;
import java.util.ArrayList;

public class Tank {
    static final int LEFT =0;
    static final int RIGHT =1;
    static final int UP = 2;
    static final int DOWN=3;

    int x;
    int y;
    int orient;
    Image[] images;
    public Tank(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }
    void draw(Graphics2D g2d){
        g2d.drawImage(images[orient],x,y,null);
    }
    long t;
    void fire(ArrayList<Bullet> bullets){
        long T = System.currentTimeMillis();
        if(T - t < 500){
            return;
        }
        t = T;
        if(this instanceof Player){
            SoundLoader.play("shoot.wav");
        }

        int xB =  x + images[orient].getWidth(null)/2;
        int yB = y + images[orient].getHeight(null)/2;
        Bullet b = new Bullet(xB,yB,orient);
        bullets.add(b);
    }

    void move(ArrayList<Map> maps){
        int speed = 1;
        int xR = x;
        int yR = y;
        switch (orient){
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        if(checkMap(maps) == false){
            x = xR;
            y = yR;
        }

        if (
                x <= 0 || x >= TankFrame.W - images[orient].getWidth(null) -5
        ) {
            x = xR;

        }
        if (
                x <= 0 || x >= TankFrame.H - images[orient].getHeight(null)-10
        ) {
            y = yR;

        }
    }
    boolean checkMap(ArrayList<Map> maps){
        for(Map m: maps){
            Rectangle rect = getRect().intersection(m.getRect());
            if(m.bit != 4 && rect.isEmpty() == false)
            {
                return false;
            }
        }
        return true;
    }
    Rectangle getRect(){
        int w = images[orient].getWidth(null);
        int h = images[orient].getHeight(null);

        if(orient == LEFT || orient == RIGHT){
            h -= 2;
        }else{
            w -= 2;
        }
        Rectangle rect = new Rectangle(x,y,w,h);
        return  rect;
    }

    boolean checkDie(ArrayList<Bullet> bullets){
        for ( Bullet b: bullets ) {
            Rectangle r = b.getRect().intersection(getRect());
            if(r.isEmpty() == false){
                bullets.remove(b);
                SoundLoader.play("explosion_tank.wav");
                return true;
            }
        }
        return  false;
    }

}
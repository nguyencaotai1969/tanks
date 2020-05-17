import  java.awt.*;
public class Bullet {
    int x;
    int y;
    int orient;
    Image image = ImageLoader.getImage("bullet.png");

    public Bullet(int x, int y, int orient) {
        this.x = x - image.getWidth(null)/2;
        this.y = y - image.getHeight(null)/2;
        this.orient = orient;
    }
    void draw(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
    boolean move(){
        int speed = 2;
        switch (orient){
            case Tank.LEFT:
                x -= speed;
                break;
            case Tank.RIGHT:
                    x += speed;
                    break;
            case  Tank.UP:
                y -= speed;
                break;
            case  Tank.DOWN:
                y += speed;
                break;
        }
        return x < 0 || x > TankFrame.W || y < 0
                || y > TankFrame.H;
    }
    Rectangle getRect(){
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        Rectangle rect = new Rectangle(x,y,w,h);
        return  rect;
    }
}

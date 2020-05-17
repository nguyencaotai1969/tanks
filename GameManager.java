import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    int[][] map = {
            {0,0,4,4,4,4,4,4,4,4,4,4,0,0,4,4,4,4,4,4,4,4,4,4,0,0},
            {0,0,4,4,4,4,4,4,4,4,4,4,0,0,4,4,4,4,4,4,4,4,4,4,0,0},
            {4,4,4,4,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,4,4,4,4},
            {4,4,4,4,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,4,4,4,4},
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
            {5,5,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,5,5},
            {5,5,0,0,0,0,0,0,0,0,1,1,5,5,1,1,0,0,0,0,0,0,0,0,5,5},
            {2,2,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,2,2},
            {2,2,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,2,2},
            {2,2,1,1,5,5,1,1,5,5,1,1,1,1,1,1,5,5,1,1,5,5,1,1,2,2},
            {2,2,1,1,5,5,1,1,5,5,1,1,1,1,1,1,5,5,1,1,5,5,1,1,2,2},
            {2,2,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,2,2},
            {2,2,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,2,2},
            {2,2,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,2,2},
            {2,2,1,1,0,0,1,1,0,0,0,1,1,1,1,0,0,0,1,1,0,0,1,1,2,2},
            {2,2,2,2,2,2,0,0,0,0,0,1,3,0,1,0,0,0,0,0,2,2,2,2,0,0},
            {2,2,2,2,2,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,2,2,2,2,0,0}
    };

    ArrayList<Map> maps;
    Player player;
    ArrayList<Boss> bosses;
    ArrayList<Bullet> bulletsPlayer;
    ArrayList<Bullet> bulletsBoss;
    void initGame(){
        bulletsBoss = new ArrayList<>();
        bulletsPlayer = new ArrayList<>();
        player = new Player(160,460);
        bosses = new ArrayList<>();
        initBoss();
        readMap();
        SoundLoader.play("enter_game.wav");

    }
    void readMap(){
        maps = new ArrayList <>();
        for (int j=0;j < map.length;j++){
            for (int i=0;i < map[j].length;i++){
                if(map[j][i] > 0){
                    int x = i*19;
                    int y = j*19;
                    Map m = new Map(x,y,map[j][i]);
                    maps.add(m);
                }
            }
        }
    }
    void initBoss(){
        Boss b = new Boss(0,0);
        bosses.add(b);
        b = new Boss(TankFrame.W / 2 - 19,0);
        bosses.add(b);
        b = new Boss(TankFrame.W - 32,0);
        bosses.add(b);
    }
    void draw(Graphics2D g2d){
        for (Bullet b : bulletsPlayer) {
            b.draw(g2d);
        }
        for (Bullet b : bulletsBoss){
            b.draw(g2d);
        }

        player.draw(g2d);
        for (Boss b : bosses) {
            b.draw(g2d);
        }
        for (Map m: maps) {
            m.draw(g2d);
        }
    }
    void playerMove (int newOrient){
        player.orient = newOrient;
        player.move(maps);
    }
    boolean AI(){
        for (int i = bosses.size() -1 ;i >=0;i--){
            bosses.get(i).createOrient();
            bosses.get(i).move(maps);
            bosses.get(i).fire(bulletsBoss);
            boolean die = bosses.get(i).checkDie(bulletsPlayer);
            if(die){
                bosses.remove(i);
                if(bosses.size() <= 2){
                    initBoss();
                }
            }
        }
         return player.checkDie(bulletsBoss) || moveBullet(bulletsBoss) || moveBullet(bulletsPlayer);
    }
    boolean moveBullet(ArrayList<Bullet> bullets){
        for (int i = bullets.size() -1 ; i >= 0 ; i--) {
            boolean out = bullets.get(i).move();
            if(out == true){
                bullets.remove(i);
                continue;
            }
            for (Map m: maps ) {
                if(m.bit == 4 || m.bit == 2){
                    continue;
                }
                Rectangle rect = m.getRect().intersection(bullets.get(i).getRect());
                if(rect.isEmpty()== false){
                    SoundLoader.play("explosion.wav");
                    bullets.remove(i);
                    if(m.bit != 5){
                        maps.remove(m);
                    }
                    if(m.bit == 3){
                        return true;
                    }
                    break;
                }
            }

        }
        return false;
    }
}

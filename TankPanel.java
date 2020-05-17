import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankPanel extends JPanel {
    GameManager manager = new GameManager();
    boolean[] flag = new boolean[256];
    public TankPanel() {
        setBackground(Color.BLACK);
        manager.initGame();
        setFocusable(true);
        addKeyListener(keyListener);

        Thread t = new Thread(run);
        t.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
       manager.draw(g2d);
    }
    Runnable run = new Runnable() {
        @Override
        public void run() {
            while (true){
                    if(flag[KeyEvent.VK_LEFT]){
                        manager.playerMove(Tank.LEFT);
                    }
               else if(flag[KeyEvent.VK_RIGHT]){
                    manager.playerMove(Tank.RIGHT);
                }
                else if(flag[KeyEvent.VK_UP]){
                    manager.playerMove(Tank.UP);
                }
                else if(flag[KeyEvent.VK_DOWN]){
                    manager.playerMove(Tank.DOWN);
                }
                if(flag[KeyEvent.VK_SPACE]){
                    manager.player.fire(manager.bulletsPlayer);
                }
               boolean die = manager.AI();
                if(die == true){
                    int result = JOptionPane.showConfirmDialog(
                            null,"Do you want to repaly ","Game Over",JOptionPane.YES_NO_OPTION
                    );
                    if(result == JOptionPane.YES_OPTION){
                        flag = new boolean[256];
                        manager.initGame();
                    }else{
                        System.exit(0);
                    }
                }
                repaint();
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    KeyListener  keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
           flag[e.getKeyCode()] =true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            flag[e.getKeyCode()]=false;
        }
    };
}

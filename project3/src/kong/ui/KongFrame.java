package kong.ui;

import kong.KongGame;
import kong.system.GameSystem;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KongFrame extends JFrame {
    public KongFrame(KongGame game) {
        setTitle("Kong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        KongRenderView rv = new KongRenderView(game.world);
        setContentPane(rv);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.sysmgr.onControlKeyDown(GameSystem.ACTION_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.sysmgr.onControlKeyDown(GameSystem.ACTION_RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        game.sysmgr.onControlKeyDown(GameSystem.ACTION_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.sysmgr.onControlKeyDown(GameSystem.ACTION_DOWN);
                        break;
                    case KeyEvent.VK_F9:
                        KongRenderView.debugCollisionBoxes = !KongRenderView.debugCollisionBoxes;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.sysmgr.onControlKeyUp(GameSystem.ACTION_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.sysmgr.onControlKeyUp(GameSystem.ACTION_RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        game.sysmgr.onControlKeyUp(GameSystem.ACTION_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.sysmgr.onControlKeyUp(GameSystem.ACTION_DOWN);
                        break;
                }
            }
        });
        Timer timer = new Timer(33, ev -> {
            game.world.onTick();
            game.sysmgr.onTick();
            rv.repaint();
        });
        timer.start();

        setSize(512, 384);
        setResizable(false);
    }


}

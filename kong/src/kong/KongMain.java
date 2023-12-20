package kong;

import java.awt.*;

public class KongMain {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1");  // disable GUI scale - it
                                                        // hurts on my 17" 1920x1080
                                                        // home laptop.

        EventQueue.invokeLater(() -> { // run in the event loop thread
            KongGame.getInstance().startGame();
        });
    }
}

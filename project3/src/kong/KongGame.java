package kong;

import kong.system.*;
import kong.ui.KongFrame;
import kong.world.World;

public class KongGame {
    private static KongGame instance;
    private KongFrame gameWindow;
    public World world;
    public GameSystemManager sysmgr;

    private KongGame() {

    }

    public static KongGame getInstance() {
        if (instance == null) {
            instance = new KongGame();
        }
        return instance;
    }

    public void startGame() {
        world = new World();
        sysmgr = new GameSystemManager(world);
        sysmgr.addSystem(new SystemRollingBarrel(world));
        sysmgr.addSystem(new SystemLadder(world));
        sysmgr.addSystem(new SystemPlayerMovement(world));
        sysmgr.addSystem(new SystemVelocity(world));
        sysmgr.addSystem(new SystemBarrelSpawn(world));
        sysmgr.addSystem(new SystemVictory(world));
        sysmgr.addSystem(new SystemKillerObject(world));
        sysmgr.addSystem(new SystemMovingBar(world));
        world.initGameWorld();
        world.resetPlayer();

        gameWindow = new KongFrame(this);
        gameWindow.setVisible(true);
    }
}

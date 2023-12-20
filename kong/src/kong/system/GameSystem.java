package kong.system;

import kong.world.World;

public abstract class GameSystem {
    public static final int ACTION_UP = 0;
    public static final int ACTION_LEFT = 1;
    public static final int ACTION_RIGHT = 2;
    public static final int ACTION_DOWN = 3;

    protected final World world;

    public GameSystem(World world) {
        this.world = world;
    }

    public void onControlKeyUp(int action) {}
    public void onControlKeyDown(int action) {}
    public void onTick() {}
}

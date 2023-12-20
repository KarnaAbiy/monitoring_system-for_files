package kong.system;

import kong.world.World;

import java.util.ArrayList;
import java.util.List;

public class GameSystemManager extends GameSystem {
    private final List<GameSystem> systems = new ArrayList<>();

    public GameSystemManager(World world) {
        super(world);
    }

    public void addSystem(GameSystem system) {
        systems.add(system);
    }

    @Override
    public void onControlKeyUp(int action) {
        systems.forEach(sys -> sys.onControlKeyUp(action));
    }

    @Override
    public void onControlKeyDown(int action) {
        systems.forEach(sys -> sys.onControlKeyDown(action));
    }

    @Override
    public void onTick() {
        systems.forEach(GameSystem::onTick);
    }
}

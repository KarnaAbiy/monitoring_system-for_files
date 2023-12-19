package kong.system;

import kong.entity.component.ComponentRollingBarrel;
import kong.world.World;

public class SystemRollingBarrel extends GameSystem {
    public SystemRollingBarrel(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentRollingBarrel.class).forEach(entity -> {
            ComponentRollingBarrel velComp = entity.findComponent(ComponentRollingBarrel.class);
            velComp.yVel += 0.5f;
        });
    }
}

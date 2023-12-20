package kong.system;

import kong.entity.EntityRollingBarrel;
import kong.entity.component.ComponentBarrelSpawner;
import kong.entity.component.ComponentLocation;
import kong.world.World;

public class SystemBarrelSpawn extends GameSystem {
    public SystemBarrelSpawn(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentBarrelSpawner.class).forEach(entity -> {
            ComponentLocation loc = entity.getLocationComponent();
            ComponentBarrelSpawner cbs = entity.findComponent(ComponentBarrelSpawner.class);
            if(--cbs.remainingTicksToSpawn == 0) {
                cbs.remainingTicksToSpawn = cbs.spawnRate;

                EntityRollingBarrel barrel = new EntityRollingBarrel();
                barrel.getLocationComponent().set(loc.x, loc.y);
                world.queueAddEntity(barrel);
            }
        });
    }
}

package kong.system;

import kong.entity.component.ComponentLocation;
import kong.entity.component.ComponentMovingBar;
import kong.entity.component.ComponentRollingBarrel;
import kong.entity.component.ComponentVelocity;
import kong.world.World;

public class SystemMovingBar extends GameSystem {
    public SystemMovingBar(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentMovingBar.class).forEach(entity -> {
            ComponentLocation lc = entity.findComponent(ComponentLocation.class);
            ComponentMovingBar comp = entity.findComponent(ComponentMovingBar.class);
            ComponentVelocity vel = entity.findComponent(ComponentVelocity.class);

            if (lc.x < comp.leftEdge) {
                vel.xVel = comp.speed;
            } else if (lc.x > comp.rightEdge) {
                vel.xVel = -comp.speed;
            } else if (Math.abs(vel.xVel) < 1e-4f) {
                vel.xVel = comp.speed;
            }
        });
    }
}

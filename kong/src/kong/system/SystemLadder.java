package kong.system;

import kong.entity.EntityBase;
import kong.entity.component.*;
import kong.world.World;

public class SystemLadder extends GameSystem {
    public SystemLadder(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentPlayerController.class).forEach(dynamicEntity -> {
            ComponentPlayerController moveComp = dynamicEntity.findComponent(ComponentPlayerController.class);
            ComponentLocation locComp = dynamicEntity.getLocationComponent();
            ComponentDynamicCollisionBox dcbComp = dynamicEntity.findComponent(ComponentDynamicCollisionBox.class);

            moveComp.onLadder = false;

            if(dcbComp != null) {
                float left = locComp.x - dcbComp.xHalfExtent;
                float right = locComp.x + dcbComp.xHalfExtent;
                float top = locComp.y - dcbComp.yHalfExtent;
                float bottom = locComp.y + dcbComp.yHalfExtent;

                assert !dynamicEntity.hasComponent(ComponentLadder.class);

                Iterable<EntityBase> iterable = world.entitiesWithComponent(ComponentLadder.class)::iterator;
                for(EntityBase entity : iterable) {
                    ComponentLocation slocComp = entity.getLocationComponent();
                    ComponentLadder scbComp = entity.findComponent(ComponentLadder.class);

                    float oleft = slocComp.x - scbComp.xHalfExtent;
                    float oright = slocComp.x + scbComp.xHalfExtent;
                    float otop = slocComp.y - scbComp.yHalfExtent;
                    float obottom = slocComp.y + scbComp.yHalfExtent;

                    if(right > oleft && left < oright && bottom > otop && top < obottom) {
                        moveComp.onLadder = true;
                        break;
                    }
                }
            }
        });
    }
}

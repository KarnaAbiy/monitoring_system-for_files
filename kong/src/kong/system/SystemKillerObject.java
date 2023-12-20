package kong.system;

import kong.entity.component.ComponentDynamicCollisionBox;
import kong.entity.component.ComponentKillerObject;
import kong.entity.component.ComponentPlayerController;
import kong.world.World;

public class SystemKillerObject extends GameSystem {
    public SystemKillerObject(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentKillerObject.class).forEach(entity -> {
            ComponentDynamicCollisionBox dcbComp = entity.findComponent(ComponentDynamicCollisionBox.class);
            world.overlappingEntities(entity, dcbComp).filter(e -> e.hasComponent(ComponentPlayerController.class))
                    .forEach(entityPlayer -> {
                        ComponentPlayerController comp = entityPlayer.findComponent(ComponentPlayerController.class);
                        if(--comp.lives <= 0) {
                            comp.score = 0;
                            comp.lives = 3;
                        }

                        world.markForReset();
                    });
        });
    }
}

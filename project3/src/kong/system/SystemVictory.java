package kong.system;

import kong.entity.component.ComponentPlayerController;
import kong.entity.component.ComponentVictoryTrigger;
import kong.world.World;

public class SystemVictory extends GameSystem {
    public SystemVictory(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentVictoryTrigger.class).forEach(entity -> {
            ComponentVictoryTrigger dcbComp = entity.findComponent(ComponentVictoryTrigger.class);

            world.overlappingEntities(entity, dcbComp).filter(e -> e.hasComponent(ComponentPlayerController.class))
                    .forEach(entityPlayer -> {
                        ComponentPlayerController comp = entityPlayer.findComponent(ComponentPlayerController.class);
                        comp.score += 100;

                        world.markForReset();
                    });
        });
    }
}

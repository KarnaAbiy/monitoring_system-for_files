package kong.entity;

import kong.entity.component.ComponentLocation;
import kong.entity.component.ComponentVictoryTrigger;

public class EntityVictoryTrigger extends EntityBase {
    public EntityVictoryTrigger() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentVictoryTrigger(64, 16));
    }
}

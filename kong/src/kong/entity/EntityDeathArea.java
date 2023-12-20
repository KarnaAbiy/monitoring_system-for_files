package kong.entity;

import kong.entity.component.ComponentDynamicCollisionBox;
import kong.entity.component.ComponentKillerObject;
import kong.entity.component.ComponentLocation;

public class EntityDeathArea extends EntityBase {
    public EntityDeathArea(int halfWidth, int halfHeight) {
        addComponent(new ComponentLocation());
        addComponent(new ComponentDynamicCollisionBox(halfWidth, halfHeight));
        addComponent(new ComponentKillerObject());
    }
}

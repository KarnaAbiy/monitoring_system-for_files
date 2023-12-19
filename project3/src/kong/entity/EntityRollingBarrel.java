package kong.entity;

import kong.entity.component.*;

public class EntityRollingBarrel extends EntityBase {
    public EntityRollingBarrel() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentStaticSprite("/barrel.png"));
        addComponent(new ComponentRollingBarrel());
        addComponent(new ComponentKillerObject());
        addComponent(new ComponentDynamicCollisionBox(4, 4));
    }
}

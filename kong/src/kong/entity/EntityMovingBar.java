package kong.entity;

import kong.entity.component.*;

public class EntityMovingBar extends EntityBase {
    public EntityMovingBar() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentVelocity());
        addComponent(new ComponentStaticSprite("/movingbar.png"));
        addComponent(new ComponentMovingBar());
        addComponent(new ComponentKillerObject());
        addComponent(new ComponentDynamicCollisionBox(16, 8));
    }
}

package kong.entity;

import kong.entity.component.ComponentLadder;
import kong.entity.component.ComponentLocation;
import kong.entity.component.ComponentStaticSprite;

public class EntityLadder extends EntityBase {
    public EntityLadder() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentLadder(8, 28));
        addComponent(new ComponentStaticSprite("/ladder.png"));
    }
}

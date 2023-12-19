package kong.entity;

import kong.entity.component.*;

public class EntityPlayer extends EntityBase {
    public EntityPlayer() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentPlayerController());
        addComponent(new ComponentDynamicCollisionBox(7, 7));
        addComponent(new ComponentStaticSprite("/char.png"));
    }
}

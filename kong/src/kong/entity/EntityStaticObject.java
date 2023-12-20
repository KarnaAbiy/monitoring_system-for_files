package kong.entity;

import kong.entity.component.ComponentLocation;
import kong.entity.component.ComponentStaticCollisionBox;
import kong.entity.component.ComponentStaticSprite;

public class EntityStaticObject extends EntityBase {
    public EntityStaticObject(String spriteName, int width, int height) {
        addComponent(new ComponentLocation());
        addComponent(new ComponentStaticSprite(spriteName));
        addComponent(new ComponentStaticCollisionBox(width / 2, height / 2));
    }
}

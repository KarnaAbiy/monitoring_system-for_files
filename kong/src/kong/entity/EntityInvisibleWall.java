package kong.entity;

import kong.entity.component.ComponentLocation;
import kong.entity.component.ComponentStaticCollisionBox;

public class EntityInvisibleWall extends EntityBase {
    public EntityInvisibleWall(int xHalfExtent, int yHalfExtent) {
        addComponent(new ComponentLocation());
        addComponent(new ComponentStaticCollisionBox(xHalfExtent, yHalfExtent));
    }
}

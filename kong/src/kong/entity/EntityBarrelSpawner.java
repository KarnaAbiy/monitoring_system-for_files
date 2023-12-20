package kong.entity;

import kong.entity.component.ComponentBarrelSpawner;
import kong.entity.component.ComponentLocation;

public class EntityBarrelSpawner extends EntityBase {
    public EntityBarrelSpawner() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentBarrelSpawner());
    }
}

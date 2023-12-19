package kong.entity.component;

public class ComponentStaticCollisionBox extends ComponentCollisionBox {
    public int tiltDirection = 0;

    public ComponentStaticCollisionBox(int xHalfExtent, int yHalfExtent) {
        super(xHalfExtent, yHalfExtent);
    }
}

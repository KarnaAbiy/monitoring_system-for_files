package kong.entity.component;

public abstract class ComponentCollisionBox implements Component {
    public int xHalfExtent, yHalfExtent;

    public ComponentCollisionBox(int xHalfExtent, int yHalfExtent) {
        this.xHalfExtent = xHalfExtent;
        this.yHalfExtent = yHalfExtent;
    }
}

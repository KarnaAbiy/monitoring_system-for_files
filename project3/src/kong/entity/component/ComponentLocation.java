package kong.entity.component;

public class ComponentLocation implements Component {
    public float x, y;

    public ComponentLocation() {
    }

    public ComponentLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

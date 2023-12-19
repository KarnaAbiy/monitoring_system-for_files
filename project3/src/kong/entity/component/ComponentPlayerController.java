package kong.entity.component;

public class ComponentPlayerController extends ComponentVelocity {
    public float playerMovementSpeed = 2.f;
    public float jumpAcceleration = 0.0f;
    public boolean onLadder = false;

    public int lives = 3;
    public int score = 0;
}

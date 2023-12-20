package kong.system;

import kong.entity.component.ComponentPlayerController;
import kong.world.World;

public class SystemPlayerMovement extends GameSystem {
    private int controlsDown = 0;

    public SystemPlayerMovement(World world) {
        super(world);
    }

    @Override
    public void onControlKeyDown(int action) {
        controlsDown |= 1 << action;
    }

    @Override
    public void onControlKeyUp(int action) {
        controlsDown &= ~(1 << action);
    }

    @Override
    public void onTick() {
        updateMovementComponents();
    }

    private void updateMovementComponents() {
        world.entitiesWithComponent(ComponentPlayerController.class).forEach(entity -> {
            ComponentPlayerController movementComp = entity.findComponent(ComponentPlayerController.class);
            updateMovementComponent(movementComp);
        });
    }

    private void updateMovementComponent(ComponentPlayerController comp) {
        comp.jumpAcceleration -= comp.jumpAcceleration * 0.49f;
        comp.xVel = 0;
        if((controlsDown & (1 << ACTION_LEFT)) != 0) {
            comp.xVel -= comp.playerMovementSpeed;
        }
        if((controlsDown & (1 << ACTION_RIGHT)) != 0) {
            comp.xVel += comp.playerMovementSpeed;
        }
        if (comp.onLadder) {
            comp.yVel = 0;
            if((controlsDown & (1 << ACTION_UP)) != 0) {
                comp.yVel -= comp.playerMovementSpeed;
            }
            if((controlsDown & (1 << ACTION_DOWN)) != 0) {
                comp.yVel += comp.playerMovementSpeed;
            }
        } else {
            comp.yVel += 0.981f + comp.jumpAcceleration;
            if ((controlsDown & (1 << ACTION_UP)) != 0) {
                if (comp.onGround) {
                    comp.jumpAcceleration = -5.1f;
                }
            }
        }
    }
}

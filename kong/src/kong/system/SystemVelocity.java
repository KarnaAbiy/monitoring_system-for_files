package kong.system;

import kong.entity.EntityBase;
import kong.entity.component.*;
import kong.world.World;

public class SystemVelocity extends GameSystem {
    public static final int COLLISION_BIT_XPOS = 1 << 0;
    public static final int COLLISION_BIT_XNEG = 1 << 1;
    public static final int COLLISION_BIT_YPOS = 1 << 2;
    public static final int COLLISION_BIT_YNEG = 1 << 3;

    public SystemVelocity(World world) {
        super(world);
    }

    @Override
    public void onTick() {
        world.entitiesWithComponent(ComponentVelocity.class).forEach(dynamicEntity -> {
            ComponentVelocity velComp = dynamicEntity.findComponent(ComponentVelocity.class);
            ComponentLocation locComp = dynamicEntity.getLocationComponent();
            ComponentDynamicCollisionBox dcbComp = dynamicEntity.findComponent(ComponentDynamicCollisionBox.class);
            assert locComp != null;

            float offsetX = velComp.xVel;
            float offsetY = velComp.yVel;

            int collisionBits = 0;

            if(dcbComp != null) {
                final float EPSILON = 1e-4f;

                float left = locComp.x - dcbComp.xHalfExtent;
                float right = locComp.x + dcbComp.xHalfExtent;
                float top = locComp.y - dcbComp.yHalfExtent;
                float bottom = locComp.y + dcbComp.yHalfExtent;

                assert !dynamicEntity.hasComponent(ComponentStaticCollisionBox.class);
                Iterable<EntityBase> iterable = world.entitiesWithComponent(ComponentStaticCollisionBox.class)::iterator;
                for(EntityBase entity : iterable) {
                    ComponentLocation slocComp = entity.getLocationComponent();
                    ComponentStaticCollisionBox scbComp = entity.findComponent(ComponentStaticCollisionBox.class);
                    float oleft = slocComp.x - scbComp.xHalfExtent;
                    float oright = slocComp.x + scbComp.xHalfExtent;
                    float otop = slocComp.y - scbComp.yHalfExtent;
                    float obottom = slocComp.y + scbComp.yHalfExtent;

                    float clipY = 0.0f;
                    if (right >= oleft && left <= oright) {
                        if (offsetY > 0 && bottom <= otop + EPSILON) {
                            clipY = bottom + offsetY - otop;
                            if (clipY <= 0) {
                                clipY = 0;
                            } else {
                                if (velComp instanceof ComponentRollingObject) {
                                    velComp.xVel += scbComp.tiltDirection * 0.05f;
                                }
                                collisionBits |= COLLISION_BIT_YPOS;
                            }
                        } else if (offsetY < 0 && top >= obottom - EPSILON) {
                            clipY = top + offsetY - obottom;
                            if (clipY >= 0) {
                                clipY = 0;
                            } else {
                                collisionBits |= COLLISION_BIT_YNEG;
                            }
                        }
                    }

                    float clipX = 0.0f;
                    if (bottom >= otop && top <= obottom) {
                        if (offsetX > 0 && right <= oleft + EPSILON) {
                            clipX = right + offsetX - oleft;
                            if (clipX <= 0) {
                                clipX = 0;
                            } else {
                                collisionBits |= COLLISION_BIT_XPOS;
                            }
                        } else if (offsetX < 0 && left >= oright - EPSILON) {
                            clipX = left + offsetX - oright;
                            if (clipX >= 0) {
                                clipX = 0;
                            } else {
                                collisionBits |= COLLISION_BIT_XNEG;
                            }
                        }
                    }

                    offsetX -= clipX;
                    offsetY -= clipY;
                }
            }

            velComp.onGround = (collisionBits & COLLISION_BIT_YPOS) != 0;
            if((collisionBits & COLLISION_BIT_XNEG) != 0) {
                velComp.xVel = 0;
            }
            if((collisionBits & COLLISION_BIT_XPOS) != 0) {
                velComp.xVel = 0;
            }
            if((collisionBits & COLLISION_BIT_YNEG) != 0) {
                velComp.yVel = 0;
            }
            if((collisionBits & COLLISION_BIT_YPOS) != 0) {
                velComp.yVel = 0;
            }

            locComp.x += offsetX;
            locComp.y += offsetY;
        });
    }
}

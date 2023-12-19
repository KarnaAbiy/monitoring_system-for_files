package kong.world;

import kong.entity.*;
import kong.entity.component.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class World {
    private final List<EntityBase> lstEntities = new ArrayList<>();
    private boolean markedForReset = false;
    private final List<EntityBase> entitiesToAdd = new ArrayList<>();
    private EntityPlayer player;

    public void addEntity(EntityBase entity) {
        lstEntities.add(entity);
    }

    public void onTick() {
        entitiesToAdd.forEach(this::addEntity);
        entitiesToAdd.clear();
        if(markedForReset) {
            resetGameWorld();
            markedForReset = false;
        }
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public Collection<EntityBase> getEntities() {
        return Collections.unmodifiableCollection(lstEntities);
    }

    public Stream<EntityBase> entitiesWithComponent(Class<? extends Component> componentClass) {
        return lstEntities.stream().filter(e -> e.hasComponent(componentClass));
    }

    public Stream<EntityBase> overlappingEntities(EntityBase entity, ComponentCollisionBox cbComp) {
        ComponentLocation locComp = entity.getLocationComponent();

        if(cbComp != null) {
            float left = locComp.x - cbComp.xHalfExtent;
            float right = locComp.x + cbComp.xHalfExtent;
            float top = locComp.y - cbComp.yHalfExtent;
            float bottom = locComp.y + cbComp.yHalfExtent;

            return entitiesWithComponent(ComponentDynamicCollisionBox.class).filter((dynamicEntity) -> {
                ComponentLocation slocComp = dynamicEntity.getLocationComponent();
                ComponentDynamicCollisionBox dcbComp = dynamicEntity.findComponent(ComponentDynamicCollisionBox.class);

                float oleft = slocComp.x - dcbComp.xHalfExtent;
                float oright = slocComp.x + dcbComp.xHalfExtent;
                float otop = slocComp.y - dcbComp.yHalfExtent;
                float obottom = slocComp.y + dcbComp.yHalfExtent;

                return right > oleft && left < oright && bottom > otop && top < obottom;
            });
        }

        return Stream.empty();
    }

    public void resetGameWorld() {
        lstEntities.removeIf(e -> e.hasComponent(ComponentRollingBarrel.class));

        resetPlayer();
    }

    public void initGameWorld() {
        {
            EntityStaticObject entity = new EntityStaticObject("/bridge_tb.png", 128, 8);
            entity.getLocationComponent().set(112, 80);
            entity.findComponent(ComponentStaticCollisionBox.class).tiltDirection = 1;
            addEntity(entity);
        }
        {
            EntityStaticObject entity = new EntityStaticObject("/bridge_bt.png", 128, 8);
            entity.findComponent(ComponentStaticCollisionBox.class).tiltDirection = -1;
            entity.getLocationComponent().set(128, 128);
            addEntity(entity);
        }
        {
            EntityStaticObject entity = new EntityStaticObject("/bridge_bt.png", 128, 8);
            entity.findComponent(ComponentStaticCollisionBox.class).tiltDirection = -1;
            entity.getLocationComponent().set(128, 32);
            addEntity(entity);
        }
        {
            EntityInvisibleWall entity = new EntityInvisibleWall(8, 999);
            entity.getLocationComponent().set(200, 96);
            addEntity(entity);
        }
        {
            EntityInvisibleWall entity = new EntityInvisibleWall(8, 999);
            entity.getLocationComponent().set(40, 96);
            addEntity(entity);
        }
        {
            EntityLadder ladderEntity = new EntityLadder();
            ladderEntity.getLocationComponent().set(184, 92);
            addEntity(ladderEntity);
        }
        {
            EntityLadder ladderEntity = new EntityLadder();
            ladderEntity.getLocationComponent().set(56, 44);
            addEntity(ladderEntity);
        }
        {
            EntityLadder ladderEntity = new EntityLadder();
            ladderEntity.getLocationComponent().set(168, -4);
            addEntity(ladderEntity);
        }
        {
            EntityBarrelSpawner barrelEntity = new EntityBarrelSpawner();
            barrelEntity.getLocationComponent().set(184, -32);
            addEntity(barrelEntity);
        }
        {
            EntityVictoryTrigger entity = new EntityVictoryTrigger();
            entity.getLocationComponent().set(168, -32);
            addEntity(entity);
        }
        {
            EntityMovingBar entity = new EntityMovingBar();
            entity.getLocationComponent().set(128, 48);
            ComponentMovingBar comp = entity.findComponent(ComponentMovingBar.class);
            comp.leftEdge = 72;
            comp.rightEdge = 168;
            addEntity(entity);
        }
        {
            EntityDeathArea entity = new EntityDeathArea(999, 128);
            entity.getLocationComponent().x = 128;
            entity.getLocationComponent().y = 512;
            addEntity(entity);
        }
        {
            player = new EntityPlayer();
            addEntity(player);
        }
    }

    public void resetPlayer() {
        player.getLocationComponent().set(72, 116);
    }

    public void markForReset() {
        markedForReset = true;
    }

    public void queueAddEntity(EntityBase entity) {
        this.entitiesToAdd.add(entity);
    }
}

package kong.ui;

import kong.entity.EntityBase;
import kong.entity.EntityPlayer;
import kong.entity.component.*;
import kong.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class KongRenderView extends JComponent {
    public static boolean debugCollisionBoxes = false;

    private final Font font = new Font("Courier New", Font.PLAIN, 8);

    private final World world;

    public KongRenderView(World world) {
        this.world = world;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.scale(2.0, 2.0);

        for (EntityBase entity : world.getEntities()) {
            ComponentLocation location = entity.getLocationComponent();
            ComponentStaticSprite sprite = (ComponentStaticSprite) entity.findComponent(ComponentStaticSprite.class);

            if (location != null && sprite != null) {
                BufferedImage image = sprite.getImage();
                g2d.drawImage(
                        image,
                        (int) (location.x - image.getWidth(this) / 2),
                        (int) (location.y - image.getHeight(this) / 2),
                        this
                );
            }

            if (debugCollisionBoxes) {
                ComponentCollisionBox collBox = entity.findComponent(ComponentCollisionBox.class);
                if (location != null && collBox != null) {
                    Color color = Color.BLUE;
                    if (collBox instanceof ComponentStaticCollisionBox) {
                        color = Color.GREEN;
                    } else if (collBox instanceof ComponentDynamicCollisionBox) {
                        color = Color.YELLOW;
                    }
                    g2d.setColor(color);
                    g2d.drawRect((int) (location.x - collBox.xHalfExtent), (int) (location.y - collBox.yHalfExtent),
                            collBox.xHalfExtent * 2, collBox.yHalfExtent * 2);
                }
            }
        }

        EntityPlayer player = world.getPlayer();
        if(player != null) {
            ComponentPlayerController pc = player.findComponent(ComponentPlayerController.class);
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);
            g2d.drawString("Lives: " + pc.lives, 8, 8);
            g2d.drawString("Score: " + pc.score, 8, g2d.getFontMetrics().getHeight() + 8);
        }
        g2d.dispose();
    }
}

package kong.entity.component;

import kong.render.ImageCache;

import java.awt.image.BufferedImage;

public class ComponentStaticSprite implements Component {
    private BufferedImage cachedImage;
    private boolean imageLoaded = false;

    private final String name;

    public ComponentStaticSprite(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        if(!imageLoaded) {
            cachedImage = ImageCache.INSTANCE.getImage(this.name);
            imageLoaded = true;
        }
        return cachedImage;
    }
}

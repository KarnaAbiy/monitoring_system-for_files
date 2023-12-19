package kong.render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    public static final ImageCache INSTANCE = new ImageCache();

    private final Map<String, BufferedImage> cachedImages = new HashMap<>();

    public BufferedImage getImage(String name) {
        if(cachedImages.containsKey(name)) {
            return cachedImages.get(name);
        }
        BufferedImage image = loadImage(name);
        cachedImages.put(name, image);
        return image;
    }

    public BufferedImage loadImage(String name) {
        try {
            URL resource = getClass().getResource(name);
            if(resource == null) {
                return null;
            }
            return ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package hutaroAlblo.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Background extends Sprite
{
    public static final int BACKGROUND_WIDTH = 640;
    public static final int BACKGROUND_HEIGHT = 3200;

    protected Image imgBackground;

    public Background(String path) {
        super(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        try
        {
            imgBackground = new Image(Files.newInputStream(
                    Paths.get(path)));
        } catch (Exception e) {
        }
        spriteX = 0;
        spriteY = (int) imgBackground.getHeight() -
                BACKGROUND_HEIGHT;
    }

    public void scrollBackground(Double scrollSpeed)
    {
        y += scrollSpeed;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(imgBackground, spriteX, spriteY,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT,
                x, y,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }
}

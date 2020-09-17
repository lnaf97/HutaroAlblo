package hutaroAlblo.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Sprite
{
    public static final String SPRITE_SHEET_PATH =
            "assets/img/hutaroAlbloSheet.png";

    protected static Image spriteSheet;
    protected int width, height;
    protected double x, y;
    protected int spriteX, spriteY;

    public Sprite(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public static void initSpriteSheet()
    {
        try
        {
            spriteSheet = new Image(Files.newInputStream(
                    Paths.get(SPRITE_SHEET_PATH)));
        } catch (Exception e) { }
    }

    public void moveTo(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public static Image getSpriteSheet()
    {
        return spriteSheet;
    }

    public void draw(GraphicsContext gc)
    {
        gc.drawImage(spriteSheet, spriteX, spriteY,
                width, height, x, y, width, height);
    }
}

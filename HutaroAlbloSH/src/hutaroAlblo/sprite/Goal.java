package hutaroAlblo.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Goal extends AnimatedSprite
{
    public static final int GOAL_WIDTH = 104;
    public static final int GOAL_HEIGHT = 128;

    public Goal()
    {
        super(GOAL_WIDTH, GOAL_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 4, 4, 117, 117, 230, 230, 343, 343,
                457, 457, 571, 571, 685, 685, 798, 798, 911, 911, 1024, 1024 };
        spriteYCoordinates[RIGHT] = new int[] { 265, 265, 265, 265, 265, 265, 265, 265, 265, 265,
                265, 265, 265, 265, 265, 265, 265, 265, 265, 265 };

        updateSpriteCoordinates();
    }

    public void drawStatic(GraphicsContext gc)
    {
        gc.drawImage(spriteSheet, 4, 265,
                GOAL_WIDTH, GOAL_HEIGHT, x, y,
                GOAL_WIDTH, GOAL_HEIGHT);
    }

    public void move()
    {
        animate(RIGHT);
    }
}

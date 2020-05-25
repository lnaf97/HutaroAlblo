package hutaroAlblo.level;

import hutaroAlblo.sprite.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public abstract class Level
{
    protected Platform[] platforms;
    protected String levelSong;
    protected Goal goal;
    protected Background background;

    public Level()
    {
        goal = new Goal();
    }

    public Platform[] getPlatforms()
    {
        return platforms;
    }

    public String getLevelSong()
    {
        return levelSong;
    }

    public Goal getGoal()
    {
        return goal;
    }

    public Background getBackground()
    {
        return background;
    }

    public void move(MainCharacter character, boolean levelFinished)
    {
        if ( levelFinished )
            goal.move();
    }

    public void draw(GraphicsContext gc)
    {
        goal.draw(gc);
    }

    public void attachPlatformToSprite(AnimatedSprite sprite)
    {
        Arrays.sort(platforms, new Comparator<Platform>() {
            @Override
            public int compare(Platform p1, Platform p2)
            {
                return Double.compare(
                        p1.distanceTo(sprite),
                        p2.distanceTo(sprite));
            }
        });

        if (!((MainCharacter) sprite).isJumping() && platforms[0].distanceTo(sprite) <= Platform.LANDING_THRESHOLD)
        {
            sprite.setCurrentPlatform(platforms[0]);
            sprite.moveTo(sprite.getX(), platforms[0].getYforSprite(sprite) - sprite.getHeight());
        }
        else
            sprite.setCurrentPlatform(null);
    }

    public abstract void restore(MainCharacter character);
    public abstract boolean checkCollisionsWith(AnimatedSprite sprite);
}
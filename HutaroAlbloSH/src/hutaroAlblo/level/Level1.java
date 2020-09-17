package hutaroAlblo.level;

import hutaroAlblo.sprite.AnimatedSprite;
import hutaroAlblo.sprite.Background;
import hutaroAlblo.sprite.MainCharacter;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level1 extends Level
{
    public static String LEVEL1_SONG = "assets/sound/soundtrack.mp3";

    public Level1(MainCharacter character)
    {
        super();
        this.levelSong = LEVEL1_SONG;
        background = new Background("assets/img/background.png");
        background.moveTo(0,-2600);
        platforms = new Platform[] {
                new Platform(0, 554, 640, 554),
                new Platform(450, 471, 640, 471),
                new Platform(29, 360, 314, 360),
                new Platform( 0, 216, 123, 216),
                new Platform(240,78, 485, 78),
                new Platform(482, -35, 640, -35),
                new Platform(0, -114, 238, -114),
                new Platform(548, -203, 640, -203),
                new Platform(0, -225, 123, -225),
                new Platform(225, -323, 469, -323),
                new Platform(413, -457, 640, -457),
                new Platform(293, -591, 539, -591),
                new Platform(201,-721, 446, -721),
                new Platform(51, -845, 296, -845),
                new Platform(310, -982, 556, -982),
                new Platform(61, -1141, 306, -1141),
                new Platform(0, -1291, 150, -1291),
                new Platform(140, -1446, 386, -1446),
                new Platform(436, -1553, 509, -1553),
                new Platform(555, -1624, 610, -1624),
                new Platform(375, -1701, 422, -1701),
                new Platform(133, -1743, 178, -1743),
                new Platform(0, -1835, 39, -1835),
                new Platform(236, -1923, 481, -1923),
                new Platform(565, -2035, 613, -2035),
                new Platform(439, -2197, 525, -2197),
                new Platform(0, -2293, 230, -2293),
                new Platform(422, -2433, 640, -2433)
        };

        restore(character);
    }

    @Override
    public void move(MainCharacter character, boolean levelFinished)
    {
        if ( background.getY() < 0 ) {
            double scrollSpeed = character.getY() <= 400 ? 1.2 :
                    character.getY() <= 500 ? 0.6 : 0.3;
            for (int i = 0; i < platforms.length; i++) {
                platforms[i].movePlatform(scrollSpeed);
            }
            background.scrollBackground(scrollSpeed);
            goal.moveTo(520, goal.getY() + scrollSpeed);
        }
        super.move(character, levelFinished);
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        background.draw(gc);
        super.draw(gc);
    }

    @Override
    public void restore(MainCharacter character)
    {
        character.moveTo(580, 395);
        character.resetJumpForce();
        character.animate(AnimatedSprite.LEFT);
        goal.moveTo(520, -2561);
        background.moveTo(0,-2600);
        for ( int i = 0; i < platforms.length; i++ )
        {
            platforms[i].restorePlatform();
        }
    }

    @Override
    public boolean checkCollisionsWith(AnimatedSprite sprite)
    {
        boolean result = false;
        return result;
    }
}
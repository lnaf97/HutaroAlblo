package hutaroAlblo.scene;

import hutaroAlblo.HutaroAlbloMain;
import hutaroAlblo.level.Level;
import hutaroAlblo.level.Level1;
import hutaroAlblo.sprite.AnimatedSprite;
import hutaroAlblo.sprite.MainCharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;

public class GameScene extends HutaroAlbloScene
{
    public static final String JUMP_SOUND = "assets/sound/jump.mp3";
    public static final String DIE_SOUND = "assets/sound/die.mp3";

    private Level1 level;
    private MainCharacter character;

    private MediaPlayer mediaPlayerEffects;
    private Media soundEffects;

    public GameScene()
    {
        super();
        character = new MainCharacter();
        level = new Level1(character);
    }

    public void playEffect(String path)
    {
        soundEffects = new Media(new File(path).toURI().toString());
        mediaPlayerEffects = new MediaPlayer(soundEffects);
        mediaPlayerEffects.play();
    }

    @Override
    public void draw()
    {
        activeKeys.clear();
        playSound(level.getLevelSong());
        level.restore(character);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                if(activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    mediaPlayer.stop();
                    HutaroAlbloMain.setScene(
                            HutaroAlbloMain.WELCOME_SCENE);
                } else if (activeKeys.contains(KeyCode.SPACE)) {
                    if ( !character.isJumping() ) {
                        character.jump();
                        playEffect(JUMP_SOUND);
                    }
                    else if ( character.MAX_JUMP_FORCE > character.getInitialJumpForce() )
                        character.increaseJump();
                }

                if (activeKeys.contains(KeyCode.LEFT))
                {
                    character.move(AnimatedSprite.LEFT);
                }
                else if (activeKeys.contains(KeyCode.RIGHT))
                {
                    character.move(AnimatedSprite.RIGHT);
                }

                if (character.collidesWith(level.getGoal()))
                {
                    this.stop();
                    mediaPlayer.stop();
                    HutaroAlbloMain.setScene(HutaroAlbloMain.LEVEL_COMPLETE_SCENE);
                }
                else if (character.getY() > 600)
                {
                    playEffect(DIE_SOUND);
                    this.stop();
                    mediaPlayer.stop();
                    HutaroAlbloMain.setScene(HutaroAlbloMain.GAME_OVER_SCENE);
                } else {
                    level.attachPlatformToSprite(character);
                    character.checkGravity();
                    level.move(character);
                }

                level.draw(gc);
                character.draw(gc);
            }
        }.start();
    }
}

package hutaroAlblo.scene;

import hutaroAlblo.HutaroAlbloMain;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.nio.file.Files;
import java.nio.file.Paths;

public class WelcomeScene extends HutaroAlbloScene
{
    public static final String WELCOME_SCREEN1_PATH =
            "assets/img/WelcomeScene1.png";

    public static final String WELCOME_SCREEN2_PATH =
            "assets/img/WelcomeScene2.png";

    private Image welcomeImage1;
    private Image welcomeImage2;

    public WelcomeScene()
    {
        super();
        try
        {
            welcomeImage1 = new Image(Files.newInputStream(Paths.get(WELCOME_SCREEN1_PATH)));
            welcomeImage2 = new Image(Files.newInputStream(Paths.get(WELCOME_SCREEN2_PATH)));
        } catch (Exception e){}
    }

    @Override
    public void draw()
    {
        activeKeys.clear();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                try
                {
                    TitleAnimation();
                } catch (InterruptedException e) { }
                if(activeKeys.contains(KeyCode.SPACE))
                {
                    this.stop();
                    HutaroAlbloMain.setScene(HutaroAlbloMain.GAME_SCENE);
                } else if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    HutaroAlbloMain.exit();
                }
            }
        }.start();
    }

    public void TitleAnimation() throws InterruptedException
    {
        gc.drawImage(welcomeImage1, 0, 0);
        gc.drawImage(welcomeImage2, 0, 0);
    }
}

package hutaroAlblo.scene;

import hutaroAlblo.HutaroAlbloMain;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverScene extends HutaroAlbloScene
{
    @Override
    public void draw()
    {
        activeKeys.clear();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        Font myFont = Font.font("Courier New", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("GAME OVER", 250, 250);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (activeKeys.contains(KeyCode.ENTER))
                {
                    this.stop();
                    HutaroAlbloMain.setScene(HutaroAlbloMain.WELCOME_SCENE);
                }
            }
        }.start();
    }
}

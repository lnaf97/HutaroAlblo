package hutaroAlblo.scene;

import hutaroAlblo.HutaroAlbloMain;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameOverScene extends HutaroAlbloScene
{
    private String pathFile = "assets/scores.txt";
    Scanner sc = new Scanner(System.in);
    int score = 1020;

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

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (activeKeys.contains(KeyCode.ENTER)) {
                    this.stop();
                    HutaroAlbloMain.setScene(HutaroAlbloMain.WELCOME_SCENE);
                }
            }
        }.start();
    }

    public void SetNewScore()
    {
        try
        {
            File file = new File(pathFile);
            String nick = "";
            System.out.print("Enter your nick: ");
            nick = sc.nextLine();

            if (file.exists())
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile));
                writer.write(nick + " - " + score);
                writer.close();
            }

        }catch (Exception e){}
    }

    public void ShowScores()
    {
        try
        {
            FileReader fileReader = new FileReader(pathFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            int count = 1;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(count + " - " + line);
                count++;
            }

        }catch (Exception e){}
    }
}

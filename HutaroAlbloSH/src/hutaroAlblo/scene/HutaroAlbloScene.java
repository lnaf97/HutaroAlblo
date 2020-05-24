package hutaroAlblo.scene;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public abstract class HutaroAlbloScene extends Scene
{
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 600;

    private StackPane root = new StackPane();

    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;

    protected GraphicsContext gc;

    protected MediaPlayer mediaPlayer;
    protected Media sound;

    public HutaroAlbloScene()
    {
        super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);

        root = new StackPane();
        this.setRoot(root);

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();
        this.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        this.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
            releasedKeys.add(e.getCode());
        });
    }

    public void playSound(String path)
    {
        sound = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public abstract void draw();
}

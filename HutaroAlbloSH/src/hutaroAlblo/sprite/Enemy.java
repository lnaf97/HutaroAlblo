package hutaroAlblo.sprite;

public class Enemy extends AnimatedSprite
{
    public static final int ENEMY_WIDTH = 108;
    public static final int ENEMY_HEIGHT = 80;

    public Enemy()
    {
        super(ENEMY_WIDTH, ENEMY_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 30 };
        spriteYCoordinates[RIGHT] = new int[] { 64 };

        updateSpriteCoordinates();
    }

    public void move()
    {
        animate(RIGHT);
    }
}

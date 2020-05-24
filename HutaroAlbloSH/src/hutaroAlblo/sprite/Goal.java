package hutaroAlblo.sprite;

public class Goal extends AnimatedSprite
{
    public static final int GOAL_WIDTH = 40;
    public static final int GOAL_HEIGHT = 56;

    public Goal()
    {
        super(GOAL_WIDTH, GOAL_HEIGHT);

        spriteXCoordinates[LEFT] = new int[] { 30 };
        spriteYCoordinates[LEFT] = new int[] { 64 };

        //updateSpriteCoordinates();
    }

    public void move()
    {
        animate(RIGHT);
    }
}

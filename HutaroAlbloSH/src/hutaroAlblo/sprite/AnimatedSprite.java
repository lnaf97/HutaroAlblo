package hutaroAlblo.sprite;

import hutaroAlblo.level.Platform;
import hutaroAlblo.sprite.Sprite;

public abstract class AnimatedSprite extends Sprite
{
    public static final double GRAVITY = 5;

    public static final int TOTAL_MOVEMENTS = 4;
    public static final byte SPRITE_CHANGE = 10;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int JUMP_RIGHT = 2;
    public static final int JUMP_LEFT = 3;

    protected int currentDirection;
    protected byte currentSprite;
    protected byte currentSpriteChange;
    protected Platform currentPlatform;

    public int[][] spriteXCoordinates = new int[TOTAL_MOVEMENTS][];
    public int[][] spriteYCoordinates = new int[TOTAL_MOVEMENTS][];

    public AnimatedSprite(int width, int height)
    {
        super(width, height);
        currentDirection = RIGHT;
        currentSprite = 0;
        currentSpriteChange = 0;
        currentPlatform = null;
    }

    public void animate(int movement)
    {
        if (movement != currentDirection)
        {
            currentDirection = movement;
            currentSprite = 0;
            currentSpriteChange = 0;
        } else {
            currentSpriteChange++;
            if (currentSpriteChange >= SPRITE_CHANGE)
            {
                currentSpriteChange = 0;
                currentSprite = (byte)((currentSprite + 1) %
                        spriteXCoordinates[currentDirection].length);
            }
        }
        updateSpriteCoordinates();
    }

    protected void updateSpriteCoordinates()
    {
        if ( currentDirection < spriteXCoordinates.length
                && currentSprite < spriteXCoordinates[currentDirection].length
            && currentDirection < spriteYCoordinates.length
                && currentSprite < spriteYCoordinates[currentDirection].length) {
            spriteX = spriteXCoordinates[currentDirection][currentSprite];
            spriteY = spriteYCoordinates[currentDirection][currentSprite];
        }
    }

    public Platform getCurrentPlatform()
    {
        return currentPlatform;
    }

    public void setCurrentPlatform(Platform currentPlatform)
    {
        this.currentPlatform = currentPlatform;
    }

    public void checkGravity()
    {
    }

    public boolean collidesWith(AnimatedSprite sp)
    {
        return (x + width / 2 > sp.x && x < sp.x + sp.width / 2 &&
                y + height > sp.y && y < sp.y + sp.height );
    }
}
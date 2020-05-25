package hutaroAlblo.sprite;

import hutaroAlblo.scene.HutaroAlbloScene;
import javafx.scene.canvas.GraphicsContext;

public class MainCharacter extends AnimatedSprite
{
    public static final int MAIN_CHARACTER_WIDTH = 50;
    public static final int MAIN_CHARACTER_HEIGHT = 64;

    public static final int MAX_JUMP_FORCE = 360;
    public static final double SPEED = 2.5;

    private int jumpDirection;
    private double jumpSpeed;
    private int currentJumpForce;
    private int initialJumpForce;
    private int jumpState;

    public MainCharacter()
    {
        super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 0, 50, 100, 150 };
        spriteYCoordinates[RIGHT] = new int[] { 64, 64, 64, 64 };

        spriteXCoordinates[LEFT] = new int[] { 0, 50, 100, 150 };
        spriteYCoordinates[LEFT] = new int[] { 132, 132, 132, 132 };

        spriteXCoordinates[JUMP_LEFT] = new int[] { 50 };
        spriteYCoordinates[JUMP_LEFT] = new int[] { 132 };

        spriteXCoordinates[JUMP_RIGHT] = new int[] { 100 };
        spriteYCoordinates[JUMP_RIGHT] = new int[] { 64 };

        updateSpriteCoordinates();
    }

    @Override
    public void checkGravity()
    {
        if (currentJumpForce > 0)
        {
            currentPlatform = null;
            if (currentJumpForce > initialJumpForce / 2) {
                jumpState = 1;
                if ( currentJumpForce > (initialJumpForce / 4) )
                    y -= GRAVITY;
                else
                    y -= GRAVITY * 0.6;
            }
            else {
                jumpState = 0;
                y += GRAVITY * 1.2;
                resetJumpForce();
            }

            currentJumpForce -= GRAVITY;
        }
        else if (currentPlatform == null)
        {
            y += GRAVITY;
        }
    }

    public void move(int movement)
    {
        double newX = x, oldX = x, newY = y, oldY = y;
        jumpSpeed = movement != jumpDirection ? SPEED * 0.75 : SPEED * 1.5;

        double moveSpeed = currentPlatform == null ? jumpSpeed:SPEED;

        if (movement == LEFT)
        {
            newX -= moveSpeed;
        }
        else if (movement == RIGHT)
        {
            newX += moveSpeed;
        }

        moveTo(newX, newY);
        if (!checkBoundaries())
            moveTo(oldX, oldY);

        if ( isJumping() )
            animate(2 + jumpDirection);
        else
            animate(movement);
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        super.draw(gc);
    }

    public boolean checkBoundaries()
    {
        return x >= 0 && x + width <= HutaroAlbloScene.GAME_WIDTH &&
                y + height <= HutaroAlbloScene.GAME_HEIGHT;
    }

    public void jump()
    {
        if (currentPlatform != null)
        {
            currentPlatform = null;
            jumpDirection = currentDirection;
            initialJumpForce = MAX_JUMP_FORCE / 3;
            currentJumpForce = initialJumpForce;
        }
    }

    public void increaseJump()
    {
        initialJumpForce += 10;
        currentJumpForce += 10;
    }

    public boolean isJumping()
    {
        return jumpState == 1;
    }

    public int getJumpForce() {
        return currentJumpForce;
    }

    public int getInitialJumpForce() {
        return initialJumpForce;
    }

    public void resetJumpForce()
    {
        initialJumpForce = 0;
        currentJumpForce = 0;
    }
}
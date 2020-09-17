package hutaroAlblo.level;

import hutaroAlblo.sprite.AnimatedSprite;

public class Platform
{
    public static final int LANDING_THRESHOLD = 7;

    private double x1, y1, x2, y2, initialY1, initialY2;

    public Platform(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.initialY1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.initialY2 = y2;
    }

    public double getX1()
    {
        return x1;
    }

    public double getY1()
    {
        return y1;
    }

    public double getX2()
    {
        return x2;
    }

    public double getY2()
    {
        return y2;
    }

    public void restorePlatform()
    {
        y1 = initialY1;
        y2 = initialY2;
    }

    public void movePlatform(Double scrollSpeed)
    {
        y1 += scrollSpeed;
        y2 += scrollSpeed;
    }

    public double getYforSprite(AnimatedSprite sprite)
    {
        double result = -1;

        if (Math.min(x1, x2) <= sprite.getX() + sprite.getWidth() - 10 &&
                sprite.getX() <= Math.max(x1, x2))
            result = y1;

        if (result <= sprite.getY())
            result = -1;

        return result;
    }

    public double distanceTo(AnimatedSprite sprite)
    {
        double expectedYCoordinate = getYforSprite(sprite) - sprite.getHeight();
        double result = Integer.MAX_VALUE;

        if (expectedYCoordinate > 0)
            result = Math.abs(expectedYCoordinate - sprite.getY());

        return result;
    }
}

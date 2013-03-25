
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy
{

    private String enemy = "enemy.PNG";
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    public BufferedImage image;

    public Enemy(int x, int y)
    {
        BufferedImage ii = ResourceLoader.getBufferedImage(enemy);
        image = ii;
        width = image.getWidth();
        height = image.getHeight();
        visible = true;
        this.x = x;
        this.y = y;
    }

    public void move()
    {
        if (x < 0) {
            x = 700;
        }
        x -= 1;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(Boolean visible)
    {
        this.visible = visible;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }
}
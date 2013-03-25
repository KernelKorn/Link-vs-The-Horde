
import java.awt.Rectangle;

public class Arrow
{

    private int x, y;
    private Rectangle image;
    boolean visible;
    public int width, height;
    private final int BOARD_WIDTH = 600;
    private final int ARROW_SPEED = 2;

    public Arrow(int x, int y)
    {

        Rectangle ii = new Rectangle(y, x, 30, 5);

        image = ii;
        visible = true;
        width = (int) image.getWidth();
        height = (int) image.getHeight();
        this.x = x;
        this.y = y;
    }

    public Rectangle getImage()
    {
        return image;
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

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }

    public void move()
    {
        x += ARROW_SPEED;
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
}

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Link {

    private String link = "8.PNG";
    private int dx;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private BufferedImage image;
    private ArrayList arrows;

    public Link() {
        BufferedImage ii = ResourceLoader.getBufferedImage(link);
        image = ii;
        width = image.getWidth();
        height = image.getHeight();
        arrows = new ArrayList();
        visible = true;
        x = 40;
        y = 60;
    }

    public void move() {
        x += dx;
        y += dy;
        bounderies();

    }

    private void bounderies() {
        if (x > 600 - image.getWidth()) {
            x = 600 - image.getWidth();
        }
        if (x < 0) {
            x = 0;
        }
        if (y > 600 - image.getHeight()) {
            y = 600 - image.getHeight();
        }
        if (y < 0) {
            y = 0;
        }
    } // end method bounderies

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ArrayList getArrows() {
        return arrows;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    @SuppressWarnings("unchecked")
    public void fire() {
    	if(arrows.size() < 1)
        arrows.add(new Arrow(x + 50, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
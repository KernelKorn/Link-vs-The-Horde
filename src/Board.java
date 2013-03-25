
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    Random r = new Random();
    private Timer timer;
    private Link link;
    BufferedImage i = ResourceLoader.getBufferedImage("enemy.PNG");
    private ArrayList enemies;
    private boolean ingame;
    private boolean gamewin = false;
    private boolean newWave = true;
    private static int waveNum = 1;
    private int incEnemies = 1;
    private int B_WIDTH;
    private int B_HEIGHT;
    private int heightPos = (600 - i.getHeight());
    private int[][] pos = {
        {780, r.nextInt(heightPos)},{700, r.nextInt(heightPos)}, 
        {880, r.nextInt(heightPos)},{780, r.nextInt(heightPos)}, 
        {580, r.nextInt(heightPos)}, {780, r.nextInt(heightPos)},
        {790, r.nextInt(heightPos)}, {760, r.nextInt(heightPos)}, 
        {790, r.nextInt(heightPos)}, {980, r.nextInt(heightPos)}, 
        {660, r.nextInt(heightPos)}, {610, r.nextInt(heightPos)},
        {930, r.nextInt(heightPos)}, {690, r.nextInt(heightPos)}, 
        {530, r.nextInt(heightPos)}, {940, r.nextInt(heightPos)}, 
        {990, r.nextInt(heightPos)}, {920, r.nextInt(heightPos)},
        {900, r.nextInt(heightPos)}, {660, r.nextInt(heightPos)}, 
        {640, r.nextInt(heightPos)}, {810, r.nextInt(heightPos)}, 
        {860, r.nextInt(heightPos)}, {740, r.nextInt(heightPos)},
        {820, r.nextInt(heightPos)}, {690, r.nextInt(heightPos)}, 
        {700, r.nextInt(heightPos)}
    }; // end pos array

    public Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(new Color(55, 106, 34));
        setDoubleBuffered(true);
        ingame = true;

        setSize(400, 300);

        link = new Link();

        initEnemies();

        timer = new Timer(5, this);
        timer.start();
    } // end Board

    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }

    public void initEnemies() {
        enemies = new ArrayList();
        
        while ( newWave ){
	        for (int i = 1; i <= incEnemies; i++) {
	            enemies.add(new Enemy(pos[i][0], pos[i][1]));
	            newWave = false;
	        }
        }
    } // end initEnemy

    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {

            Graphics2D g2d = (Graphics2D) g;

            if (link.isVisible()) {
                g2d.drawImage(link.getImage(), link.getX(), link.getY(),
                        this);
            } // end inner if

            ArrayList as = link.getArrows();

            for (int i = 0; i < as.size(); i++) {
                Arrow a = (Arrow) as.get(i);
                g.setColor(new Color(96, 64, 32));
                g.fillRect(a.getX(), a.getY(), a.width, a.height);

                g.setColor(Color.GRAY);
                int[] xPoints = {a.getX() + a.width,
                    a.getX() + a.width + 10, a.getX() + a.width};
                int[] yPoints = {a.getY() + a.height + 2,
                    a.getY() + (a.height / 2), a.getY() - 2};
                g.fillPolygon(xPoints, yPoints, 3);
            } // end for
            
            for (int i = 0; i < enemies.size(); i++) {
                Enemy en = (Enemy) enemies.get(i);
                if (en.isVisible()) {
                    g2d.drawImage(en.getImage(), en.getX(), en.getY(), this);
                } // end inner if
            } // end for

            g2d.setColor(Color.WHITE);
            g2d.drawString("Enemies left: " + enemies.size(), 5, 15);
            
            if ( waveNum < 13 ) {
	            g2d.setColor(Color.WHITE);
	            g2d.drawString("Wave: " + waveNum, 5, 30);
            } else{
	            g2d.setColor(Color.WHITE);
	            g2d.drawString("Final Wave!", 5, 30);
            }


        } else if (gamewin) {
            String msg = "You Win!";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (600 - metr.stringWidth(msg)) / 2,
                    600 / 2);
        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (600 - metr.stringWidth(msg)) / 2,
                    600 / 2);
        } // end outter if

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    } // end paint

    public void actionPerformed(ActionEvent e) {

        if (enemies.isEmpty()) {         	
        	newWave = true;
        	
        	waveNum++;
        	incEnemies += 2;        	
        	
        	if ( waveNum  == 14 ){
        		ingame = false;
        		gamewin = true;
        	} // end if
        	else initEnemies();
        } // end if
        
        ArrayList as = link.getArrows();

        for (int i = 0; i < as.size(); i++) {
            Arrow a = (Arrow) as.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                as.remove(i);
            } 
        } // end for

        for (int i = 0; i < enemies.size(); i++) {
            Enemy en = (Enemy) enemies.get(i);
            if (en.isVisible()) {
                en.move();
            } else {
                enemies.remove(i);
            }
        } // end for

        link.move();
        checkCollisions();
        repaint();
    } // end actionPerformed

    public void checkCollisions() {

        Rectangle r3 = link.getBounds();

        for (int j = 0; j < enemies.size(); j++) {
            Enemy en = (Enemy) enemies.get(j);
            Rectangle r2 = en.getBounds();

            if (r3.intersects(r2)) {
                link.setVisible(false);
                en.setVisible(false);
                ingame = false;
                gamewin = false;
            } // end if
        } // end for loop

        ArrayList as = link.getArrows();

        for (int i = 0; i < as.size(); i++) {
            Arrow aa = (Arrow) as.get(i);

            Rectangle r1 = aa.getBounds();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy a = (Enemy) enemies.get(j);
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                    aa.setVisible(false);
                    a.setVisible(false);
                } //end of if
            } // end inner for loop
        } // end outer for loop
    } // end checkCollisions

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            link.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            link.keyPressed(e);
        }
    } // end class TAdapter
} // end class board
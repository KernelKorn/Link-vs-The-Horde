import javax.swing.JFrame;

public class Play extends JFrame {

    public Play() {
        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("Link vs. The Horde");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Play();
    }
}
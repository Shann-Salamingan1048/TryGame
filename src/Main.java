import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("hi");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Try Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // the size will be preferred size of the gamePanel

        window.setLocationRelativeTo(null); // center at the screen
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}

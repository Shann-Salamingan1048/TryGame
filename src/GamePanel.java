
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{ // force to put the void function named "run"
    // Screen settings
    final int original_TileSize = 16; // 16 x 16 size
    final int scale = 3;

    final int tileSize = original_TileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    // set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    final int FPS = 60;
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(this.screenWidth,this.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // with this, this game panel can be focused on receive key input
    }
    public void run()
    {
        double drawInterval = 1_000_000_000/FPS; // 1 Billion nano secs divided by FPS 60
        double delta = 0;
        long lastTime =  System.nanoTime();
        long currentTime = 0;
        while(gameThread != null) // as long as gameThread exist, it will loop
        {
           currentTime = System.nanoTime();
           delta += (currentTime - lastTime) / drawInterval;
           lastTime = currentTime;
           System.out.println(delta);
           if(delta > 1)
           {

               update();
               // draw the screen
               repaint();
               delta--;
           }
        }

    }
    public void startGameThread()
    {
        gameThread = new Thread(this); // instantiate a thread
        gameThread.start();
    }

    public void update()
    {
        if(keyH.upPressed)
        {
            this.playerY -= this.playerSpeed;
        }
        else if(keyH.downPressed)
        {
            this.playerY += this.playerSpeed;
        }
        else if(keyH.leftPressed)
        {
            this.playerX -= this.playerSpeed;
        }
        else if(keyH.rightPressed)
        {
            this.playerX += this.playerSpeed;
        }
    }
    public void paintComponent(Graphics g) // Graphics a class that has many functions to draw objects on the screen
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(this.playerX,this.playerY,this.tileSize,this.tileSize); // x position, y position, width size, height size
        //g2.dispose();
        int xPoints[] = {150,100,150,200,400}; // x positions vertices
        int yPoints[] = {150,150,50,50,400}; // y positions vertices
        g2.setColor(Color.BLUE);
        g2.drawPolygon(xPoints,yPoints,5);
        g2.dispose();
    }

}

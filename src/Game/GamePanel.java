package Game;

import Game.States.GameStateManager;
import Game.States.MenuState;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.security.KeyRep;


public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;
    public static int oldFrameCount;

    private MouseHandler mouse;
    private KeyHandler key;


    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private GameStateManager gsm;
    private boolean Started = false;
    private final Object lock = new Object();

    public GamePanel(int width, int height) {

        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init(){
        running = true;

        this.setLayout(new BorderLayout());
        // Create the menu panel with BorderLayout
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(Color.gray);

        // Create the panel for the menu text
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(0, 0, 0, 0));
        textPanel.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("Menu", SwingConstants.CENTER);
        titleLabel.setForeground(new Color(255, 255, 255, 255));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 120));
        textPanel.add(titleLabel);

        // Add the panel for the menu text to the center of the upper half
        menuPanel.add(textPanel, BorderLayout.NORTH);

        // Create the play button
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 45));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (lock) {
                    menuPanel.setVisible(false);
                    Started = true;
                    lock.notify(); // Notify the waiting thread
                }
            }
        });

        JPanel but = new JPanel();
        but.setPreferredSize(new Dimension(10, 450));
        but.add(playButton, BorderLayout.CENTER);
        but.setBackground(new Color(0, 0, 0, 0));
        playButton.setBackground(new Color(45, 189, 84, 255));
        playButton.setPreferredSize(new Dimension(400, 100));
        playButton.setForeground(Color.white);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 45));

        JPanel but2 = new JPanel();
        but2.setPreferredSize(new Dimension(10, 300));
        but2.add(quitButton, BorderLayout.CENTER);
        but2.setBackground(new Color(0, 0, 0, 0));
        quitButton.setBackground(new Color(213, 25, 59, 255));
        quitButton.setForeground(Color.white);
        quitButton.setPreferredSize(new Dimension(400, 100));

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LOL");
                Game_Launcher.Instance.window.dispose();
            }
        });


        menuPanel.add(but, BorderLayout.CENTER);
        menuPanel.add(but2, BorderLayout.SOUTH);

        // Add the menu panel to the center of the game panel
        add(menuPanel, BorderLayout.CENTER);

        img = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }



    public void run() {
        final int CONVERTER = 1000000000;
        init();
        synchronized (lock) {
            while (!Started) {
                try {
                    lock.wait(); // Wait until notified
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        final double GAME_HERTZ = 60.0;
        final double TBU = CONVERTER / GAME_HERTZ; // time before updating

        final int MUBC = 5; // must update b4 counter

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = CONVERTER / TARGET_FPS; //Total time b4 render;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / CONVERTER);
        oldFrameCount = 0;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBC)) {
                update();
                input(mouse,key);
                lastUpdateTime+= TBU;
                updateCount++;
            }

            if(now-lastUpdateTime >TBU)
            {
                lastUpdateTime = now - TBU;
            }

            input(mouse,key);
            render();
            draw();

            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime/ CONVERTER);
            if(thisSecond >lastSecondTime) {
                if(frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND" + thisSecond + " "+ frameCount);
                    oldFrameCount = frameCount;
                }
            frameCount = 0;
            lastSecondTime = thisSecond;
            }

            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
                Thread.yield();

                try{
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("Error:yielding thread");

                }
                now = System.nanoTime();
            }
        }

    }


    public void update(){
        gsm.update();
    }

    public void input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse,key);
    }

    public void render(){
        if( g != null) {
            g.setColor(new Color(50, 50, 50));
            g.fillRect(0,0,width,height);
            gsm.render(g);
        }
    }

    public void draw(){
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img,0,0,width,height,null);
        g2.dispose();
    }

}

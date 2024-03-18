package Game;

import Game.States.GameStateManager;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.security.KeyRep;


public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private MouseHandler mouse;
    private KeyHandler key;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private GameStateManager gsm;


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

        img = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }

    public void run() {
        final int CONVERTER = 1000000000;
        init();

        final double GAME_HERTZ = 60.0;
        final double TBU = CONVERTER / GAME_HERTZ; // time before updating

        final int MUBC = 5; // must update b4 counter

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = CONVERTER / TARGET_FPS; //Total time b4 render;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / CONVERTER);
        int oldFrameCount = 0;

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
            g.setColor(new Color(102, 0, 255));
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

package Game.Graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;

    private float count;
    private float delay;

    private int timesPlayed;

    public Animation(BufferedImage[] frames){
        timesPlayed = 0;
        setFrames(frames);
    }

    public Animation(){
        timesPlayed = 0;
    }

    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 1f;
        numFrames = frames.length;
    }

    public void setDelay(int i) { delay = i; }
    public void setFrame(int i) { currentFrame = i; }
    public void setNumFrames(int i) { numFrames = i; }

    public void update(){
        if(delay == -1) return;

        ++count;

        if(count == delay){
            currentFrame++;
            count = 0;
        }
        if(currentFrame == numFrames){
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public float getDelay() { return delay; }
    public float getFrame() { return currentFrame; }
    public float getCount() { return count; }
    public BufferedImage getImage() { return frames[currentFrame]; }
    private boolean hasPlayedOnce() { return timesPlayed>0; }
    public boolean hasPlayed(int i) { return timesPlayed == i; }

}

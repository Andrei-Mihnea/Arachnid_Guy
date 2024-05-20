package Game.Entity;

import Game.Graphics.Animation;
import Game.Graphics.Sprite;
import Game.Util.AABB;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;
import Game.Util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public boolean alive;

    private int UP = 3;
    private int DOWN = 0;
    private int RIGHT = 2;
    private int LEFT = 1;
    private int ATTACKU = 7;
    private int ATTACKD = 4;
    private int ATTACKR = 6;
    private int ATTACKL = 5;
    private int DEAD = 6;

    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean once;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;

    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;


    protected float maxSpeed = 3f;
    protected float acc = 3f;
    protected float deacc = 2f;

    protected AABB hitBounds;
    protected AABB bounds;
    protected AABB tc;

    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        pos = new Vector2f(origin);
        this.size = size;
        once = false;
        alive = true;
        bounds = new AABB(origin, size / 2 - 30, size - 55);
        hitBounds = new AABB(origin, size, size);
        hitBounds.setXOffset(size / 2);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);

    }

    public void setPos(Vector2f v) {
        pos.x = v.x;
        pos.y = v.y;
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void setOrder(int down, int up, int left, int right, int dead) {
        this.DOWN = down;
        this.UP = up;
        this.LEFT = left;
        this.RIGHT = right;
        this.DEAD = dead;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(int i) {
        size = i;
    }

    public void setMaxSpeed(float f) {
        maxSpeed = f;
    }

    public void setAcc(float f) {
        acc = f;
    }

    public void setDeacc(float f) {
        deacc = f;
    }

    public AABB getBounds() {
        return bounds;
    }

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return ani;
    }

    public void animate() {
        if (up) {
            if (attack) {
                if (currentAnimation != ATTACKU || ani.getDelay() == -1) {
                    setAnimation(ATTACKU, sprite.getSpriteArray(ATTACKU), 8);
                }
            } else if (currentAnimation != UP || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 8);
            }
        } else if (down) {
            if (attack) {
                if (currentAnimation != ATTACKD || ani.getDelay() == -1) {
                    setAnimation(ATTACKD, sprite.getSpriteArray(ATTACKD), 8);
                }
            } else if (currentAnimation != DOWN || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 8);
            }
        } else if (right) {
            if (attack) {
                if (currentAnimation != ATTACKR || ani.getDelay() == -1) {
                    setAnimation(ATTACKR, sprite.getSpriteArray(ATTACKR), 8);
                }
            } else if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 8);
            }
        } else if (left) {
            if (attack) {
                if (currentAnimation != ATTACKL || ani.getDelay() == -1) {
                    setAnimation(ATTACKL, sprite.getSpriteArray(ATTACKL), 8);
                }
            } else if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 8);
            }

        } else if (attack) {
            if (currentAnimation != ATTACKR || ani.getDelay() == -1) {
                setAnimation(ATTACKR, sprite.getSpriteArray(ATTACKR), 8);
            }
        } else if( !alive ) {
            if (currentAnimation != DEAD || ani.getDelay() == -1){
                setAnimation( DEAD, sprite.getSpriteArray(DEAD),8);
            }
        } else {
            setAnimation(DOWN, sprite.getSpriteArray(DOWN), 8);
        }

    }



    private void setHitBoxDirection(){
        if(up){
            hitBounds.setYOffset(-size/2);
            hitBounds.setXOffset(0);
        }
        else if(down){
            hitBounds.setYOffset(size/2);
            hitBounds.setXOffset(0);
        }
        else if(left){
            hitBounds.setXOffset(-size/2);
            hitBounds.setYOffset(0);
        }
        else if(right){
            hitBounds.setXOffset(size/2);
            hitBounds.setYOffset(0);
        }
    }

    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();

    }

    public abstract void render(Graphics2D g);
    public  void input(KeyHandler key, MouseHandler mouse){

    }

    public float getMaxSpeed(){
        return maxSpeed;
    }
}

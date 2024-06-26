package Game.Tiles.blocks;

import Game.Util.Vector2f;
import Game.Util.AABB;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int w;
    protected int h;

    protected BufferedImage img;
    protected Vector2f pos;
    public int type;
    public Block(BufferedImage img, Vector2f pos, int w, int h){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public abstract boolean update(AABB p);

    public void render(Graphics2D g){
        g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y,w,h,null);

    }
    public void delete(Graphics2D g){
        //g.dispose();
    }

}

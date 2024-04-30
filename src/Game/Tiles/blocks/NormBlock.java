package Game.Tiles.blocks;

import Game.Tiles.blocks.Block;
import Game.Util.AABB;
import Game.Util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block{

    public NormBlock(BufferedImage img, Vector2f pos, int w, int h){

        super(img,pos,w,h);
        type = 1;
    }

    public boolean update(AABB p){
        return false;
    }

    public void render(Graphics2D g){
        super.render(g);
    }

}

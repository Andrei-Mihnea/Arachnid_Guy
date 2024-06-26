package Game.Util;

import Game.Entity.Entity;
import Game.Tiles.TileMapObj;
import Game.Tiles.blocks.Block;
import Game.Tiles.blocks.HoleBlock;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

public class AABB {

    public Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;
    private float r;
    private int size;
    private Entity e;

    public AABB(Entity e){
        this.e = e;
        this.pos = new Vector2f(0,0);
    }

    public AABB(Vector2f pos, int w,int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public AABB(Vector2f pos, int r){
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public Vector2f getPos() { return pos; }

    public float getRadius() { return r; }
    public float getWidth() { return w; }
    public float getHeight() { return h; }

    public void setBox(Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public void setCircle(Vector2f pos, int r, Entity e){
        this.pos = pos;
        this.r = r;
        this.e = e;

        size = r;
    }

    public void setWidth(float f){ w = f; }
    public  void setHeight(float f){ h = f; }

    public void setXOffset(float f){ xOffset = f; }
    public void setYOffset(float f){ yOffset = f; }

    public float getXOffset(){ return xOffset; }
    public float getYOffset(){ return yOffset; }


    public boolean collides( AABB bBox){
        float ax = ((pos.getWorldVar().x + (xOffset)) + (this.w/2));
        float ay = ((pos.getWorldVar().y + (yOffset)) + (this.h/2));
        float bx = ((bBox.pos.getWorldVar().x +(bBox.xOffset)) +(bBox.getWidth()/2));
        float by = ((bBox.pos.getWorldVar().y +(bBox.yOffset)) +(bBox.getHeight()/2));

        //System.out.println("("+ay + ", "+ by+")"+"-----"+ ((this.h / 2) + (bBox.h / 2)));

        if(Math.abs(ax-bx) < (this.w / 2) + (bBox.getWidth() / 2)){
            if(Math.abs(ay-by) < (this.h / 2) + (bBox.getHeight() / 2)){
                return true;
            }
        }
        return false;
    }

   /* public boolean colCircleBox(AABB aBox){
        float cx = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
        float cy = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.pos.getWorldVar().y + (aBox.getWidth() / 2), Math.min(cy, aBox.pos.getWorldVar().y));

        if((xDelta * xDelta + yDelta * yDelta) < ((this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2)))){
            return true;
        }

        return false;
    }*/

    public boolean collisionTile(float ax, float ay){
        List<Integer> xl = new ArrayList<Integer>();
        List<Integer> yl = new ArrayList<Integer>();
        for (int c = 0; c < 4;++c){
            int xt = (int) ((pos.x + ax)+ (c % 2) * w + xOffset)/64;
            int yt = (int) ((pos.y + ay)+ ((int)(c / 2))* h + yOffset)/64;

            xl.add(xt);
            yl.add(yt);
            //System.out.println((pos.x+ax)+ " "+(pos.y+ay));
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt)+","+String.valueOf(yt))){
                int res = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).type;

                return TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(this);
            }
        }


        return false;
    }

    /*public boolean collisionTile(float ax, float ay) {
        for (int c = 0; c < 4; ++c) {

            int xt = (int) ((e.getBounds().getPos().x + ax) + (c % 2) * e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
            int yt = (int) ( (e.getBounds().getPos().y + ay) + ((int)(c/2)) * e.getBounds().getHeight()+ e.getBounds().getYOffset())/64;

            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt)+","+String.valueOf(yt))){
                Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt)+","+String.valueOf(yt));
                if(block instanceof HoleBlock){
                    return collisionHole(ax,ay,xt,yt,block);
                }
            }
        }
        return false;
    }*/

    public boolean collisionHole(float ax,float ay,float xt, float yt, Block block){
        int nextXt = (int) ((e.getBounds().getPos().x + ax) + e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
        int nextYt = (int) ( (e.getBounds().getPos().y + ay) + e.getBounds().getHeight()+ e.getBounds().getYOffset())/64;

        if((nextXt == yt + 1) || (nextXt == xt + 1)){
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(nextXt)+","+String.valueOf(nextYt))){
                Block blockNeighbor = TileMapObj.tmo_blocks.get(String.valueOf(nextXt)+"," +String.valueOf(nextYt));
                return blockNeighbor.update(e.getBounds());
            }
        }

        return false;
    }
    public boolean colCircleBox(AABB aBox){
        float dx = Math.max(aBox.getPos().getWorldVar().x + aBox.getXOffset(), Math.min(pos.getWorldVar().x + (r / 2), aBox.getPos().getWorldVar().x + aBox.getXOffset() + aBox.getWidth()));
        float dy = Math.max(aBox.getPos().getWorldVar().y + aBox.getYOffset(), Math.min(pos.getWorldVar().y + (r / 2), aBox.getPos().getWorldVar().y + aBox.getYOffset() + aBox.getHeight()));
        dx = pos.getWorldVar().x + (r / 2) - dx;
        dy = pos.getWorldVar().y + (r / 2) - dy;

        //System.out.println(Vector2f.distance(aBox.pos,pos)+" "+pos);
        if(Vector2f.distance(aBox.pos,pos) < 250)
            return true;
        /*if( Math.sqrt(dx * dx + dy * dy) < (r / 2)){
            return true;
        }
*/
        return false;

    }

    public boolean Kill(AABB enemy){
        if(Vector2f.distance(enemy.pos,pos) < 70){
            return true;
        }
        return false;
    }
}

package Game.Entity;

import Game.Graphics.Sprite;
import Game.Util.AABB;
import Game.Util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{
    private int r;
    AABB sense;
    AABB tc;

    public Enemy(Sprite sprite, Vector2f origin, int size){
        super(sprite, origin, size);

        //sprite.setHeight(sprite.h/2);
        //sprite.setWidth(sprite.w/2);

        //System.out.println(sprite.w+", "+sprite.h);

        acc = 0.2f;
        maxSpeed = 3f;
        r = 500;
        bounds.setXOffset(5);
        bounds.setYOffset(3);

        sense = new AABB(new Vector2f(pos.x, pos.y ),r);
        tc = new AABB(this);

    }

    public void move(Player player){

        if(sense.colCircleBox(player.getBounds())) {
            //System.out.println(1);
            if (pos.y > player.pos.y) {
                dy -= acc;
                if (dy < maxSpeed) {
                    dy = -maxSpeed;
                }
            } else {
                if (dy < 0) {
                    dy += deacc;
                    if (dy > 0) {
                        dy = 0;
                    }
                }
            }
            if (pos.y < player.pos.y) {
                dy += acc;
                if (dy > 0) {
                    dy = maxSpeed;
                }

            } else {
                if (dy > 0) {
                    dy -= deacc;
                    if (dy < 0) {
                        dy = 0;
                    }
                }
            }
            if (pos.x > player.pos.x) {
                dx -= acc;
                if (dx < 0) {
                    dx = -maxSpeed;
                }
                //dx = -maxSpeed;
            } else {
                if (dx < 0) {
                    dx += deacc;
                    if (dx > 0) {
                        dx = 0;
                    }
                }
            }
            if (pos.x < player.pos.x) {
                dx += acc;
                if (dx > maxSpeed)
                    dx = maxSpeed;

                //dx = maxSpeed;

            } else {
                if (dx > 0) {
                    dx -= deacc;
                    if (dx < 0) {
                        dx = 0;
                    }
                }
            }

        } else{
            dx = 0;
            dy = 0;
        }
        sense.pos.x = pos.x;
        sense.pos.y = pos.y;
    }


    public void update(Player player) {
        super.update();
        move(player);

        if(!tc.collisionTile(dx, 0)) {

            sense.getPos().x += dx;
            pos.x += dx;


        }
        if(!tc.collisionTile(0,dy)){
            sense.getPos().y += dy;
            pos.y += dy;
        }
        /*System.out.print("("+sense.getPos().x+","+sense.getPos().y +")------------- ");
        System.out.println("("+player.pos.x+","+player.pos.y+")");*/
    }

    public int getW(){
        return sprite.w;
    }

    public int getH(){
        return sprite.h;
    }

    @Override
    public void render(Graphics2D g){
        g.setColor(Color.green);

        g.drawRect((int)(pos.getWorldVar().x+ bounds.getXOffset()), (int)(pos.getWorldVar().y + bounds.getYOffset()),(int)bounds.getWidth()*2,(int)bounds.getHeight()*2);

        //System.out.println("("+sense.getPos().getWorldVar().x+" "+sense.getPos().getWorldVar().y+")"+"- "+r);
        g.setColor(Color.blue);
        g.drawOval((int)(pos.getWorldVar().x-r/2),(int)(pos.getWorldVar().y-r/2), r, r);
        g.drawImage(ani.getImage(),(int)(pos.getWorldVar().x),(int)(pos.getWorldVar().y),size, size,null);
    }
}

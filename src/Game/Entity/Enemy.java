package Game.Entity;

import Game.Graphics.Sprite;
import Game.Util.AABB;
import Game.Util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{
    private int r;
    private int hp = 1;
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
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
        //bounds.setWidth(bounds.getWidth() *10 );
        //bounds.setHeight(bounds.getHeight() *3 );
        sense = new AABB(new Vector2f(pos.x, pos.y ),r);
        tc = new AABB(this);

    }

    public void move(Player player) {
        if (alive) {
            if (sense.colCircleBox(player.getBounds())) {
                //System.out.println(1);
                if (pos.y > player.pos.y + 1) {
                    dy -= acc;
                    up = true;
                    down = false;
                    if (dy < -maxSpeed) {
                        dy = -maxSpeed;
                    }
                } else if (pos.y < player.pos.y - 1) {
                    dy += acc;
                    down = true;
                    up = false;
                    if (dy > maxSpeed) {
                        dy = maxSpeed;
                    }

                } else {
                    dy = 0;
                    up = false;
                    down = false;
                }

                if (pos.x > player.pos.x + 1) {
                    dx -= acc;
                    right = false;
                    left = true;
                    if (dx < -maxSpeed) {
                        dx = -maxSpeed;
                    }
                    //dx = -maxSpeed;
                } else if (pos.x < player.pos.x - 1) {
                    dx += acc;
                    right = true;
                    left = false;
                    if (dx > maxSpeed)
                        dx = maxSpeed;

                    //dx = maxSpeed;

                } else {
                    dx = 0;
                    right = false;
                    left = false;
                }

            } else {
                up = false;
                down = false;
                left = false;
                right = false;
                dx = 0;
                dy = 0;
            }
            sense.pos.x = pos.x;
            sense.pos.y = pos.y;
        } else{
            dx = 0;
            dy = 0;
        }
    }


    public void update(Player player) {
        if (alive) {
            super.update();
            move(player);

            if (!sense.collisionTile(dx, 0)) {
                bounds.pos.x += dx;
                sense.getPos().x += dx;
                pos.x += dx;


            }
            if (!sense.collisionTile(0, dy)) {
                bounds.pos.y += dy;
                sense.getPos().y += dy;
                pos.y += dy;
            }
        }else{

            super.update();
            setAnimation(6,sprite.getSpriteArray(6),4);


        }

    }

    public int getW(){
        return sprite.w;
    }

    public int getH(){
        return sprite.h;
    }

    @Override
    public void render(Graphics2D g){
        g.setColor(Color.red);

        g.drawRect((int)(pos.getWorldVar().x+ bounds.getXOffset()), (int)(pos.getWorldVar().y + bounds.getYOffset()),(int)bounds.getWidth(),(int)bounds.getHeight());

        //System.out.println("("+sense.getPos().getWorldVar().x+" "+sense.getPos().getWorldVar().y+")"+"- "+r);
        g.setColor(Color.blue);
        g.drawOval((int)(pos.getWorldVar().x-r/2),(int)(pos.getWorldVar().y-r/2), r, r);
        g.drawImage(ani.getImage(),(int)(pos.getWorldVar().x),(int)(pos.getWorldVar().y),size, size,null);
    }

    public boolean checkHP(){
        if(hp > 0){
            return true;
        }
        return false;
    }
    public void removeLife(){
        --hp;
    }
    public void dead(){
        alive = false;
    }
}

package Game.Entity;

import Game.Graphics.Sprite;
import Game.Util.AABB;
import Game.Util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{
    private int r;
    AABB sense;
    public Enemy(Sprite sprite, Vector2f origin, int size){
        super(sprite, origin, size);

        acc = 1f;
        maxSpeed = 3f;
        r = 135;
        bounds.setXOffset(50);
        bounds.setYOffset(30);

        sense = new AABB(new Vector2f(origin.x - size/2, origin.y - size/2),r);
    }

    public void update(AABB player){
        if(sense.colCircleBox(player)){
            System.out.println("Player detected");
        }
    }

    @Override
    public void render(Graphics2D g){
        g.setColor(Color.green);
        g.drawRect((int)(pos.getWorldVar().x+ bounds.getXOffset()), (int)(pos.getWorldVar().y + bounds.getYOffset()),(int)bounds.getWidth(),(int)bounds.getHeight());

        g.setColor(Color.blue);
        g.drawOval((int)(sense.getPos().getWorldVar().x),(int)(sense.getPos().getWorldVar().y), r, r);
        g.drawImage(ani.getImage(),(int)(pos.getWorldVar().x),(int)(pos.getWorldVar().y),size, size,null);
    }
}

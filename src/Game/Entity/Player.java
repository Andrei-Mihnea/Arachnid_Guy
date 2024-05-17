package Game.Entity;

import Game.GamePanel;
import Game.Graphics.Sprite;
import Game.States.PlayState;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;
import Game.Util.Vector2f;
import Game.States.PlayState;

import java.awt.*;
import java.sql.SQLOutput;

public class Player extends Entity{

    public Player(Sprite sprite, Vector2f origin, int size) {

        super(sprite, origin, size);
        acc = 2f;
        maxSpeed = 8f;
        bounds.setXOffset(50);
        bounds.setYOffset(30);
    }

    public void move(){
        if(up){
            dy-=acc;
            if(dy<maxSpeed){
                dy = -maxSpeed;
            }
        }
        else{
            if( dy < 0) {
                dy += deacc;
                if(dy > 0){
                    dy = 0;
                }
            }
        }
        if(down) {
            dy+= acc;
            if (dy > 0) {
                dy = maxSpeed;
            }

        }
        else{
            if( dy > 0) {
                dy -= deacc;
                if(dy < 0){
                    dy = 0;
                }
            }
        }
        if(left){
            dx -= acc;
            if(dx <0) {
                dx = -maxSpeed;
            }
            //dx = -maxSpeed;
        }
        else{
            if( dx < 0) {
                dx += deacc;
                if(dx > 0){
                    dx = 0;
                }
            }
        }
        if(right){
            dx += acc;
            if(dx > maxSpeed)
                dx = maxSpeed;

            //dx = maxSpeed;

        }
        else{
            if( dx > 0) {
                dx -= deacc;
                if(dx < 0){
                    dx = 0;
                }
            }
        }

    }



    public void update(Enemy enemy){
        super.update();
        move();

        if(bounds.Kill(enemy.getBounds()) && enemy.alive){
            setPos( new Vector2f(0+(GamePanel.width/2)-150,0+(GamePanel.height/2)+150));
            PlayState.map.x = dx;
            PlayState.map.y = dy;
        }

        if(attack && hitBounds.collides(enemy.getBounds())&&enemy.checkHP()){
            System.out.println("LOL");
            enemy.removeLife();
            if(!enemy.checkHP()){
                enemy.dead();
                System.out.println("Enemy dead");
            }
        }
        if(!bounds.collisionTile(dx,0)){
            PlayState.map.x+=dx;
            pos.x += dx;
            bounds.pos.x = pos.x;

        }
        if(!bounds.collisionTile(0,dy)) {
            PlayState.map.y += dy;
            pos.y += dy;
            bounds.pos.y = pos.y;
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.drawRect(((int) (pos.getWorldVar().x+bounds.getXOffset())), (int) (pos.getWorldVar().y+bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g.drawImage(ani.getImage(),(int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size,null );

        if(attack) {
            g.setColor(Color.red);
            g.drawRect((int)(hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()),(int)(hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()),(int)hitBounds.getWidth(),(int)hitBounds.getHeight());
        }

    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if(mouse.getButton() == 1){
            System.out.println("Player" + pos.x+ ", " + pos.y);
        }

        if (key.up.down) {
            up = true;
        } else {
            up = false;
        }

        if (key.down.down) {
            down = true;
        } else {
            down = false;
        }

        if (key.left.down) {
            left = true;
        }
        else {
            left = false;
        }

        if (key.right.down) {
            right = true;
         } else {
            right = false;
        }

        if (key.attack.down) {
            attack = true;
        } else {
            attack = false;
        }
    }

    private Vector2f ResetPos(){
        return new Vector2f(0+(GamePanel.width/2)-150,0+(GamePanel.height/2)+150);
    }
}

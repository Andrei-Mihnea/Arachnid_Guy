package Game.Entity;

import Game.Graphics.Sprite;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;
import Game.Util.Vector2f;

import java.awt.*;

public class Player extends Entity{

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void move(){
        if(up){
            if(dy <0 ) {
                return;
            }
            dy -= acc;
        }
        /*else{
            if( dy < 0) {
                dy += deacc;
                if(dy > 0){
                    dy = 0;
                }
            }
        }*/
        if(down) {
            if (dy > boundY)
                return;
            dy += acc;
            //dy = maxSpeed;

        }
        /*else{
            if( dy > 0) {
                dy -= deacc;
                if(dy < 0){
                    dy = 0;
                }
            }
        }*/
        if(left){
            if(dx <0)
                return;
            dx -= acc;
                //dx = -maxSpeed;
        }
        /*else{
            if( dx < 0) {
                dx += deacc;
                if(dx > 0){
                    dx = 0;
                }
            }
        }*/
        if(right){
            if(dx > boundX)
                return;
            dx += acc;
            //dx = maxSpeed;

        }
        /*else{
            if( dx > 0) {
                dx -= deacc;
                if(dx < 0){
                    dx = 0;
                }
            }
        }*/

    }

    public void update(){
        super.update();
        move();
        pos.x = dx;
        pos.y = dy;
        //System.out.println(dx+","+dy);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(),(int) (pos.x), (int) (pos.y), size, size,null );
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
}

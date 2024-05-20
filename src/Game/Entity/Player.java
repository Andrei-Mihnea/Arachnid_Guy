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
import java.util.Vector;

public class Player extends Entity{

    public static int kills = 0;

    private static Player instance;
    public static Player Instance() {return instance; };

    //private Boolean alr;
    public Player(Sprite sprite, Vector2f origin, int size) {

        super(sprite, origin, size);
        acc = 2f;
        maxSpeed = 8f;
        bounds.setXOffset(50);
        bounds.setYOffset(30);
        instance = this;
    }
    static int i =0;
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



    public void update(Vector<Enemy> enemies){
        super.update();
        move();
        for(Enemy enemy : enemies) {
            if (bounds.Kill(enemy.getBounds()) && enemy.alive) {
                //death
                switch ( PlayState.CurrLevel){
                    case 0:
                        System.out.println(0);
                        resetPos0(enemies);
                        break;
                    case 1:
                        System.out.println(1);
                        resetPos1( enemies);
                        break;
                    case 2:
                        System.out.println(2);
                        resetPos2(enemies);
                        break;
                }



            }

            if (attack && hitBounds.collides(enemy.getBounds()) && enemy.checkHP()) {
                enemy.removeLife();
                if (!enemy.checkHP()) {
                    enemy.dead();
                    ++kills;
                    System.out.println("Enemy dead");
                }
            }

        }

        if (!bounds.collisionTile(dx, 0)) {
            PlayState.map.x += dx;
            pos.x += dx;
            bounds.pos.x = pos.x;

        }
        if (!bounds.collisionTile(0, dy)) {
            PlayState.map.y += dy;
            pos.y += dy;
            bounds.pos.y = pos.y;
        }

        //System.out.println(pos.x + " " + pos.y + " | " + PlayState.map.x + " " + PlayState.map.y);
    }

    public static void ChangeLvl()
    {
        PlayState.map.x = Instance().pos.x;
        PlayState.map.y = Instance().pos.y;
    }


    public void update2(Enemy enemy){
        super.update();
        move();



            if (bounds.Kill(enemy.getBounds()) && enemy.alive) {
                setPos(new Vector2f(0 + (GamePanel.width / 2) - 150, 0 + (GamePanel.height / 2) + 150));
                PlayState.map.x = dx;
                PlayState.map.y = dy;
            }

            if (attack && hitBounds.collides(enemy.getBounds()) && enemy.checkHP()) {
                enemy.removeLife();
                if (!enemy.checkHP()) {
                    enemy.dead();
                    ++kills;
                    System.out.println("Enemy dead");
                }
            }
            if (!bounds.collisionTile(dx, 0)) {
                PlayState.map.x += dx;
                pos.x += dx;
                bounds.pos.x = pos.x;

            }
            if (!bounds.collisionTile(0, dy)) {
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

    private void resetPos0(Vector<Enemy> enemies) {

        Vector2f v = new Vector2f(0 + (GamePanel.width / 2) - 150, 0 + (GamePanel.height / 2) + 150);
        setPos (v);


        if (enemies.get(0).alive) {
            enemies.get(0).setPos(new Vector2f(0 + (GamePanel.width / 2) + 50, 0 + (GamePanel.height / 2) + 2500));
        }
        if (enemies.get(1).alive) {
            enemies.get(1).setPos(new Vector2f(0 + (GamePanel.width / 2) - 400, 0 + (GamePanel.height / 2) + 2500));
        }
        if (enemies.get(2).alive) {
            enemies.get(2).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1400, 0 + (GamePanel.height / 2) + 850));
        }
        if (enemies.get(3).alive) {
            enemies.get(3).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1900, 0 + (GamePanel.height / 2) + 850));
        }
        if (enemies.get(4).alive) {
            enemies.get(4).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1900, 0 + (GamePanel.height / 2) + 2000));
        }
        if (enemies.get(5).alive) {
            enemies.get(5).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1400, 0 + (GamePanel.height / 2) + 2000));
        }

        PlayState.map = new Vector2f(v.x - 490, v.y - 510);


    }

    private void resetPos1(Vector<Enemy> enemies){
        Vector2f v = new Vector2f(0+(GamePanel.width/2)+200,0+(GamePanel.height/2)+550);
        setPos (v);

        if(enemies.get(0).alive) {
            enemies.get(0).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1000, 0 + (GamePanel.height / 2) - 50));
        }
        if(enemies.get(1).alive) {
            enemies.get(1).setPos(new Vector2f(0 + (GamePanel.width / 2) + 700, 0 + (GamePanel.height / 2) - 50));
        }
        if(enemies.get(2).alive) {
            enemies.get(2).setPos(new Vector2f(0 + (GamePanel.width / 2) + 2000, 0 + (GamePanel.height / 2) + 550));
        }
        if(enemies.get(3).alive) {
            enemies.get(3).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1750, 0 + (GamePanel.height / 2) + 800));
        }
        if(enemies.get(4).alive) {
            enemies.get(4).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1200, 0 + (GamePanel.height / 2) + 1650));
        }
        if(enemies.get(5).alive) {
            enemies.get(5).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1400, 0 + (GamePanel.height / 2) + 1850));
        }
        if(enemies.get(6).alive) {
            enemies.get(6).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1000, 0 + (GamePanel.height / 2) + 1850));
        }

        PlayState.map = new Vector2f(v.x - 490, v.y - 510);
    }

    private void resetPos2(Vector<Enemy> enemies){
        Vector2f v = new Vector2f(0+(GamePanel.width/2)+25,0+(GamePanel.height/2)+1700);
        setPos(v);


        if(enemies.get(0).alive) {
            enemies.get(0).setPos(new Vector2f(0 + (GamePanel.width / 2) + 800, 0 + (GamePanel.height / 2) + 1500));
        }
        if(enemies.get(1).alive) {
            enemies.get(1).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1200, 0 + (GamePanel.height / 2) + 1500));
        }
        if(enemies.get(2).alive) {
            enemies.get(2).setPos(new Vector2f(0 + (GamePanel.width / 2) + 800, 0 + (GamePanel.height / 2) + 1800));
        }
        if(enemies.get(3).alive) {
            enemies.get(3).setPos(new Vector2f(0 + (GamePanel.width / 2) + 2100, 0 + (GamePanel.height / 2) + 1700));
        }
        if(enemies.get(4).alive) {
            enemies.get(4).setPos(new Vector2f(0 + (GamePanel.width / 2) + 2100, 0 + (GamePanel.height / 2) + 800));
        }
        if(enemies.get(5).alive) {
            enemies.get(5).setPos(new Vector2f(0 + (GamePanel.width / 2) + 1700, 0 + (GamePanel.height / 2) + 300));
        }
        if(enemies.get(6).alive) {
            enemies.get(6).setPos(new Vector2f(0 + (GamePanel.width / 2) + 800, 0 + (GamePanel.height / 2) + 300));
        }


        PlayState.map = new Vector2f(v.x - 490, v.y - 510);
    }
}

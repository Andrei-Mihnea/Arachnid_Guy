package Game.States;

import Game.Entity.Enemy;
import Game.Entity.Player;
import Game.GamePanel;
import Game.Graphics.Font;
import Game.Graphics.Sprite;
import Game.Tiles.TileManager;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;
import Game.Util.Vector2f;

import java.awt.Graphics2D;
import java.util.Vector;

public class PlayState extends GameState{

    public boolean ok;

    private Font font;
    private Player player;
    private Vector<Enemy> enemies;
    private TileManager tm;
    private LevelManager lm;
    private Sprite sprite_enemy;

    public static Vector2f map;
    static int CurrLevel = 0;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x,map.y);
        ok = false;
        tm = new TileManager("tile/lvl0.xml");
        font = new Font("font/font.png",10,10);
        lm = new LevelManager();
        sprite_enemy = new Sprite("entity/guard_white_enemy.png",16);

        sprite_enemy.setSize(16,16);


        lm.lvl0();
        player = new Player(new Sprite("entity/Onyx_Cartier.png",32), lm.player_positions.elementAt(0), 128);
        enemies = new Vector<Enemy>();
        for(int i =0 ; i < lm.enemy_positions.elementAt(0).size();++i){
           Enemy enemy =  new Enemy(sprite_enemy,lm.enemy_positions.elementAt(0).elementAt(i) , 64);
            //Enemy enemy2 =  new Enemy(sprite_enemy,lm.enemy_positions.elementAt(1).elementAt(i) , 64);
            enemy.setOrder(0,1,2,3,4);
            enemies.add(enemy);
            //enemies.add(enemy2);
        }




    }
    private String damn = "Level_Demo";

    public void update(){
        Vector2f.setWorldVar(map.x-35, map.y+100);
        for(int i = 0; i < enemies.size();++i) {
            player.update(enemies.elementAt(i));
            enemies.elementAt(i).update(player);
            if(!enemies.elementAt(i).alive){
                enemies.remove(i);
            }

        }

        if(enemies.size() == 0 ){
            if(CurrLevel == 0)
                goToLVL1();
            else if (CurrLevel == 1)
            {
                //goToLevel2();
            }

            ok = true;
            CurrLevel++;
        }

    }

    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse,key);
    }

    public void render(Graphics2D g){

        tm.render(g);
        Sprite.drawArray(g,font, GamePanel.oldFrameCount+"FPS", new Vector2f(GamePanel.width -192 ,32),32,32);
        Sprite.drawArray(g,font,damn,new Vector2f(0,64),32,32,21,0);
        player.render(g);
        for(int i = 0; i < enemies.size();++i) {
            enemies.elementAt(i).render(g);
        }

    }

    public void goToLVL1(){
        lm.lvl1();
        System.out.println("CHANGE LEVEL");;
        tm.deleteCurrentTiles(GamePanel.g);
        tm = new TileManager("tile/lvl1.xml");
        player = new Player(new Sprite("entity/Onyx_Cartier.png",32), lm.player_positions.elementAt(1), 128);
        enemies = new Vector<Enemy>();

        for(int i =0 ; i < lm.enemy_positions.elementAt(1).size();++i) {
            Enemy enemy = new Enemy(sprite_enemy, lm.enemy_positions.elementAt(1).elementAt(i), 64);
            enemy.setOrder(0, 1, 2, 3, 4);
            enemies.add(enemy);
        }
    }

}
package Game.States;

import Game.Entity.Enemy;
import Game.Entity.Player;
import Game.GamePanel;
import Game.Graphics.Font;
import Game.Graphics.Sprite;
import Game.SQL;
import Game.Tiles.TileManager;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;
import Game.Util.Vector2f;

import java.awt.Graphics2D;
import java.util.Vector;

public class PlayState extends GameState{

    public boolean ok;

    private float originalSpeed;
    private Font font;
    private Player player;
    private Vector<Enemy> enemies;
    private TileManager tm;
    private LevelManager lm;
    private Sprite sprite_enemy1,sprite_enemy2,sprite_enemy3;

    public SQL db;
    public static Vector2f map;
    public static int CurrLevel = 0;

    public PlayState(GameStateManager gsm){
        super(gsm);
        db = new SQL();
        map = new Vector2f();
        Vector2f.setWorldVar(map.x,map.y);
        ok = false;
        tm = new TileManager("tile/lvl0.xml");
        font = new Font("font/font.png",10,10);
        lm = new LevelManager();

        sprite_enemy1 = new Sprite("entity/guard_white_enemy.png",16);
        sprite_enemy1.setSize(16,16);

        sprite_enemy2 = new Sprite("entity/guard_yellow_enemy.png",16);
        sprite_enemy2.setSize(16,16);

        sprite_enemy3 = new Sprite("entity/guard_orange_enemy.png",16);
        sprite_enemy3.setSize(16,16);

        player.kills = db.GetKills();
        if(player.kills == 0){
            db.add(0);
        }
        lm.lvl0();
        player = new Player(new Sprite("entity/Onyx_Cartier.png",32), lm.player_positions.elementAt(0), 128);
        enemies = new Vector<Enemy>();
        for(int i =0 ; i < lm.enemy_positions.elementAt(0).size();++i){
           Enemy enemy =  new Enemy(sprite_enemy1,lm.enemy_positions.elementAt(0).elementAt(i) , 64);
            //Enemy enemy2 =  new Enemy(sprite_enemy,lm.enemy_positions.elementAt(1).elementAt(i) , 64);
            enemy.setOrder(0,1,2,3,4);
            enemies.add(enemy);
            //enemies.add(enemy2);
        }


    }
    private String damn = "Level_Demo";

    public void update(){

        Vector2f.setWorldVar(map.x-35, map.y+100);
        player.update(enemies);
        //player.update2(enemies.elementAt(0));

        for(int i = 0; i < enemies.size();++i) {
            enemies.elementAt(i).update(player);
            if(!enemies.elementAt(i).alive){
                enemies.remove(i);
            }

        }

        if(player.kills != db.GetKills()){
            db.updateKills(player.kills);
        }

        if(enemies.size() == 0 ){
            if(CurrLevel == 0)
                goToLVL1();
            else if (CurrLevel == 1)
            {
                goToLVL2();
            }
            else{
                if(CurrLevel == 2){
                    goToCredits();
                }
            }

            ok = true;
            CurrLevel++;
        }

    }

    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse,key);
    }

    public void render(Graphics2D g){
        String KillStr = String.valueOf(player.kills);
        tm.render(g);
        Sprite.drawArray(g,font, GamePanel.oldFrameCount+"FPS", new Vector2f(GamePanel.width -192 ,32),32,32);
        Sprite.drawArray(g,font,damn,new Vector2f(0,64),32,32,21,0);
        Sprite.drawArray(g,font,KillStr + " Kills", new Vector2f(GamePanel.width -192 ,64),32,20);
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
            Enemy enemy = new Enemy(sprite_enemy2, lm.enemy_positions.elementAt(1).elementAt(i), 64);
            enemy.setHP(2000);
            enemy.setOrder(0, 1, 2, 3, 4);
            enemies.add(enemy);
        }

        ChangeMapPosOnLevelChange(1);
    }

    public void ChangeMapPosOnLevelChange(int i)
    {
        Vector2f v = new Vector2f(lm.player_positions.elementAt(i));
        PlayState.map = new Vector2f(v.x - 490, v.y - 510);
        //System.out.println("AAA " + lm.player_positions.elementAt(1).x + " " + lm.player_positions.elementAt(1).y + " | " + PlayState.map.x + " " + PlayState.map.y);
    }

    public void goToLVL2(){
        lm.lvl2();
        System.out.println("CHANGE LEVEL");;
        tm.deleteCurrentTiles(GamePanel.g);
        tm = new TileManager("tile/lvl2.xml");
        player = new Player(new Sprite("entity/Onyx_Cartier.png",32), lm.player_positions.elementAt(2), 128);
        enemies = new Vector<Enemy>();

        for(int i =0 ; i < lm.enemy_positions.elementAt(2).size();++i) {
            Enemy enemy = new Enemy(sprite_enemy3, lm.enemy_positions.elementAt(2).elementAt(i), 64);
            enemy.setOrder(0, 1, 2, 3, 4);
            enemy.setHP(3);
            enemies.add(enemy);
        }

        ChangeMapPosOnLevelChange(2);
    }

    public void goToCredits(){
        lm.lvl2();
        System.out.println("CHANGE LEVEL");;
        tm.deleteCurrentTiles(GamePanel.g);
        tm = new TileManager("tile/credit.xml");
        player = new Player(new Sprite("entity/Onyx_Cartier.png",32), lm.player_positions.elementAt(2), 128);
        enemies = new Vector<Enemy>();
        enemies.add(new Enemy(new Sprite("entity/Onyx_Cartier.png",32), lm.player_positions.elementAt(2),128));

        ChangeMapPosOnLevelChange(2);
    }

}
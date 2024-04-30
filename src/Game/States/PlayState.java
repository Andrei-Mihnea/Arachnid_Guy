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
import java.awt.Color;

public class PlayState extends GameState{

    private Font font;
    private Player player;
    private Enemy enemy;
    private TileManager tm;


    public static Vector2f map;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x,map.y);

        tm = new TileManager("tile/TEST01.xml");
        font = new Font("font/font.png",10,10);

        player = new Player(new Sprite("entity/Onyx_Cartier.png"), new Vector2f(0+(GamePanel.width/2)-150,0+(GamePanel.height/2)+150), 128);
        enemy =  new Enemy(new Sprite("entity/guard_white_enemy.png"), new Vector2f(0+(GamePanel.width/2) + 100,0+(GamePanel.height/2) + 150), 128);
    }
    private String damn = "Level_Demo";

    public void update(){
        Vector2f.setWorldVar(map.x-35, map.y+100);
        player.update();
        enemy.update();
    }

    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse,key);
    }

    public void render(Graphics2D g){

        tm.render(g);
        Sprite.drawArray(g,font, GamePanel.oldFrameCount+"FPS", new Vector2f(GamePanel.width -192 ,32),32,32,28,0);
        Sprite.drawArray(g,font,damn,new Vector2f(0,64),32,32,21,0);

        player.render(g);
        enemy.render(g);
    }

}

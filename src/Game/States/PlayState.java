package Game.States;

import Game.Entity.Player;
import Game.Graphics.Font;
import Game.Graphics.Sprite;
import Game.Util.KeyHandler;
import Game.Util.MouseHandler;
import Game.Util.Vector2f;

import java.awt.Graphics2D;
import java.awt.Color;

public class PlayState extends GameState{

    private Font font;
    private Player player;

    public PlayState(GameStateManager gsm){
        super(gsm);
        font = new Font("font/ZeldaFont.png",16,16);
        player = new Player(new Sprite("entity/Onyx_Cartier.png"), new Vector2f(800,800), 128);
    }

    public void update(){
        player.update();
    }

    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse,key);
    }

    public void render(Graphics2D g){
        Sprite.drawArray(g,font,"Test test", new Vector2f(100,100),32,32,16,0);
        player.render(g);
    }

}

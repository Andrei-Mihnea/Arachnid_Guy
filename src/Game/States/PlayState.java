package Game.States;

import Game.Util.KeyHandler;
import Game.Util.MouseHandler;

import java.awt.Graphics2D;
import java.awt.Color;

public class PlayState extends GameState{

    public PlayState(GameStateManager gsm){
        super(gsm);
    }

    public void update(){

    }

    public void input(MouseHandler mouse, KeyHandler key){

    }

    public void render(Graphics2D g){
        g.setColor(Color.magenta);
        g.fillRect(100,100,200,200);
    }

}

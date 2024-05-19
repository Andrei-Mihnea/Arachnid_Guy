package Game.States;

import Game.Entity.Player;
import Game.GamePanel;
import Game.Util.Vector2f;

import java.util.Vector;

public class LevelManager {
    public Vector<Vector2f> player_positions;
    public Vector<Vector<Vector2f>> enemy_positions;

    public LevelManager(){
        player_positions = new Vector<Vector2f>(5);
        enemy_positions = new Vector<Vector<Vector2f>>(5);
    }

    public void lvl0(){

        Vector<Vector2f> enemies = new Vector<Vector2f>();

        if(player_positions.size() == 0 ||player_positions.elementAt(0) == null){
            player_positions.add(new Vector2f(0+(GamePanel.width/2)-150,0+(GamePanel.height/2)+150));
        } else{
            player_positions.set(0, new Vector2f(0+(GamePanel.width/2)-150,0+(GamePanel.height/2)+150));
        }

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 100,0+(GamePanel.height/2) + 150));

        if(enemy_positions.size() == 0 || enemy_positions.elementAt(0) == null){
            enemy_positions.add(enemies);
        } else{
            enemy_positions.set(0,enemies);
        }
    }

    public void lvl1(){
        Vector<Vector2f> enemies = new Vector<Vector2f>();

        if(player_positions.size() == 1 ||player_positions.elementAt(1) == null){
            player_positions.add(new Vector2f(0+(GamePanel.width/2)+50,0+(GamePanel.height/2)+350));
            PlayState.map.x += 50;
            PlayState.map.y = 350;
        } else{
            player_positions.set(1, new Vector2f(0+(GamePanel.width/2)+50,0+(GamePanel.height/2)+350));
            PlayState.map.x += 50;
            PlayState.map.y = 350;
        }

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 100,0+(GamePanel.height/2) + 150));

        if(enemy_positions.size() == 1 || enemy_positions.elementAt(1) == null){
            enemy_positions.add(enemies);
        } else{
            enemy_positions.set(1,enemies);
        }
    }



}

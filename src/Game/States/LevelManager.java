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

        enemies.add(new Vector2f(0+(GamePanel.width/2) +50,0+(GamePanel.height/2) + 2500));
        enemies.add(new Vector2f(0+(GamePanel.width/2) -400,0+(GamePanel.height/2) + 2500));

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1400,0+(GamePanel.height/2) + 850));
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1900,0+(GamePanel.height/2) + 850));

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1900,0+(GamePanel.height/2) + 2000));
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1400,0+(GamePanel.height/2) + 2000));


        if(enemy_positions.size() == 0 || enemy_positions.elementAt(0) == null){
            enemy_positions.add(enemies);
        } else{
            enemy_positions.set(0,enemies);
        }
    }

    public void lvl1(){
        Vector<Vector2f> enemies = new Vector<Vector2f>();

        if(player_positions.size() == 1 ||player_positions.elementAt(1) == null){
            player_positions.add(new Vector2f(0+(GamePanel.width/2)+200,0+(GamePanel.height/2)+550));
        } else{
            player_positions.set(1, new Vector2f(0+(GamePanel.width/2)+200,0+(GamePanel.height/2)+550));
        }



        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1000,0+(GamePanel.height/2) - 50));//inamic sus dreapta
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 700,0+(GamePanel.height/2) - 50));

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 2000,0+(GamePanel.height/2) + 550));//inamic coridor principal
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1750,0+(GamePanel.height/2) + 800));//inamic coridor principal

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1200,0+(GamePanel.height/2) + 1650));//inamic camera finala
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1400,0+(GamePanel.height/2) + 1850));
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1000,0+(GamePanel.height/2) + 1850));

        if(enemy_positions.size() == 1 || enemy_positions.elementAt(1) == null){
            enemy_positions.add(enemies);
        } else{
            enemy_positions.set(1,enemies);
        }
        Player.ChangeLvl();
    }

    public void lvl2(){

        Vector<Vector2f> enemies = new Vector<Vector2f>();

        if(player_positions.size() == 2 ||player_positions.elementAt(2) == null){
            player_positions.add(new Vector2f(0+(GamePanel.width/2)+25,0+(GamePanel.height/2)+1700));
        } else{
            player_positions.set(2, new Vector2f(0+(GamePanel.width/2)+25,0+(GamePanel.height/2)+1700));
        }

        enemies.add(new Vector2f(0+(GamePanel.width/2) +800,0+(GamePanel.height/2) + 1500));//inamic camera principala
        enemies.add(new Vector2f(0+(GamePanel.width/2) +1200,0+(GamePanel.height/2) + 1500));//inamic camera principala
        enemies.add(new Vector2f(0+(GamePanel.width/2) +800,0+(GamePanel.height/2) + 1800));//inamic camera principala


        enemies.add(new Vector2f(0+(GamePanel.width/2) + 2100,0+(GamePanel.height/2) + 1700));//inamic coridor principal
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 2100,0+(GamePanel.height/2) + 800));//inamic coridor principal

        enemies.add(new Vector2f(0+(GamePanel.width/2) + 1700,0+(GamePanel.height/2) + 300));//inamic camera finala
        enemies.add(new Vector2f(0+(GamePanel.width/2) + 800,0+(GamePanel.height/2) +300));//inamic camera finala

        if(enemy_positions.size() == 2 || enemy_positions.elementAt(2) == null){
            enemy_positions.add(enemies);
        } else{
            enemy_positions.set(2,enemies);
        }
    }



}

package Game.Tiles;

import java.awt.*;
import java.util.HashMap;

import Game.Graphics.Sprite;
import Game.Tiles.blocks.HoleBlock;
import Game.Util.Vector2f;

import java.util.ArrayList;
import java.util.Vector;

import Game.Tiles.blocks.Block;
import Game.Tiles.blocks.ObjBlock;

public class TileMapObj  extends TileMap{

    public static HashMap<String,Block> tmo_blocks;
    private Vector<Integer> v;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns ){
        Block tempBlock;
        tmo_blocks = new HashMap<String, Block>();
        initV();
        String[] block = data.split(",");
        for(int i = 0; i < (width * height); ++i){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            //System.out.println(temp);
            if(temp!=0){
                if(!check(temp)){
                    tempBlock = new HoleBlock(sprite.getSprite( (int) (( temp - 1 ) % tileColumns),(int)((temp-1) / tileColumns)), new Vector2f((int)(i%width)*tileWidth,(int)(i/height)*tileHeight),tileWidth,tileHeight);

                } else{
                    tempBlock = new ObjBlock(sprite.getSprite( (int) (( temp - 1 ) % tileColumns),(int)((temp-1) / tileColumns)), new Vector2f((int)(i%width)*tileWidth,(int)(i/height)*tileHeight),tileWidth,tileHeight);

                    //System.out.println( (int)(i%width)*tileWidth+" "+(int)(i/height)*tileHeight);
                }
                tmo_blocks.put(String.valueOf((int)(i%width))+","+String.valueOf((int)(i/height)),tempBlock);
            }
        }
    }

    private void initV(){
        v = new Vector<Integer>();
        v.add(1);
        v.add(2);
        v.add(3);
        v.add(4);
        v.add(5);
        v.add(6);
        v.add(13);
        v.add(14);
        v.add(17);
        v.add(18);
        v.add(19);
        v.add(27);
        v.add(30);
        v.add(40);
        v.add(41);
        v.add(42);
        v.add(43);
        v.add(44);
        v.add(45);
    }
    private boolean check(int x){
        for(int i = 0; i < v.size(); ++i){
            if( v.elementAt(i) == x){
                return true;
            }
        }
        return false;
    }
    public void render(Graphics2D g){
        for(Block block: tmo_blocks.values()){
            block.render(g);
        }
    }

    @Override
    public void destroy(Graphics2D g) {
        for(Block block: tmo_blocks.values()){
            block.delete(g);
        }
    }
}

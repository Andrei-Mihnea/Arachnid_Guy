package Game.Tiles;

import java.awt.*;
import java.util.HashMap;

import Game.Graphics.Sprite;
import Game.Tiles.blocks.HoleBlock;
import Game.Util.Vector2f;

import java.util.ArrayList;
import Game.Tiles.blocks.Block;
import Game.Tiles.blocks.ObjBlock;

public class TileMapObj  extends TileMap{

    public static HashMap<String,Block> tmo_blocks;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns ){
        Block tempBlock;
        tmo_blocks = new HashMap<String, Block>();

        String[] block = data.split(",");
        for(int i = 0; i < (width * height); ++i){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            //System.out.println(temp);
            if(temp!=0){
                if(temp != 2 && temp != 3 && temp != 4 && temp != 5 && temp != 13&& temp !=6){
                    tempBlock = new HoleBlock(sprite.getSprite( (int) (( temp - 1 ) % tileColumns),(int)((temp-1) / tileColumns)), new Vector2f((int)(i%width)*tileWidth,(int)(i/height)*tileHeight),tileWidth,tileHeight);

                } else{
                    tempBlock = new ObjBlock(sprite.getSprite( (int) (( temp - 1 ) % tileColumns),(int)((temp-1) / tileColumns)), new Vector2f((int)(i%width)*tileWidth,(int)(i/height)*tileHeight),tileWidth,tileHeight);

                    System.out.println( (int)(i%width)*tileWidth+" "+(int)(i/height)*tileHeight);
                }
                tmo_blocks.put(String.valueOf((int)(i%width))+","+String.valueOf((int)(i/height)),tempBlock);
            }
        }
    }

    public void render(Graphics2D g){
        for(Block block: tmo_blocks.values()){
            block.render(g);
        }
    }
}

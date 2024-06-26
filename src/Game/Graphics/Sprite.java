package Game.Graphics;

import Game.Util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

public class Sprite {

    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    private int tile_size;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;

    private static Font currentFont;

    public Sprite(String file, int Tile_size){
        tile_size = Tile_size;

        w = tile_size;
        h = tile_size;


        //System.out.println(w+", "+h);

        System.out.println("Loading: "+ file+ "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth()/w;
        hSprite = SPRITESHEET.getHeight()/h;
        loadSpriteArray();
    }

    public Sprite(String file, int w, int h){
        this.w = w;
        this.h = h;

        System.out.println("Loading: "+ file+ "...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth()/w;
        hSprite = SPRITESHEET.getHeight()/h;
        loadSpriteArray();
    }

    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width) {
        w = width;
        wSprite = SPRITESHEET.getWidth() / w;
    }

    public void setHeight(int height) {
        h = height;
        hSprite = SPRITESHEET.getHeight() / h;
    }

    public int getWidth(){ return w; }
    public int getHeight(){ return h; }

    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e){
            System.out.println("ERROR: could not load file: "+file);
        }

        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[hSprite][wSprite];


        for (int y = 0; y < hSprite; ++y) {
            for (int x = 0; x < wSprite; ++x) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSPRITESHEET(){
        return SPRITESHEET;
    }

    public BufferedImage getSprite(int x, int y){
        return SPRITESHEET.getSubimage(x*w,y*h,w,h);
    }

    public BufferedImage[] getSpriteArray(int i){
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2(int i){
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset,int yOffset){
        float x = pos.x;
        float y = pos.y;

        for(int i = 0; i < img.size(); ++i){
            if(img.get(i) != null){
                g.drawImage(img.get(i),(int) x, (int) y, width, height , null);
            }

            x+= xOffset;
            y+= yOffset;
        }
    }

    public static void drawArray(Graphics2D g, String word, Vector2f pos,int size){
            drawArray(g,currentFont, word, pos, size, size, size, 0);
    }

    public static void drawArray(Graphics2D g, String word, Vector2f pos,int size,int xOffset){
            drawArray(g,currentFont,word, pos, size, size, xOffset, 0 );
    }

    public static void drawArray(Graphics2D g,Font f, String word, Vector2f pos, int width, int height,int xOffset) {
        drawArray(g,currentFont,word,pos,width,height,xOffset);
    }

    public static void drawArray(Graphics2D g,Font font, String word, Vector2f pos,int size,int xOffset){
        drawArray(g,font,word,pos,size,size,xOffset,0);
    }

    public static void drawArray(Graphics2D g,Font font, String word, Vector2f pos, int width, int height,int xOffset,int yOffset){
        float x = pos.x;
        float y = pos.y;

        currentFont = font;

        for(int i=0; i<word.length();++i) {
            if (word.charAt(i) != 32) {
                g.drawImage(font.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }

    }
}

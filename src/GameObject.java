import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public abstract class GameObject {
    
    protected int x, y;
    protected ID id;
    protected double velX, velY;
    protected double HP;
    protected static BufferedImage[][] animation;
    protected static BufferedImage img;
    protected static BufferedImage bullet;
    protected static BufferedImage sunFall;
    protected static BufferedImage lawnmower;
    protected int aniTick, aniIndex, aniSpeed = 50;
    protected int ObjectConstants;

    
    public GameObject(int x, int y, ID id, int HP){
        this.x = x;
        this.y = y;
        this.id = id;
        this.HP = HP;
        importImg();
        loadAnimation();
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    protected void importImg(){
        InputStream is = getClass().getResourceAsStream("/Ani.png");
        InputStream is2 = getClass().getResourceAsStream("/bullet.png");
        InputStream is3 = getClass().getResourceAsStream("/sun.png");
        InputStream is4 = getClass().getResourceAsStream("/lawnmower.png");

        try {
            img = ImageIO.read(is);
            bullet = ImageIO.read(is2);
            sunFall = ImageIO.read(is3);
            lawnmower = ImageIO.read(is4);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void loadAnimation(){
        animation = new BufferedImage[8][4];
        for(int j = 0; j < animation.length; j++){
            for(int i = 0; i < animation[j].length; i++){
                animation[j][i] = img.getSubimage(i*100, j*101 ,95,95);
            }
        }
    }

    

    protected void updateAnimationTick(int constant) {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= animation[constant].length){
                aniIndex = 0;
            }
        }
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return this.id;
    }

    public void setVelX(double x){
        this.velX = x;
    }
    public double getVelX(){
        return velX;
    }

    public void setVelY(double y){
        this.velY = y;
    }
    public double getVelY(){
        return velY;
    }
    public void setHP(double HP){
        this.HP = HP;
    }
    public double getHP(){
        return HP;
    }
}

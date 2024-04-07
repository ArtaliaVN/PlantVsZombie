import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Projectile extends GameObject {

    private double damage;
    private boolean active;
    private Handler handler = new Handler();
    private SoundEffect hitZombie;
    private ID plantID;
   
    public Projectile (int x, int y, ID id,ID plantID, int HP, Handler handler)
    {
        super(x, y, id, HP);
        getDamage(plantID);
        this.plantID = plantID;
        this.handler = handler;
        hitZombie = new SoundEffect("C:\\Users\\vuduc\\Desktop\\OOP-Project (1) (2)\\OOP-Project (1)\\OOP-Project\\PlantsVsZombies\\src\\quack_5.wav");
    
    }

    public void getDamage(ID plantID){
        if (plantID == ID.Peashooter){
            this.damage = 1;
        }
        else if (plantID == ID.SlowPeashooter){
            this.damage = 0.5;
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        
        g.drawImage(bullet, this.x, this.y, null);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 20, 20);
    }
    
    @Override
    public void tick() 
    {
        x += 10;
        if(x >= Game.WIDTH - 40 || this.HP == 0)
        {
            handler.removeProObject(this);
        }
        collision();
    }

    public void collision(){
        for (int i = 0; i < handler.ZList.size(); i++){
            Zombies temp = handler.ZList.get(i);
            if ( this.getBounds().intersects( temp.getBounds() ) ){
                temp.setHP(temp.getHP() - this.damage);
                this.setHP(0);
                hitZombie.play();
                if (plantID == ID.SlowPeashooter){
                    temp.getSlow(3);
                }
            } 
        }
    }

    public void shoot(int x, int y, ID plantID, GameObject plant){
        for (int i = 0; i < handler.ZList.size(); i++){
            GameObject temp = handler.ZList.get(i);
            if(temp.getBounds().y == plant.getBounds().y){
                setActive(true);
                break;
            }
            else if(temp.getBounds().y != plant.getBounds().y)
                setActive(false);
        }
        if(active){
            handler.addProObject(new Projectile(x, y, ID.Protectile, plantID, y, handler));
        }
    }
    
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    public boolean isActive()
    {
        return active;
    }

} 

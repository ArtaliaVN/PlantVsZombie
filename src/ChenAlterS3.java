import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ChenAlterS3 extends Plants {
    private boolean active;
    public ChenAlterS3(int x, int y, ID id, int hp, Handler handler){
        super(x, y, id, y, handler);
    }

    @Override
    public void tick(){
        collisionCheck();
        if(isActive() == true){
            x += 2;
            if(x >= Game.WIDTH - 40)
            {
                handler.removePObject(this);
            }
            collision();
        }
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y,150, 100);
    }

    @Override
    public  void render(Graphics g){
        g.drawImage(lawnmower, this.x, this.y, null);
    }

    private boolean collisionCheck(){
        boolean check = false;
        for (int i = 0; i < handler.ZList.size(); i++){
            GameObject temp = handler.ZList.get(i);
            if (getBounds().intersects(temp.getBounds())){
                setActive(true);
            }
        }
        return check;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    public boolean isActive()
    {
        return active;
    }

    @Override
    public void collision(){
        for (int i = 0; i < handler.ZList.size(); i++){
            GameObject temp = handler.ZList.get(i);
            if ( this.getBounds().intersects( temp.getBounds())){
                temp.setHP(0);
            }
        }
    }
}
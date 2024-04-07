import java.awt.*;

public class Zombies extends GameObject {

    private Handler handler;
    private StopWatch clock = new StopWatch();
    protected double moveSpeed;
    private String status = "";
    private double duration;

    public Zombies(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP);
        this.HP = 5;
        this.handler = handler;
    }

    private boolean collision(){
        for (int i = 0; i < handler.PList.size(); i++){
            GameObject temp = handler.PList.get(i);
            if (getBounds().intersects(temp.getBounds())){
                return true;
            }   
        }
        return false;
    }

    public void getSlow(int duration){
        status = "SLOW";
        this.duration = duration + clock.getElapsedTimeInSeconds();
        moveSpeed = velX/2.0;
    }

    @Override
    public void tick() {
        if(!collision()){
            if ( !status.equals("") ){
                if (status.equals("SLOW")){
                    if (duration < clock.getElapsedTimeInSeconds()) moveSpeed = velX;
                }
            }
            x -= moveSpeed;
        }

        if (x <= 0 || this.HP <= 0){
            handler.removeZObject(this);
        }
        collision();
    }

    @Override
    public void render(Graphics g) {
        if(!collision()){
            g.drawImage(animation[3][aniIndex], this.x-50, this.y-50, 100, 100, null);
            updateAnimationTick(3);
        }
        else{
            g.drawImage(animation[4][aniIndex], this.x-50, this.y-50, 100, 100, null);
            updateAnimationTick(4);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
    
}

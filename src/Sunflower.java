import java.awt.*;
import java.util.Random;

public class Sunflower extends Plants{
    
    private int flower = 0;
    private Random r = new Random();
    private SunSpawn sunSpawn;

    public Sunflower(int x, int y, ID id, int HP, Handler handler, SunSpawn sunSpawn) {
        super(x, y, id, HP, handler);
        this.sunSpawn = sunSpawn;
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) >= 3.0){
            lastTime = currentTime;
            sunSpawn.addSun(this.getX() - r.nextInt(20), this.getY() + r.nextInt(15), ID.Sun ,1 ,handler);
        }
        super.collision();
    }

    public int getFlower(){
        return flower;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(animation[2][aniIndex], this.x-30, this.y-30, 100, 100, null);
        updateAnimationTick(2);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 30, 30);
    }

}
import java.awt.*;
import java.util.Random;

public class Sun extends GameObject{

    private Handler sun;
    Random r = new Random();
    private int stopPoint = r.nextInt(720) + 70;
    private double lifeTime = 8.0;
    private StopWatch timer = new StopWatch();

    public Sun(int x, int y, ID id, int HP, Handler sun) {
        super(x, y, id, HP);
        this.sun = sun;
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if (y < stopPoint) y += 2;
        if (lifeTime - timer.getElapsedTimeInSeconds() <= 0) sun.removeSObject(this);
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(sunFall, this.x, this.y, null);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(this.x, this.y, 20, 20);
    }
    
}

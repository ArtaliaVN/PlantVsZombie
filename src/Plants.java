import java.awt.*;
import java.util.Random;

public class Plants extends GameObject {

    Random r = new Random();
    StopWatch timer = new StopWatch();
    double lastTime = timer.getElapsedTimeInSeconds();
    Projectile shoot;
    Handler handler;
    protected int constant;
    private double immuneTime = timer.getElapsedTimeInSeconds();
    private boolean getHit = true;

    public Plants(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP);
        this.handler = handler;
        shoot = new Projectile(this.x, this.y, ID.Protectile, this.id, 0, handler);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
        
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) >= 1.0){
            lastTime = currentTime;
            shoot.shoot(this.x, this.y, this.getID(), this);
        }
        collision();

        if (this.HP == 0) handler.removePObject(this);
    }

    public void collision(){

        for (int i = 0; i < handler.ZList.size(); i++){

            GameObject temp = handler.ZList.get(i);
            if (getBounds().intersects(temp.getBounds())){
                if (getHit){
                    HP--;
                    System.out.println("Ouch!");
                    if (this.HP == 0){
                        handler.removePObject(this);
                    }
                    getHit = false;
                    immuneTime = timer.getElapsedTimeInSeconds();
                }
                else{
                    double waitTime = timer.getElapsedTimeInSeconds();
                    if ( roundAvoid(waitTime - immuneTime, 1) >= 0.5 ){
                        getHit = true;
                    }
                }
            }
        }

    }

    public void setConstant(int constant){
        this.constant = constant;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
            g.drawImage(animation[constant][aniIndex], this.x-30, this.y-30, 100, 100, null);
            updateAnimationTick(constant);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 100, 100);
    }
    
}

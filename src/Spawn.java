import java.util.Random;

public class Spawn {
    
    StopWatch timer = new StopWatch();
    Random r = new Random();
    private Handler handler;
    private HUD hud;
    private double lastTime = timer.getElapsedTimeInSeconds(), immuneTime = timer.getElapsedTimeInSeconds();
    private SoundEffect zombieEffect;
    private double countTime =3;

    public Spawn (Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        zombieEffect = new SoundEffect("OOP-Project (1)\\OOP-Project\\PlantsVsZombies\\src\\zombies.wav");
        
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick(){
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) >0){
            if (roundAvoid(currentTime - lastTime, 1) == countTime){
                zombieEffect.play();   
            }
            if (roundAvoid(currentTime - lastTime, 1) % (countTime *2 ) == 0){
                handler.addZObject(new BasicZombie(Game.WIDTH - 42,r.nextInt(5)*172 + 86, ID.Zombies, 5, handler));
            }
            if (roundAvoid(currentTime - lastTime, 1) == 15.0){
                zombieEffect.play();   
            }
            if (roundAvoid(currentTime - lastTime, 1) % 20.0 ==0){
                handler.addZObject(new BasicZombie(Game.WIDTH - 42,r.nextInt(5)*172 + 86, ID.Zombies, 5, handler));
                handler.addZObject(new BasicZombie(Game.WIDTH - 42,r.nextInt(5)*172 + 86, ID.Zombies, 5, handler));
                lastTime = currentTime;
            }
        }
    }
    public void setCountTime(double time){
        countTime = time;
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
    }
}

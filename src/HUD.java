import java.awt.*;

public class HUD {
    private Game game;
    private SunSpawn sunSpawn;
    private double time=0;
    StopWatch timer = new StopWatch();
    double lastTime = timer.getElapsedTimeInSeconds();

    public void setSunSpawn(SunSpawn sunSpawn){
        this.sunSpawn = sunSpawn;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    private void countDownTime() {
        double currentTime = timer.getElapsedTimeInSeconds();

        if(game.gameState==STATE.GAME){
            
            if (roundAvoid(currentTime - lastTime, 1) >= 1){
                lastTime=currentTime;
                time=roundAvoid(time+1,1);
            }
        }
    }
    
    public void tick(){
        sunSpawn.tick();
        countDownTime();
    }

    public void render(Graphics g){
        if(time < 20){
            g.setColor(Color.green);
            g.fillRect(game.WIDTH /2 -300, 35, (int) time*10, 20);
        }
        if(time<40 && time>=20){
            g.setColor(Color.blue);
            g.fillRect(game.WIDTH /2 -300, 35, (int) time*10, 20);
        }
        if(time<=60 && time>=40){
            g.setColor(Color.red);
            g.fillRect(game.WIDTH /2 -300, 35, (int) time*10, 20);
        }

        g.setColor(Color.BLACK);
        g.drawRect(game.WIDTH /2 -300, 35, 600, 20);
        sunSpawn.render(g);;
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
        time =0;
        sunSpawn.setTime();
    }
    public double getTime(){
        return time;
    }

    public SunSpawn getSunSpawn(){
        return this.sunSpawn;
    }
}

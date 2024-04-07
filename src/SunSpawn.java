import java.util.Random;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SunSpawn extends MouseAdapter{

    private Random r = new Random();
    private StopWatch timer = new StopWatch();
    private double lastTime = timer.getElapsedTimeInSeconds();
    private Handler sun;
    private PlantSelector plantSelector;
    private SoundEffect sunPickup;


    public SunSpawn(PlantSelector plantSelector, Handler sun){
        this.plantSelector = plantSelector;
        this.sun = sun;
        sunPickup = new SoundEffect("OOP-Project (1)\\OOP-Project\\PlantsVsZombies\\src\\ka-ching.wav");
    }

    public void addSun(int x, int y, ID id ,int HP, Handler sun){
        sun.addSObject(new Sun(x, y, id, 1, this.sun));
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick(){
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) >= 2.0){
            lastTime = currentTime;
            sun.addSObject(new Sun(r.nextInt(1140) + 200, 0, ID.Sun, 1, sun));
        }
        sun.tick();
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
    }

    public void render(Graphics g){
        sun.render(g);
    }

    public void mouseMoved(MouseEvent e){
        for (int i = 0; i < this.sun.SList.size(); i++){
            int sunX = this.sun.SList.get(i).getX();
            int sunY = this.sun.SList.get(i).getY();
            if (e.getX() > sunX && e.getX() < sunX + 20){
                if (e.getY() > sunY && e.getY() < sunY +20){
                    sunPickup.play();
                    this.sun.SList.remove(i);
                    plantSelector.setCountSun(25);
                }
            }
        }
    }
}

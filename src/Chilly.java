import java.awt.*;
import java.util.Random;

public class Chilly extends Plants {

    public Chilly(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP, handler);
    }

    @Override
    public void tick() {

        collision();
        if (this.HP == 0) handler.removePObject(this);
    }

    @Override
    public void collision(){
        double currentTime = timer.getElapsedTimeInSeconds();
        if ( roundAvoid(currentTime - lastTime, 1) >= 2 ){
            for (int i = 0; i < handler.ZList.size(); i++){
                GameObject temp = handler.ZList.get(i);
                if (temp.getY() == this.getY()){
                    temp.setHP(0);
                }
            }
            this.HP = 0;
        }

     }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if(this.HP!=0){
            g.drawImage(animation[7][aniIndex], this.x-30, this.y-30,100,100, null);
            updateAnimationTick(7);
        }
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 32, 32);
    }
}


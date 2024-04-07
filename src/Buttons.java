import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;

public class Buttons extends MouseAdapter {

    public Buttons(){
    }

    public void mousePressed (MouseEvent e){
        int myX = e.getX();
        int myY = e.getY();
        if(Game.gameState == STATE.GAME){
            if (mouseOver(myX, myY, Game.WIDTH - 100, Game.HEIGHT/10 - 60, 60, 60)){
                Game.gameState = STATE.PAUSED;
            }

        }

    }

    public void mouseReleased (MouseEvent e){

    }

    private boolean mouseOver (int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            if (my > y && my < y + height){
                return true;
            }
        }
        return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(Game.gameState == STATE.GAME){
            g.setColor(Color.white);

            g.drawRect(Game.WIDTH - 100, Game.HEIGHT/10 - 60, 60, 60);
            g.drawString("Paused", Game.WIDTH - 100, Game.HEIGHT/10 - 60);


        }
    }

}

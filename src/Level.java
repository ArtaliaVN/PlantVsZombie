import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;

public class Level extends MouseAdapter {
    private Handler handler;
    private Spawn spawn;
    private HUD hud;
    private PlantSelector selector;


    public Level (Handler handler, Spawn spawn, HUD hud, PlantSelector selector){
        this.handler = handler;
        this.spawn = spawn;
        this.hud = hud;
        this.selector = selector;
    }

    public void mousePressed (MouseEvent e){
        int myX = e.getX();
        int myY = e.getY();
        
        if(Game.gameState == STATE.LEVEL){
            if (mouseOver(myX, myY, Game.WIDTH/2 - 425, 200, 150, 150)){
                Game.gameState = STATE.GAME;
    
                spawn.setTime();
                hud.setTime();
                selector.setTime();
                handler.clear();
                
                PlantSelector.countSun =200;
                handler.addPObject(new ChenAlterS3(100,56,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,228,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,400,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,572,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,754,ID.ChenAlterS3, 1000, handler));
            }
    
            else if (mouseOver(myX, myY, Game.WIDTH/2 + 300, 290, 150, 150)){
                Game.gameState = STATE.GAME;
    
                spawn.setTime();
                hud.setTime();
                selector.setTime();
                handler.clear();

                spawn.setCountTime(2);
                PlantSelector.countSun =200;
                handler.addPObject(new ChenAlterS3(100,56,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,228,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,400,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,572,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,754,ID.ChenAlterS3, 1000, handler));
                
            }
            else if (mouseOver(myX, myY, Game.WIDTH/2 - 125, 550, 150, 150)){
                Game.gameState = STATE.GAME;
    
                spawn.setTime();
                hud.setTime();
                selector.setTime();
                handler.clear();

                spawn.setCountTime(1);
                PlantSelector.countSun =200;
                handler.addPObject(new ChenAlterS3(100,56,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,228,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,400,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,572,ID.ChenAlterS3, 1000, handler));
                handler.addPObject(new ChenAlterS3(100,754,ID.ChenAlterS3, 1000, handler));
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
        g.setColor(Color.white);
        Font mainFont = new Font("Times New Roman", Font.BOLD, 84);
        g.setFont(mainFont);
        g.drawString("Levels", Game.WIDTH/2 - 130, 150);
        g.drawRect(Game.WIDTH/2 - 425, 200, 150, 150);
        g.drawString("1", Game.WIDTH/2 - 350, 300);

        g.drawRect(Game.WIDTH/2 + 300, 290, 150, 150);
        g.drawString("2", Game.WIDTH/2 + 350, 390);

        g.drawRect(Game.WIDTH/2 - 125, 550, 150, 150);
        g.drawString("3", Game.WIDTH/2 - 60, 670);
    }

}
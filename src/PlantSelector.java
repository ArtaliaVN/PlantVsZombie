

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.MouseAdapter;
import java.awt.*;

import javax.imageio.ImageIO;

public class PlantSelector extends MouseAdapter {
    StopWatch timer = new StopWatch();
    private double lastTime = timer.getElapsedTimeInSeconds();
    private ID[] selectedPlant = new ID[7];
    private Handler handler;
    private ID nextPlant;
    private int nextSlot, nextSunCost;
    private int[] slotPlanted = new int[]{0, 0, 0, 0, 0, 0, 0};
    private int[][] checkEmpty = new int[10][6];
    public static int countSun=200;
    private double[] cooldownPlant = new double[]{0,0,0,0,0,0,0};
    private double time=0;
    private boolean valid = false;
    private HUD hud;
    private BufferedImage card;
    private BufferedImage selectCard[];

    public PlantSelector(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        selectedPlant = new ID[]{ID.Sunflower, ID.Peashooter, ID.SlowPeashooter, ID.Chilly, ID.Peashooter, ID.Peashooter, ID.Peashooter};
        importImg();
        loadAnimation();
    }

    private void countDownTime() {
        double currentTime = timer.getElapsedTimeInSeconds();

        if(Game.gameState==STATE.GAME){
            
            if (roundAvoid(currentTime - lastTime, 1) >= 1){
                lastTime=currentTime;
                time=roundAvoid(time+1,1);

                if(cooldownPlant[0]>0 && slotPlanted[0] != 0){
                    cooldownPlant[0] = roundAvoid(cooldownPlant[0] - 1, 1);
                }

                if( cooldownPlant[1]>0 && slotPlanted[1] != 0){
                    cooldownPlant[1] = roundAvoid(cooldownPlant[1] - 1, 1);
                }

                if(cooldownPlant[2]>0 && slotPlanted[2] != 0){
                    cooldownPlant[2] = roundAvoid(cooldownPlant[2] - 1, 1);
                }

                if(cooldownPlant[3]>0 && slotPlanted[3] != 0){
                    cooldownPlant[3] = roundAvoid(cooldownPlant[3] - 1, 1);
                }

                if(cooldownPlant[4]>0 && slotPlanted[4] != 0){
                    cooldownPlant[4] = roundAvoid(cooldownPlant[4] - 1, 1);
                }

                if(cooldownPlant[5]>0 && slotPlanted[5] != 0){
                    cooldownPlant[5] = roundAvoid(cooldownPlant[5] - 1, 1);
                }

                if(cooldownPlant[6]>0 && slotPlanted[6] != 0){
                    cooldownPlant[6] = roundAvoid(cooldownPlant[6] - 1, 1);
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        if (Game.gameState == STATE.GAME) {
            int mx = e.getX();
            int my = e.getY();

            if (mouseOver(mx, my, 0, 0, 180, 120) && countSun>=50 && cooldownPlant[0] ==0) {
                nextPlant = selectedPlant[0];
                nextSlot = 0;
                slotPlanted[0] = 1;
                nextSunCost = 50;
            } else if (mouseOver(mx, my, 0, 120, 180, 120) && countSun>=100 && cooldownPlant[1] ==0) {
                nextPlant = selectedPlant[1];
                nextSlot = 1;
                slotPlanted[1] = 1;
                nextSunCost =100;

            } else if (mouseOver(mx, my, 0, 120 * 2, 180, 120) && countSun>=125 && cooldownPlant[2] ==0) {
                nextPlant = selectedPlant[2];
                nextSlot = 2;
                slotPlanted[2] = 1;
                nextSunCost =125;

            } else if (mouseOver(mx, my, 0, 120 * 3, 180, 120) && countSun>=75 && cooldownPlant[3] ==0) {
                nextPlant = selectedPlant[3];
                nextSlot = 3;
                slotPlanted[3] = 1;
                nextSunCost =75;
                
            } else if (mouseOver(mx, my, 0, 120 * 4, 180, 120) && countSun>=100 && cooldownPlant[4] ==0) {
                nextPlant = selectedPlant[4];
                nextSlot = 1;
                slotPlanted[4] = 1;
                nextSunCost =100;

            } else if (mouseOver(mx, my, 0, 120 * 5, 180, 120) && countSun>=100 && cooldownPlant[5] ==0) {
                nextPlant = selectedPlant[5];
                nextSlot = 5;
                slotPlanted[5] = 1;
                nextSunCost =100;
            } else if (mouseOver(mx, my, 0, 120 * 6, 180, 120) && countSun>=100 && cooldownPlant[5] ==0) {
                nextPlant = selectedPlant[6];
                nextSlot = 6;
                slotPlanted[6] = 1;
                nextSunCost =100;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (Game.gameState == STATE.GAME) {
            int mx = e.getX();
            int my = e.getY();

            int hereX = 0, hereY = 0;
            int thisI = 0, thisJ = 0;


            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < 9; i++) {
                    if (mouseOver(mx, my, 180 + i * 140, 172 * j, 140, 172)) {
                        hereX = 180 + i * 140 + 70;
                        hereY = 172 * j + 86;
                        thisI = i;
                        thisJ = j;
                    }
                }
            }

            valid = false;

            if (nextPlant != null && checkEmpty[thisI][thisJ] == 0 && hereX + hereY != 0) {
                if(nextPlant == ID.Sunflower){
                    handler.addPObject(new Sunflower(hereX, hereY, ID.Sunflower,5, handler, hud.getSunSpawn()));
                    valid = true;
                    System.out.println("sun");
                }
                else if(nextPlant == ID.Peashooter){
                    handler.addPObject(new Peashooter(hereX,hereY,ID.Peashooter,5,handler));
                    valid = true;
                    System.out.println("pea");
                }
                else if(nextPlant == ID.SlowPeashooter){
                    handler.addPObject(new SlowPeashooter(hereX,hereY,ID.SlowPeashooter,5,handler));
                    valid = true;
                    System.out.println("plants");
                }
                else if(nextPlant == ID.Chilly){
                    handler.addPObject(new Chilly(hereX,hereY,ID.Chilly,5,handler));
                    valid = true;
                    System.out.println("chilly");
                }

                nextPlant = null;
                if (valid == true){
                    countSun -= nextSunCost;
                    cooldownPlant[nextSlot] =5.0;
                    nextSlot = -1;
                    nextSunCost = 0;
                }
                else{
                    nextSunCost = 0;
                    nextSlot = -1;
                }
                checkEmpty[thisI][thisJ] = 1;
            }
        }
    }

    public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            }
        }
        return false;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick() {
        countDownTime();
    }

    public void render(Graphics g) {


        Font mainFont = new Font("Times New Roman", Font.BOLD, 32);
        g.setFont(mainFont);
        g.setColor(Color.white);
        g.drawString("Sun: "+countSun,185,30);
        g.drawRect(180, 40, 150, 1);
//        sunflower
        if((countSun>=50) && (cooldownPlant[0]==0)){
            g.drawImage(selectCard[0], 0, 0, null);
        }else{
            g.drawString(cooldownPlant[0]+"s",0,120);
        }

//      Peashooter
        if((countSun>=100) && (cooldownPlant[1]==0)){
            g.drawImage(selectCard[1], 0, 120, null);
        }else{
            g.drawString(cooldownPlant[1]+"s",0,240);
        }
//      SlowPeashooter
        if((countSun>=125) && (cooldownPlant[2]==0)){
            g.drawImage(selectCard[2], 0, 240, null);
        }else{
            g.drawString(cooldownPlant[2]+"s",0,360);
        }
//      Chilly
        if((countSun>=75) && (cooldownPlant[3]==0)){
            g.drawImage(selectCard[3], 0, 360, null);
        }else{
            g.drawString(cooldownPlant[3]+"s",0,480);
        }
//      border
        g.setColor(Color.white);

        g.drawRect(0, 0, 180, 120);

        g.drawRect(0, 120, 180, 120);

        g.drawRect(0, 120 * 2, 180, 120);

        g.drawRect(0, 120 * 3, 180, 120);

        g.drawRect(0, 120 * 4, 180, 120);

        g.drawRect(0, 120 * 5, 180, 120);

        g.drawRect(0, 120 * 6, 180, 120);
    }

    public void setTime(){
        this.lastTime = timer.getElapsedTimeInSeconds();
    }

    public void setCountSun(int sun){
        this.countSun += sun;
    }
    protected void importImg(){
        InputStream PlantSelect = getClass().getResourceAsStream("/PlantSelect.png");

        try {
            card = ImageIO.read(PlantSelect);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                PlantSelect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void loadAnimation(){
        selectCard = new BufferedImage[4];
        for(int j = 0; j < selectCard.length; j++){
            selectCard[j] = card.getSubimage(j*180, 0 ,180,120);
        }
    }
}
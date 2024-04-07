import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 1540, HEIGHT = 860;

    private Thread thread;
    private boolean running = false;

    public HUD hp;
    private Handler handler;
    private SunSpawn sunSpawn;
    private Spawn spawner;
    private Menu menu;
    private Level level;
    private Paused paused;
    private Music music;
    private PlantSelector selector;
    private Buttons button;
    
    private BufferedImage background;
    private BufferedImage menuBG;
    private BufferedImage levelBG;
    private BufferedImage winnerBG;

    private SoundEffect mainSound;

    

    public static STATE gameState = STATE.MENU;
    
    public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        importBgImg();
        handler = new Handler();
        hp = new HUD();
        button = new Buttons();
        selector = new PlantSelector(handler, hp);
        sunSpawn = new SunSpawn(selector, handler);

        hp.setSunSpawn(sunSpawn);
        spawner = new Spawn(handler, hp);
        level = new Level(handler, spawner, hp, selector);
        menu = new Menu(this);
        paused = new Paused(this);

        //mainSound = new SoundEffect("OOP-Project (1)\\OOP-Project\\PlantsVsZombies\\src\\ArknightsCC10.wav");
        //mainSound.play();
        //music = new Music(this);

        new Window(WIDTH, HEIGHT, "Plants VS Zombies", this);
    }

    public static void main(String[] args) throws Exception{
        new Game();
        
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void playExplosionSound(){
        // explosionSound.play();
    }
    public void stopExplosionSound(){
        // explosionSound.stop();
    }

    protected void importBgImg(){
        InputStream is = getClass().getResourceAsStream("/bgTest.png");
        InputStream is2 = getClass().getResourceAsStream("/menu.png");
        InputStream is3 = getClass().getResourceAsStream("/level.png");
        InputStream is4 = getClass().getResourceAsStream("winner.png");
        try {
            background = ImageIO.read(is);
            menuBG = ImageIO.read(is2);
            levelBG = ImageIO.read(is3);
            winnerBG = ImageIO.read(is4);
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override

    public void run() {
        // TODO Auto-generated method stub
        long lastTime = System.nanoTime();
        double amountOfTicks = 140.0;
        double ns = 1000000000 / amountOfTicks;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            if(now - lastTime >= ns){
                render();
                tick();
                frames++;
                lastTime = now;
            }
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        this.requestFocus();
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();




        if (gameState == STATE.GAME){
            g.drawImage(background, 0, 0, getFocusCycleRootAncestor());
            handler.render(g);
            hp.render(g);
            selector.render(g);
            button.render(g);



            
            this.addMouseListener(button);
            this.addMouseListener(selector);
            this.addMouseMotionListener(sunSpawn);

        }
        else if(gameState == STATE.MENU) {
            g.drawImage(menuBG, 0, 0, getFocusCycleRootAncestor());
            menu.render(g);
            this.addMouseListener(menu);
            
        }
        else if(gameState == STATE.PAUSED){
            paused.render(g);
            this.addMouseListener(paused);
        
        }
        else if(gameState == STATE.LEVEL){
            g.drawImage(levelBG, 0, 0, getFocusCycleRootAncestor());
            level.render(g);
            this.addMouseListener(level);
        }
        else{
            g.drawImage(winnerBG, 0, 0, getFocusCycleRootAncestor());
            paused.render(g);
            this.addMouseListener(paused);
        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int y, int x){
        if (var >= x){
            return var = x;
        }
        else if (var <= y){
            return var = y;
        }
        else return var;
    }

    private void tick() {
        if (hp.getTime() == 60){
            gameState = STATE.WIN;
        }
        if (gameState == STATE.GAME){
            handler.tick();
            hp.tick();
            spawner.tick();
            selector.tick();
        }
    }

}

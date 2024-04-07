public class SlowPeashooter extends Plants {

    public SlowPeashooter(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP, handler);
        constant = 5;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
        
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) >= 1.5){
            lastTime = currentTime;
            shoot.shoot(this.x, this.y, this.getID(), this);
        }
        collision();
        if (this.HP == 0) handler.removePObject(this);
    }


}

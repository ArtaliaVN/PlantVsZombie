
public class BasicZombie extends Zombies {
    public BasicZombie(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP, handler);
        velX = 1.1;
        moveSpeed = velX;
    }
}

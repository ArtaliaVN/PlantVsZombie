import java.util.LinkedList;
import java.awt.*;
import java.security.PublicKey;

public class Handler {

    LinkedList<Plants> PList = new LinkedList<Plants>();
    LinkedList<Zombies> ZList = new LinkedList<Zombies>();
    LinkedList<Sun> SList = new LinkedList<Sun>();
    LinkedList<Projectile> ProList = new LinkedList<Projectile>();

    public void tick(){
        for (int i = 0; i < PList.size(); i++){
            GameObject temp = PList.get(i);
            temp.tick();
        }
        for (int i = 0; i < ZList.size(); i++){
            GameObject temp = ZList.get(i);
            temp.tick();
        }
        for (int i = 0; i < SList.size(); i++){
            GameObject temp = SList.get(i);
            temp.tick();
        }
        for (int i = 0; i < ProList.size(); i++){
            GameObject temp = ProList.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < PList.size(); i++){
            GameObject temp = PList.get(i);
            temp.render(g);
        }

        for(int i = 0; i < ZList.size(); i++){
            GameObject temp = ZList.get(i);
            temp.render(g);
        }
        for(int i = 0; i < SList.size(); i++){
            GameObject temp = SList.get(i);
            temp.render(g);
        }
        for(int i = 0; i < ProList.size(); i++){
            GameObject temp = ProList.get(i);
            temp.render(g);
        }
    }

    public void addPObject(Plants o){
        this.PList.add(o);
    }

    public void removePObject(Plants o){
        this.PList.remove(o);
    }

    public void addZObject(Zombies o){
        this.ZList.add(o);
    }

    public void removeZObject(Zombies o){
        this.ZList.remove(o);
    }

    public void addSObject(Sun o){
        this.SList.add(o);
    }

    public void removeSObject(Sun o){
        this.SList.remove(o);
    }

    public void addProObject(Projectile o){
        this.ProList.add(o);
    }

    public void removeProObject(Projectile o){
        this.ProList.remove(o);
    }

    public void clear(){
        PList.clear();
        ZList.clear();
        SList.clear();
        ProList.clear();
    }
}
    

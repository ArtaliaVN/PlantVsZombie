import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    private Game game;
    public Music(Game game) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.game = game;
        File file = new File("C:\\Users\\vuduc\\.vscode\\PlantsVsZombies\\src\\ArknightsCC10.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}

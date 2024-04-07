import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends Canvas {
    
    public Window(int width, int height, String title, Game game){
        JFrame Frame = new JFrame(title);

        Frame.setPreferredSize(new Dimension(width, height));
        Frame.setMaximumSize(new Dimension(width, height));
        Frame.setMinimumSize(new Dimension(width, height));

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(false);
        Frame.setLocationRelativeTo(null);
        Frame.add(game);
        Frame.setVisible(true);
        game.start();
    }
}

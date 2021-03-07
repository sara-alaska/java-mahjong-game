import javax.swing.*;
import java.awt.*;

/*
Sets up MahJong board with music and background
 */

public class MahJongBoard extends JLayeredPane {

    public MahJongModel tileManager;
    private boolean music = true;
    boolean is_SoundOn = true;
    private static SoundClip sound = new SoundClip();

    public MahJongBoard(){
        Color bg = new Color(112, 139, 172);
        setOpaque(true);
        setBackground(bg);
        playMatchSound(is_SoundOn);
        add(new MahJongTest.TestPanel());
        setVisible(true);
        tileManager = new MahJongModel(this);
    }

    protected void playMatchSound(boolean is_SoundOn){
        if (is_SoundOn){
            sound.startSound();
        }
    }

    public void newGame()
    {
        tileManager.newGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = new ImageIcon(getClass().getResource("images/"+"dragon_bg"+".png")).getImage();
        super.paintComponent(g);
        g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);

    }
}
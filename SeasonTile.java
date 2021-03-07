import javax.swing.*;
import java.awt.*;

public class SeasonTile extends PictureTile{

    public SeasonTile(String name) {
        super(name);
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Season Tiles");
        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));
        frame.pack();
        frame.setVisible(true);
    }
}
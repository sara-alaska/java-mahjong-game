import javax.swing.*;
import java.awt.*;

/*
Defines and adds Flower tiles
*/

public class FlowerTile extends PictureTile {
    public FlowerTile(String name) {
        super(name);
    }
    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Flower Tiles");
        frame.add(new FlowerTile("Chrysanthemum"));
        frame.add(new FlowerTile("Orchid"));
        frame.add(new FlowerTile("Plum"));
        frame.add(new FlowerTile("Bamboo"));
        frame.pack();
        frame.setVisible(true);
    }
}

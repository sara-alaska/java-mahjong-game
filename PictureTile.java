import java.awt.*;

import javax.swing.*;

abstract public class PictureTile extends Tile{
    private String name;

    public PictureTile(String name) {
        this.name = name;
        setToolTipText(toString());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon image = new ImageIcon(getClass().getResource("images/" + name + ".png"));
        super.paintComponent(g);

        g.drawImage(image.getImage(), 15, 0, this);
    }

    @Override
    public String toString() {
        return "Picture " + name;
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Picture Tiles");
        frame.add(new Bamboo1Tile());
        frame.add(new FlowerTile("Chrysanthemum"));
        frame.add(new FlowerTile("Orchid"));
        frame.add(new FlowerTile("Plum"));
        frame.add(new FlowerTile("Bamboo"));
        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));
        frame.pack();
        frame.setVisible(true);
    }
}

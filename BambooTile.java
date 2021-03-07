import java.awt.*;
import javax.swing.*;
/*
Draws bamboo tiles using drawImage function
*/

public class BambooTile extends RankTile {
    public BambooTile(int rank){
        super(rank);
    }
    public String toString(){
        return "Bamboo " + rank;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon image = new ImageIcon(getClass().getResource("images/Bamboo2.png"));
        ImageIcon image3 = new ImageIcon(getClass().getResource("images/Bamboo3.png"));
        ImageIcon image4 = new ImageIcon(getClass().getResource("images/Bamboo4.png"));
        ImageIcon image5 = new ImageIcon(getClass().getResource("images/Bamboo5.png"));
        ImageIcon image6 = new ImageIcon(getClass().getResource("images/Bamboo6.png"));
        ImageIcon image7 = new ImageIcon(getClass().getResource("images/Bamboo7.png"));
        ImageIcon image8 = new ImageIcon(getClass().getResource("images/Bamboo8.png"));
        ImageIcon image9 = new ImageIcon(getClass().getResource("images/Bamboo9.png"));
        super.paintComponent(g);
        setToolTipText(toString());
        switch(rank){
            case 2:
                paintBambooStick(image);
                g.drawImage(image.getImage(), 25, 20, this);
                break;
            case 3:
                paintBambooStick(image3);
                g.drawImage(image3.getImage(), 35, 20, this);
                break;
            case 4:
                paintBambooStick(image4);
                g.drawImage(image4.getImage(), 20, 0, this);
                break;
            case 5:
                paintBambooStick(image5);
                g.drawImage(image5.getImage(), 15, 0, this);
                break;
            case 6:
                paintBambooStick(image6);
                g.drawImage(image6.getImage(), 20, 0, this);
                break;
            case 7:
                paintBambooStick(image7);
                g.drawImage(image7.getImage(), 15, 0, this);
                break;
            case 8:
                paintBambooStick(image8);
                g.drawImage(image8.getImage(), 15, 2, this);
                break;
            case 9:
                paintBambooStick(image9);
                g.drawImage(image9.getImage(), 15, 2, this);
                break;
        }
    }

    private ImageIcon paintBambooStick(ImageIcon img){
        return img;
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bamboo Tiles");

        frame.add(new BambooTile(2));
        frame.add(new BambooTile(3));
        frame.add(new BambooTile(4));
        frame.add(new BambooTile(5));
        frame.add(new BambooTile(6));
        frame.add(new BambooTile(7));
        frame.add(new BambooTile(8));
        frame.add(new BambooTile(9));

        frame.pack();
        frame.setVisible(true);
    }
}
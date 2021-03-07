import java.awt.*;
import javax.swing.*;

public class WhiteDragonTile extends Tile
{
    public WhiteDragonTile()
    {

        setToolTipText(toString());
    }
    public String toString()
    {

        return "White Dragon";
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        g2.fillRect(37, 27, 8, 5);
        g2.setColor(Color.WHITE);
        g2.fillRect(45, 27, 8, 5);
        g2.setColor(Color.BLUE);
        g2.fillRect(53, 27, 8, 5);
        g2.setColor(Color.WHITE);
        g2.fillRect(61, 27, 8, 5);
        g2.setColor(Color.BLUE);
        g2.fillRect(69, 27, 8, 5);
        g2.setColor(Color.WHITE);
        g2.fillRect(77, 27, 8, 5);
        g2.setColor(Color.BLUE);
        g2.fillRect(80, 32, 5,8);
        g2.setColor(Color.WHITE);
        g2.fillRect(80, 41, 5, 8);
        g2.setColor(Color.BLUE);
        g2.fillRect(80, 48, 5, 8);
        g2.setColor(Color.WHITE);
        g2.fillRect(80, 55, 5, 8);
        g2.setColor(Color.BLUE);
        g2.fillRect(80, 62, 5, 8);
        g2.setColor(Color.WHITE);
        g2.fillRect(77, 70, 8, 5);//
        g2.setColor(Color.BLUE);
        g2.fillRect(69, 70, 8, 5);
        g2.setColor(Color.WHITE);
        g2.fillRect(61, 70, 8, 5);
        g2.setColor(Color.BLUE);
        g2.fillRect(53, 70, 8, 5);
        g2.setColor(Color.WHITE);
        g2.fillRect(45, 70, 8, 5);
        g2.setColor(Color.BLUE);
        g2.fillRect(37, 70, 8, 5);
        g2.setColor(Color.WHITE);
        g2.fillRect(37, 62, 5, 8);//Left Side Corner
        g2.setColor(Color.BLUE);
        g2.fillRect(37, 55, 5, 8);
        g2.setColor(Color.WHITE);
        g2.fillRect(37, 47, 5, 8);
        g2.setColor(Color.BLUE);
        g2.fillRect(37, 39, 5, 8);
        g2.setColor(Color.WHITE);
        g2.fillRect(37, 32, 5, 8);
        g2.setColor(Color.BLUE);
        g2.drawRect(37, 27, 48, 48);
        g2.setColor(Color.BLUE);
        g2.drawRect(42, 32, 37, 37);
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("White Dragon Tile");

        frame.add(new WhiteDragonTile());

        frame.pack();
        frame.setVisible(true);
    }
}




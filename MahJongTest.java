import javax.swing.*;

public class MahJongTest extends JFrame
{
    public MahJongTest()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new TestPanel());

        setSize(500, 500);
        setVisible(true);
    }

    public static class TestPanel extends JPanel
    {
        public TestPanel()
        {
            setLayout(null);		// requires a setSize in Tile!!!!!!!!!!!

            Tile t;

            t = new SeasonTile("Spring");
            t.setLocation(100, 100);
            add(t);
            //System.out.println(getComponentZOrder(t));

            t = new SeasonTile("Summer");
            t.setLocation(300, 200);
            add(t);
            //System.out.println(getComponentZOrder(t));

            t = new SeasonTile("Fall");
            t.setLocation(400, 300);
            add(t);
            //System.out.println(getComponentZOrder(t));

            t = new SeasonTile("Winter");
            t.setLocation(500, 400);
            add(t);
            //System.out.println(getComponentZOrder(t));
        }
    }

    public static void main(String[] args)
    {
        new MahJongTest();
    }
}

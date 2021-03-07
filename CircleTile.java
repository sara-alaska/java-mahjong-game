import java.awt.*;
import javax.swing.*;
/*
Draws circle tiles using draw function
*/

public class CircleTile extends RankTile
{
    private Circle[] circles;
    Color GREEN = new Color(102, 164, 99);
    private static  int PANCAKE_RADIUS = 30;
    private static int LARGE_RADIUS = 15;
    private static int MEDIUM_RADIUS = 12;
    private static int SMALL_RADIUS = 8;
    private static int tileSize = 80;

    public CircleTile(int rank)
    {
        super(rank);
        setToolTipText(toString());
        circles = new Circle[rank];

        switch(rank)
        {
            case 1:
                circles[0] = new Pancake(25, (int)(0.25 * tileSize), PANCAKE_RADIUS, Color.RED);
                break;
            case 2:
                circles[0] = new Circle(63 - LARGE_RADIUS - 5, (int)(0.15 * tileSize), LARGE_RADIUS, GREEN);
                circles[1] = new Circle(63 - LARGE_RADIUS - 5, (int)(0.65 * tileSize), LARGE_RADIUS, Color.RED);

                break;
            case 3:
                circles[0] = new Circle(22, 20, MEDIUM_RADIUS, Color.BLUE);
                circles[1] = new Circle(42, 40, MEDIUM_RADIUS, Color.RED);
                circles[2] = new Circle(62, 60, MEDIUM_RADIUS, GREEN);
                break;
            case 4:
                circles[0] = new Circle(40 - LARGE_RADIUS, (int)(0.20 * tileSize), LARGE_RADIUS, Color.BLUE);
                circles[1] = new Circle(40 - LARGE_RADIUS, (int)(0.65 * tileSize), LARGE_RADIUS, GREEN);
                circles[2] = new Circle(40 + LARGE_RADIUS, (int)(0.20 * tileSize), LARGE_RADIUS, GREEN);
                circles[3] = new Circle(40 + LARGE_RADIUS, (int)(0.65 * tileSize), LARGE_RADIUS, Color.BLUE);
                break;
            case 5:
                circles[0] = new Circle(40 - MEDIUM_RADIUS - 5 , (int)(0.10 * tileSize), MEDIUM_RADIUS, Color.BLUE);
                circles[1] = new Circle(40 - MEDIUM_RADIUS - 5, (int)(0.75 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[2] = new Circle(50 - 5, (int)(0.45 * tileSize), MEDIUM_RADIUS, Color.RED);
                circles[3] = new Circle(70 - 5, (int)(0.10 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[4] = new Circle(70 - 5, 60, MEDIUM_RADIUS, Color.BLUE);
                break;
            case 6:
                circles[0] = new Circle(35 - MEDIUM_RADIUS, (int)(0.10 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[1] = new Circle(75 - MEDIUM_RADIUS, (int)(0.10 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[2] = new Circle(35 - MEDIUM_RADIUS, (int)(0.47 * tileSize), MEDIUM_RADIUS, Color.RED);
                circles[3] = new Circle(35 - MEDIUM_RADIUS, (int)(0.79 * tileSize), MEDIUM_RADIUS, Color.RED);
                circles[4] = new Circle(75 - MEDIUM_RADIUS, (int)(0.47 * tileSize), MEDIUM_RADIUS, Color.RED);
                circles[5] = new Circle(75 - MEDIUM_RADIUS, (int)(0.79 * tileSize), MEDIUM_RADIUS, Color.RED);
                break;
            case 7:
                circles[0] = new Circle(32, 18, SMALL_RADIUS, GREEN);
                circles[1] = new Circle(54, 22, SMALL_RADIUS, GREEN);
                circles[2] = new Circle(75, 26, SMALL_RADIUS, GREEN);
                circles[3] = new Circle(35, 45, SMALL_RADIUS, Color.RED);
                circles[4] = new Circle(65, 45, SMALL_RADIUS, Color.RED);
                circles[5] = new Circle(35, 65, SMALL_RADIUS, Color.RED);
                circles[6] = new Circle(65, 65, SMALL_RADIUS, Color.RED);
                break;
            case 8:
                circles[0] = new Circle(65, 29, SMALL_RADIUS, Color.BLUE);
                circles[1] = new Circle(65, 11, SMALL_RADIUS, Color.BLUE);
                circles[2] = new Circle(40, 11, SMALL_RADIUS, Color.BLUE);
                circles[3] = new Circle(40, 29, SMALL_RADIUS, Color.BLUE);
                circles[4] = new Circle(40, 47, SMALL_RADIUS, Color.BLUE);
                circles[5] = new Circle(40, 65, SMALL_RADIUS, Color.BLUE);
                circles[6] = new Circle(65, 47, SMALL_RADIUS, Color.BLUE);
                circles[7] = new Circle(65, 65, SMALL_RADIUS, Color.BLUE);
                break;
            case 9:
                circles[0] = new Circle(38 - MEDIUM_RADIUS - 5, (int)(0.15 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[1] = new Circle(62 - MEDIUM_RADIUS - 5, (int)(0.15 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[2] = new Circle(86 - MEDIUM_RADIUS - 5, (int)(0.15 * tileSize), MEDIUM_RADIUS, Color.RED);
                circles[3] = new Circle(38 - MEDIUM_RADIUS - 5, (int)(0.47 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[4] = new Circle(62 - MEDIUM_RADIUS - 5, (int)(0.47 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[5] = new Circle(86 - MEDIUM_RADIUS - 5, (int)(0.47 * tileSize), MEDIUM_RADIUS, Color.RED);
                circles[6] = new Circle(38 - MEDIUM_RADIUS - 5, (int)(0.79 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[7] = new Circle(62 - MEDIUM_RADIUS - 5, (int)(0.79 * tileSize), MEDIUM_RADIUS, GREEN);
                circles[8] = new Circle(86 - MEDIUM_RADIUS - 5, (int)(0.79 * tileSize), MEDIUM_RADIUS, Color.RED);
                break;
            default:
                System.err.println("Invalid tile");
                break;
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Circle c : circles)
            if (c != null)
                c.draw(g);
    }

    @Override public String toString()
    {
        return "Circle " + rank;
    }

    public class Circle
    {
        protected int x;
        protected int y;
        private Color color;
        protected int r; // radius
        protected int cx; // center x
        protected int cy; // center y

        public Circle(int x, int y, int r, Color color)
        {
            this.x = x;
            this.y = y;
            this.color = color;
            this.r = r;
            this.cx = this.x + this.r;
            this.cy = this.y + this.r;
        }

        public void draw(Graphics g)
        {
            g.setColor(color);
            g.fillOval(x,y, r * 2, r * 2);
            g.setColor(Color.WHITE);
            int offsetX = (int) (this.r * Math.sin(Math.PI/4));
            int offsetY = (int) (this.r * Math.cos(Math.PI/4));
            int x1 = this.cx - offsetX;
            int y1 = this.cy - offsetY;
            int x2 = this.cx + offsetX;
            int y2 = this.cy + offsetY;

            g.drawLine(x1,y1, x2, y2); // lines inside of the circles
            g.drawLine(x1, y2, x2, y1);
        }
    }

    //Pancake class
    public class Pancake extends Circle
    {
       public Pancake(int x, int y, int r, Color color) {
           super(x, y, r, color);
       }

        public void draw(Graphics g)
        {
            g.setColor(GREEN);
            g.fillOval(this.x, this.y, this.r * 2, this.r * 2);
            g.setColor(Color.RED);
            int innerRadius = this.r / 3;
            int innerRadiusStroke = innerRadius + 1;

            g.setColor(Color.WHITE);
            g.fillOval(this.cx - innerRadiusStroke, this.cy - innerRadiusStroke, innerRadiusStroke * 2, innerRadiusStroke * 2);
            g.setColor(Color.RED);
            g.fillOval(this.cx - innerRadius, this.cy - innerRadius, innerRadius * 2, innerRadius * 2);
            g.setColor(Color.WHITE);

            int offsetX = (int) (innerRadius * Math.sin(Math.PI/4));
            int offsetY = (int) (innerRadius * Math.cos(Math.PI/4));
            int x1 = this.cx - offsetX;
            int y1 = this.cy - offsetY;
            int x2 = this.cx + offsetX;
            int y2 = this.cy + offsetY;

            g.drawLine(x1,y1, x2, y2);
            g.drawLine(x1, y2, x2, y1);

            int d = (int)(Math.floor(this.r * 0.8)); // distance
            int dotRadius = 2; // radius of the dots around the inner circle
            int dotX;
            int dotY;

            for (float angle = 0; angle < 2 * Math.PI; angle += 2 * Math.PI / 16) {
                dotX = (int)(this.cx + d * Math.cos(angle));
                dotY = (int)(this.cy + d * Math.sin(angle));
                g.fillOval(dotX - dotRadius / 2, dotY - dotRadius / 2, 2 * dotRadius, 2 * dotRadius);
            }
        }
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Circle Tiles");
        frame.add(new CircleTile(1));
        frame.add(new CircleTile(2));
        frame.add(new CircleTile(3));
        frame.add(new CircleTile(4));
        frame.add(new CircleTile(5));
        frame.add(new CircleTile(6));
        frame.add(new CircleTile(7));
        frame.add(new CircleTile(8));
        frame.add(new CircleTile(9));

        frame.pack();
        frame.setVisible(true);
    }
}
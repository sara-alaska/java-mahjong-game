import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Tile extends JPanel {

    protected static Dimension CANVAS;
    private static int RECT_WIDTH = 80;
    private static int RECT_HEIGHT = 100;
    private static int RECT_X = RECT_WIDTH / 5;
    private static int RECT_Y = RECT_X;
    private static int offset = 0;
    private static int BASE_THICKNESS = 8;
    private static Rectangle FACE;
    private static GradientPaint FACE_GRADIENT;
    private static GradientPaint BASE_LEFT_LOWER_GRAD;
    private static GradientPaint BASE_LEFT_UPPER_GRAD;
    private static GradientPaint BASE_BOTTOM_UPPER_GRAD;
    private static GradientPaint BASE_BOTTOM_LOWER_GRAD;
    private static Polygon BASE_LEFT_UPPER;
    private static Polygon BASE_LEFT_LOWER;
    private static Polygon BASE_BOTTOM_UPPER;
    private static Polygon BASE_BOTTOM_LOWER;
    private Color borderColor = Color.BLACK;
    private int strokeWidth = 1;
    private boolean isSelected = false;
    private boolean render = true;
    private boolean isStart = true;

    MahJongModel mahJongModel = null;

        @Override
        public void setLocation(int x,int y){
            if(isStart){
                RECT_X = x;
                RECT_Y = y;
                isStart = false;
            }
            super.setLocation(x,y);
        }

        public void setToStart(){
            setLocation(RECT_X,RECT_Y);
        }

        public void setVisibility(boolean x){
            setVisible(x);
            render = x;
            repaint();
        }

        public boolean isEnabled(){
            return render;
        }

        public void hintHighlight(){
            borderColor = Color.MAGENTA;
            strokeWidth = 5;
            repaint();
        }
        public void highlight(boolean x){
            if(x){
                borderColor = Color.RED;
                strokeWidth = 4;
                isSelected = true;
            }else{
                borderColor = Color.black;
                strokeWidth = 1;
                isSelected =false;
            }
            repaint();
        }

        public Tile(){
            setOpaque(false);
            iSize();
            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                }
            });
            addMouseListener(new MouseAdapter() {
                private Color background;
                private Tile[] tiles = null;

                @Override
                public void mousePressed(MouseEvent e) {
                    background = getBackground();
                    setBackground(Color.RED);
                    repaint();
                    mahJongModel = MahJongModel.getInstance();
                    if(mahJongModel != null){
                        if(e.getSource()!= null){
                            tiles = mahJongModel.setup((Tile) e.getSource());
                        }
                        if(tiles != null){
                            if(mahJongModel.isValidMove((Tile) e.getSource())){
                                if(isSelected){
                                    highlight(false);
                                    mahJongModel.removeSelection((Tile) e.getSource());
                                }else{
                                    highlight(true);
                                    mahJongModel.setSelection((Tile) e.getSource());
                                }
                            }
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setBackground(background);
                }
            });
        }

        private void iSize() {
        CANVAS = new Dimension(RECT_WIDTH + 2 * BASE_THICKNESS, RECT_HEIGHT + 2 * BASE_THICKNESS);
        setSize(CANVAS);
        FACE = new Rectangle(BASE_THICKNESS * 2, 0, 79, 100);

        int[] x = {BASE_THICKNESS * 2, BASE_THICKNESS * 2, BASE_THICKNESS, BASE_THICKNESS};
        int[] y = {0, RECT_HEIGHT, RECT_HEIGHT + BASE_THICKNESS, BASE_THICKNESS};
        BASE_LEFT_UPPER = new Polygon(x, y, 4);

        int[] x1 = {BASE_THICKNESS, BASE_THICKNESS, 0, 0};
        int[] y1 = {BASE_THICKNESS, RECT_HEIGHT + BASE_THICKNESS, CANVAS.height, BASE_THICKNESS * 2};
        BASE_LEFT_LOWER = new Polygon(x1, y1, 4);

        int[] x2 = {BASE_THICKNESS * 2, CANVAS.width, CANVAS.width - BASE_THICKNESS, BASE_THICKNESS};
        int[] y2 = {RECT_HEIGHT, RECT_HEIGHT, RECT_HEIGHT + BASE_THICKNESS, RECT_HEIGHT + BASE_THICKNESS};
        BASE_BOTTOM_UPPER = new Polygon(x2, y2, 4);

        int[] x3 = {BASE_THICKNESS, CANVAS.width - BASE_THICKNESS, RECT_WIDTH, 0};
        int[] y3 = {RECT_HEIGHT + BASE_THICKNESS, RECT_HEIGHT + BASE_THICKNESS, CANVAS.height, CANVAS.height};
        BASE_BOTTOM_LOWER = new Polygon(x3, y3, 4);

        FACE_GRADIENT = new GradientPaint(CANVAS.width, 0, new Color(255, 247, 227), BASE_THICKNESS * 2, RECT_HEIGHT, new Color(251, 240, 219));
        BASE_LEFT_UPPER_GRAD = new GradientPaint(BASE_THICKNESS, 0, new Color(251, 240, 172), BASE_THICKNESS * 2, 0, new Color(245, 238, 188));
        BASE_LEFT_LOWER_GRAD = new GradientPaint(0, 0, new Color(71, 122, 24), BASE_THICKNESS, 0, new Color(135, 201, 39));
        BASE_BOTTOM_UPPER_GRAD = new GradientPaint(0, CANVAS.height - BASE_THICKNESS, new Color(251, 221, 181), 0, RECT_HEIGHT, new Color(245, 238, 188));
        BASE_BOTTOM_LOWER_GRAD = new GradientPaint(0, 0, new Color(113, 168, 24), BASE_THICKNESS, 0, new Color(71, 122, 24));
        setBounds(0,0,(int)(RECT_WIDTH*1.2),(int)(RECT_HEIGHT*1.2));
}

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke b = new BasicStroke(strokeWidth, BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL,10);
        g2.setStroke(b);

        g2.setPaint(FACE_GRADIENT);
        g2.fill(FACE);

        g2.setPaint(BASE_LEFT_UPPER_GRAD);
        g2.fill(BASE_LEFT_UPPER);

        g2.setPaint(BASE_LEFT_LOWER_GRAD);
        g2.fill(BASE_LEFT_LOWER);

        g2.setPaint(BASE_BOTTOM_UPPER_GRAD);
        g2.fill(BASE_BOTTOM_UPPER);

        g2.setPaint(BASE_BOTTOM_LOWER_GRAD);
        g2.fill(BASE_BOTTOM_LOWER);

        g2.setColor(borderColor);
        g2.draw(FACE);
  }

  @Override
  public Dimension getPreferredSize() {
            return new Dimension(RECT_WIDTH + offset * RECT_X, RECT_HEIGHT + offset * RECT_Y);
        }

        public boolean matches(Tile other){
            if(this == other){
                return true;
            }
            if(this == null){
                return false;
            }
            if(this.getClass() != other.getClass()){
                return false;
            }
            return true;
        }
}
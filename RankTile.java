import java.awt.*;

public class RankTile extends Tile {

    protected int rank;
    final static Polygon dot;

    static{
        dot = new Polygon(new int[]{0,5,5,0},new int[]{0,0,5,5},4);
    }

    public RankTile(int rank){
        this.rank = rank;
        setToolTipText(String.valueOf(rank));
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    @Override
    public boolean matches(Tile other){
        if(this == other){
            return true;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        return this.rank == ((RankTile)other).rank;
    }
}

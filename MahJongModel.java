import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MahJongModel {
    Thread thread;
    private int seconds = 0;
    private boolean running = true;
    JFrame topFrame;
    private int tileSize = 80;
    public Fireworks reward;
    public boolean wonGame = false;
    Color bg = new Color(112, 139, 172);
    Tile left = new Tile();
    Tile right1 = new Tile();
    Tile right2 = new Tile();
    Tile layer1 = new Tile();
    Tile[][] layer2 = new Tile[2][2];
    Tile[][] layer3 = new Tile[4][4];
    Tile[][] layer4 = new Tile[6][6];
    Tile[][] layer5 = new Tile[8][12];
    Tile[][][] tileStack;
    SoundClip audio = new SoundClip();
    private boolean showNextValidMove = false;
    private ArrayList<Tile> hints = new ArrayList<>();
    private Tile selectedTile;
    private boolean position = false;
    private ArrayList<Tile[]> removedTilesPairs = new ArrayList<>();
    private ArrayList<Tile[]> redoTilesPairs = new ArrayList<>();
    private Tile[] sideQue = new Tile[8];
    private int globalXOffset = 2;
    private int globalYOffset = tileSize / 2;
    private static MahJongModel mj_model = null;
    private static JLayeredPane board = null;

    private int[][] maskArrayBottom = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    TileDeck deck = new TileDeck();

    private void updateSide() {
        if (removedTilesPairs.size() > 0) {
            int top = 8;
            int x;
            if (removedTilesPairs.size() <= top) {
                x = removedTilesPairs.size();
            } else {
                x = top;
            }
            for (int i = 0; i < x; i++) {
                sideQue[i] = removedTilesPairs.get((removedTilesPairs.size() - 1) - i)[0];
            }
            for (int i = 0; i < sideQue.length; i++) {
                if (sideQue[i] != null) {
                    sideQue[i].setLocation(board.getWidth() - (int) (tileSize * 1.2), 10 + (i * (int) (tileSize * 1.3)));
                    sideQue[i].highlight(false);
                    sideQue[i].setVisible(true);
                    board.repaint();
                }
            }
        }
    }

    public static MahJongModel getInstance() {
        if (mj_model == null) {
            return null;
        }
        return mj_model;
    }

    public void undo() {
        if (!removedTilesPairs.isEmpty()) {
            removedTilesPairs.get(removedTilesPairs.size() - 1)[0].setToStart();
            removedTilesPairs.get(removedTilesPairs.size() - 1)[0].setVisibility(true);
            removedTilesPairs.get(removedTilesPairs.size() - 1)[1].setVisibility(true);
            removedTilesPairs.get(removedTilesPairs.size() - 1)[0].highlight(false);
            removedTilesPairs.get(removedTilesPairs.size() - 1)[1].highlight(false);
            redoTilesPairs.add(removedTilesPairs.get(removedTilesPairs.size() - 1));
            removedTilesPairs.remove(removedTilesPairs.size() - 1);
        } else {
            JOptionPane.showMessageDialog(board, "Nothing to undo!",
                    "Undo", JOptionPane.INFORMATION_MESSAGE);
        }
        updateSide();
    }

    public void redo() {
        if (!redoTilesPairs.isEmpty()) {
            redoTilesPairs.get(redoTilesPairs.size() - 1)[0].setVisibility(false);
            redoTilesPairs.get(redoTilesPairs.size() - 1)[1].setVisibility(false);
            removedTilesPairs.add(redoTilesPairs.get(redoTilesPairs.size() - 1));
            redoTilesPairs.remove(redoTilesPairs.size() - 1);
        } else {
            JOptionPane.showMessageDialog(board, "Nothing to redo!",
                    "Redo", JOptionPane.INFORMATION_MESSAGE);
        }
        updateSide();
    }

    public boolean isValidMove(Tile tile) {
        if (!tile.isEnabled()) {
            return false;
        }
        if (tile == left) {
            return true;
        }
        if (setup(tile).length < 2) {
            return true;
        } else {
            return false;
        }
    }

    private void clearHints() {
        if (showNextValidMove) {
            for (int i = 0; i < hints.size(); i++) {
                hints.get(i).highlight(false);
            }
        }
        while (!hints.isEmpty()) {
            hints.remove(0);
        }
    }

    public void hint() {
        clearHints();
        if (layer1.isVisible() && layer1.isEnabled()) {
            if (selectedTile.matches(layer1)) {
                hints.add(layer1);
            }
        }
        if (left.isVisible() && left.isEnabled()) {
            if (selectedTile.matches(left)) {
                hints.add(left);
            }
        }
        if (right1.isVisible() && right1.isEnabled()) {
            if (selectedTile.matches(right1)) {
                if (isValidMove(right1)) {
                    hints.add(right1);
                }
            }
        }
        if (right2.isVisible() && right2.isEnabled()) {
            if (selectedTile.matches(right2)) {
                hints.add(right2);
            }
        }
        checkValid(layer2, selectedTile, hints);
        checkValid(layer3, selectedTile, hints);
        checkValid(layer4, selectedTile, hints);
        checkValid(layer5, selectedTile, hints);

        for (int i = 0; i < hints.size(); i++) {
            hints.get(i).hintHighlight();
            selectedTile.highlight(true);
        }
    }

    private void checkValid(Tile[][] tiles, Tile x, ArrayList t) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != null && tiles[i][j].isVisible()) {
                    if (tiles[i][j].matches(x) && isValidMove(tiles[i][j])) {
                        t.add(tiles[i][j]);
                    }
                }
            }
        }
    }


    public void emptyRedoQue() {
        while (!redoTilesPairs.isEmpty()) {
            redoTilesPairs.remove(0);
        }
    }

    public void emptyUndoQue() {
        while (!removedTilesPairs.isEmpty()) {
            removedTilesPairs.remove(0);
        }
    }

    public void toggleShowNextMove() {
        showNextValidMove = !showNextValidMove;
    }

    public void setSelection(Tile tile) {
        audio.firstClick();

        if (position) {
            position = false;
            if (tile.matches(selectedTile)) {
                audio.secondClick();
                removedTilesPairs.add(new Tile[]{selectedTile, tile});
                selectedTile.setVisibility(false);
                tile.setVisibility(false);
                emptyRedoQue();
                updateSide();
                if (showNextValidMove) {
                    clearHints();
                }
            } else {
                selectedTile.highlight(false);
                selectedTile = tile;
                audio.noMatchClick();
                if (showNextValidMove) {
                    hint();
                }
                position = true;
            }

        } else {
            position = true;
            selectedTile = tile;
            if (showNextValidMove) {
                hint();
            }
        }
        if(removedTilesPairs.size() == 72) {

            winGame();
        }
    }

    public void winGame(){
            JOptionPane.showMessageDialog(null,"Congratulations, you win!!!","You Win!",JOptionPane.INFORMATION_MESSAGE);
            board.removeAll();
            wonGame = true;
            reward = new Fireworks(board);
            reward.setExplosions(23, 1000);
            reward.fire();
            audio.explosion();
            running = false;
    }

    public void removeSelection(Tile tile) {
        if (position) {
            position = false;
            if (showNextValidMove) {
                clearHints();
            }
        }
    }

    public Tile[] setup(Tile tile) {
        ArrayList<Tile> stack = new ArrayList<Tile>();
        if (tile == (layer1)) {
            return new Tile[]{layer1};
        }
        if (tile == (left)) {
            return new Tile[]{left, layer5[3][0], layer5[4][0]};
        }
        if (tile == (right1)) {
            stack.add(layer5[3][11]);
            if (right2.isVisible() && right2.isEnabled()) {
                stack.add(right2);
            }
        }
        if (tile == (right2)) {
            return new Tile[]{right1};
        }

        for (int x = 0; x < tileStack.length; x++) {
            for (int y = 0; y < tileStack[x].length; y++) {
                for (int z = 0; z < tileStack[x][y].length; z++) {
                    if (tileStack[x][y][z] == tile) {
                        if ((z - 1) >= 0 && tileStack[x][y][z - 1] != null) {
                            if (tileStack[x][y][z - 1].isVisible() && tileStack[x][y][z - 1].isEnabled()) {
                                stack.add(tileStack[x][y][z - 1]);
                            }
                        }
                        if ((z + 1) < tileStack[x][y].length && tileStack[x][y][z + 1] != null) {
                            if (tileStack[x][y][z + 1].isVisible() && tileStack[x][y][z + 1].isEnabled()) {
                                stack.add(tileStack[x][y][z + 1]);
                            }
                        }
                        if (x == 3) {
                            if (z > 2 && z < 9) {

                                if (y <= 6 && y > 0) {
                                    if (tileStack[2][y - 1][z - 3].isVisible() && tileStack[2][y - 1][z - 3].isEnabled()) {
                                        stack.add(tileStack[2][y - 1][z - 3]);
                                        stack.add(new Tile());
                                        stack.add(new Tile());
                                    }
                                }
                            }

                            if (y == 3 || y == 4) {
                                if (z == 0) {
                                    if (left.isVisible() && left.isEnabled()) {
                                        stack.add(left);
                                    }
                                }
                                if (z == tileStack[x][y].length - 1) {
                                    if (right1.isVisible()) {
                                        stack.add(right1);
                                    }
                                }
                            }
                        }
                        if (x < 3 && x > 0) {
                            if (y > 0 && z > 0) {
                                if (y < tileStack[x].length - 1 && z < tileStack[x][y].length - 1) {
                                    if (tileStack[x - 1][y - 1][z - 1].isVisible() && tileStack[x - 1][y - 1][z - 1].isEnabled()) {
                                        stack.add(tileStack[x - 1][y - 1][z - 1]);
                                        stack.add(new Tile());
                                        stack.add(new Tile());
                                    }
                                }
                            }
                        }
                        if (x == 0) {
                            if (layer1.isVisible() && layer1.isEnabled()) {
                                stack.add(layer1);
                            }
                        }
                    }
                }
            }
        }
        Tile[] tiles = new Tile[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            tiles[i] = stack.get(i);
        }
        return tiles;
    }

    public void fillSpecials(JLayeredPane jPanel) {
        int centerOffset = (int) (tileSize * .20) * (4);
        left.setLocation(tileSize + (int) (tileSize * .2) + globalXOffset, (9 / 2) * tileSize - (tileSize / 2) - (int) (tileSize * .2) + globalYOffset);
        jPanel.add(left);
        jPanel.moveToFront(left);

        right1.setLocation((tileSize * 14) + (int) (tileSize * .2) + globalXOffset, (9 / 2) * tileSize - (tileSize / 2) - (int) (tileSize * .2) + globalYOffset);
        jPanel.add(right1);

        right2.setLocation((tileSize * 15) + (int) (tileSize * .2) + globalXOffset, (9 / 2) * tileSize - (tileSize / 2) - (int) (tileSize * .2) + globalYOffset);
        jPanel.add(right2);

        layer1.setLocation((tileSize * 8) + (int) (tileSize * .2) - (tileSize / 2) + (int) (tileSize * .2) + globalXOffset + (centerOffset - (int) (tileSize * .2)), (9 / 2) * tileSize - (tileSize / 2) - (int) (tileSize * .2) + globalYOffset - centerOffset);
        jPanel.add(layer1);
        jPanel.moveToFront(layer1);

    }

    public void fillGrid(Tile[][] tiles, JLayeredPane jPanel) {
        int[][] mask = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                mask[i][j] = 1;
            }
        }
        fillGridWithMask(tiles, jPanel, mask);
    }

    public void fillGridWithMask(Tile[][] tiles, JLayeredPane jPanel, int[][] mask) {
        Tile t;
        int edgeOffset = (int) (tileSize * .20) * ((10 - tiles.length) / 2);
        int offsetX = ((16 / 2 - (tiles[0].length) + (tiles[0].length / 2)) * tileSize) + edgeOffset;
        int offsetY = ((9 / 2 - (tiles.length) + (tiles.length / 2)) * tileSize) - edgeOffset;
        for (int x = tiles.length - 1; x >= 0; x--) {
            for (int y = 0; y < tiles[x].length; y++) {
                if (x < mask.length) {
                    if (mask[x][y] == 1) {
                        t = deck.deal();
                        t.setLocation((offsetX) + (y * tileSize) + globalXOffset, (offsetY) + (x * tileSize) + globalYOffset);
                        if (!t.isVisible()) {
                            t.setVisibility(true);
                            t.highlight(false);
                        }
                        jPanel.add(t);
                        tiles[x][y] = t;
                    }
                }
            }
        }
    }

    public MahJongModel(JLayeredPane jPanel) {
        deck.shuffle();
        mj_model = this;
        board = jPanel;
        fill();
        JTextField field = new JTextField();
        field.setText("testing this");
        field.setEditable(false);
        field.setLocation(20, 20);
        jPanel.add(field);
    }

    public void newGame() {
        int c;
        c = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION);
        if (c == 0) {
            board.removeAll();
            deck = new TileDeck();
            deck.shuffle();
            fill();
        }
        if(wonGame == true) {
            reward.stop();
            Color bg = new Color(112, 139, 172);
            board.setOpaque(true);
            board.setBackground(bg);
        }
    }

    public void newGame(long x) {
        int c = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION);
        if (c == 0) {
            board.setBackground(bg);
            board.removeAll();
            deck = new TileDeck();
            deck.shuffle(x);
            fill();
        }
    }

    public void restart() {
        if(wonGame == false) {
            while (!removedTilesPairs.isEmpty()) {
                undo();
            }
            while (!redoTilesPairs.isEmpty()) {
                redoTilesPairs.remove(0);
            }
        } else {
            reward.stop();
            board.setOpaque(true);
            board.setBackground(bg);
        }
    }

    public void fill() {
        topFrame = (JFrame) board.getTopLevelAncestor();
        emptyUndoQue();
        emptyRedoQue();
        left = deck.deal();
        right1 = deck.deal();
        right2 = deck.deal();
        layer1 = deck.deal();
        fillGrid(layer2, board);
        fillGrid(layer3, board);
        fillGrid(layer4, board);
        fillGridWithMask(layer5, board, maskArrayBottom);
        fillSpecials(board);

        tileStack = new Tile[][][]{layer2, layer3, layer4, layer5};
            thread = new Thread(() -> {
                if (seconds != 0){
                    while (running) {
                        topFrame.setTitle("Score: " + removedTilesPairs.size() * 2 + " Time: " + (seconds / 60) / 60 + ":" + (seconds / 60) % 60 + ":" + seconds % 60);
                        seconds++;
                        try {
                            thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread Running");
                }
            });
            thread.start();
    }
}

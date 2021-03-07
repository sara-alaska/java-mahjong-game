/*
Displays and allows user to interact with menu,
menu items which contain information
about the game, game settings
hints and sound settings.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MahJong extends JFrame {
    int tileSize = 80;
    Color bg_color = new Color(158, 180, 169);
    Font menuFont = new Font("SansSerif", Font.BOLD, 16);
    Font menuItemFont = new Font("SansSerif", Font.BOLD, 14);
    private static SoundClip sound = new SoundClip();
    MahJongBoard brd;

    private void setBackgroundMusic() {
        sound.startSound();
    }

    public MahJong(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(bg_color);
        setTitle("MahJong Solitare");
        add(new MahJongTest.TestPanel());
        setSize(tileSize*18, tileSize*10);
        brd = new MahJongBoard();
        add(brd);
        brd.repaint();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);
        menuBar.setBorder(BorderFactory.createCompoundBorder(menuBar.getBorder(),BorderFactory.createEmptyBorder(0, 0, 10, 0)));

        JMenu game = new JMenu("Game");
        game.setForeground(Color.white);
        game.setFont(menuFont);

        JMenu move = new JMenu("Move");
        move.setForeground(Color.white);
        move.setFont(menuFont);

        JMenu sound = new JMenu("Sound");
        sound.setForeground(Color.white);
        sound.setFont(menuFont);

        JMenu help = new JMenu("Help");
        help.setForeground(Color.white);
        help.setFont(menuFont);

        JMenuItem newGame = new JMenuItem("New Game",'P');
        newGame.setBackground(Color.BLACK);
        newGame.setForeground(Color.white);
        newGame.setFont(menuItemFont);

        JMenuItem restart = new JMenuItem("Restart",'R');
        restart.setBackground(Color.BLACK);
        restart.setForeground(Color.white);
        restart.setFont(menuItemFont);

        JMenuItem newNumberedGame = new JMenuItem("Numbered Game",'N');
        newNumberedGame.setBackground(Color.BLACK);
        newNumberedGame.setForeground(Color.white);
        newNumberedGame.setFont(menuItemFont);

        JMenuItem exit = new JMenuItem("Exit",'N');
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.setFont(menuItemFont);

        JMenuItem undo = new JMenuItem("Undo Move",'U');
        undo.setBackground(Color.BLACK);
        undo.setForeground(Color.white);
        undo.setFont(menuItemFont);

        JMenuItem redo = new JMenuItem("Redo Move",'R');
        redo.setBackground(Color.BLACK);
        redo.setForeground(Color.white);
        redo.setFont(menuItemFont);

        JCheckBoxMenuItem soundOff = new JCheckBoxMenuItem("Sound On");
        soundOff.setBackground(Color.BLACK);
        soundOff.setForeground(Color.white);
        soundOff.setFont(menuItemFont);

        JCheckBoxMenuItem extraHelp = new JCheckBoxMenuItem("Show a hint");
        extraHelp.setBackground(Color.BLACK);
        extraHelp.setForeground(Color.white);
        extraHelp.setFont(menuItemFont);

        JMenuItem howTo = new JMenuItem("How To Play",'H');
        howTo.setBackground(Color.BLACK);
        howTo.setForeground(Color.white);
        howTo.setFont(menuItemFont);

        JMenuItem gameRules = new JMenuItem("Game rules",'G');
        gameRules.setBackground(Color.BLACK);
        gameRules.setForeground(Color.white);
        gameRules.setFont(menuItemFont);

        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        newNumberedGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

        game.add(newGame);
        game.add(restart);
        game.add(newNumberedGame);
        game.add(exit);
        move.add(undo);
        move.add(redo);
        sound.add(soundOff);
        help.add(extraHelp);
        help.add(howTo);
        help.add(gameRules);

        menuBar.add(game);
        menuBar.add(move);
        menuBar.add(sound);
        menuBar.add(help);

        setJMenuBar(menuBar);
        setVisible(true);

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brd.tileManager.undo();
            }
        });
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brd.tileManager.redo();
            }
        });
        newGame.addActionListener(e -> {
            brd.newGame();
            setBackgroundMusic();
            repaint();
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(brd.tileManager.wonGame == false) {
                    brd.tileManager.restart();
                    setBackgroundMusic();
                    repaint();
                } else {
                    restart.setEnabled(false);
                }
            }
        });

        exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,"Do you want to exit this game?",
                    "Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
                System.exit(0);
        });

        extraHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brd.tileManager.toggleShowNextMove();
                extraHelp.setText("Show a hint");
            }
        });


        soundOff.setSelected(true); // Allows sounds to be turned off
        soundOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundClip.setSoundon(((JCheckBoxMenuItem) e.getSource()).isSelected());
            }
        });

        newNumberedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input;
                String string = "";
                do {
                    input = JOptionPane.showInputDialog("Enter new game number: ");
                    if (input.matches("^[0-9]*$")) {
                        string = input;
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter only a number!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } while (!input.matches("^[0-9]*$"));
                brd.tileManager.newGame(Long.valueOf(string));
                repaint();
            }
        });

        howTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "1. Click on the a tile you want to match. If it is an eligible tile, it will be highlighted.\n" +
                                "2. Click on a matching tile. If it is a truly a matching tile, both tiles will vanish.\n",
                        "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                                "Mahjong is played by matching two identical tiles per turn.\n" +
                                "A tile is eligible for matching if no part of another tile is lying directly on it, and it has a free long edge on either the left or the right. \n" +
                                "You win by removing all tiles.","Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new MahJong();
    }
}
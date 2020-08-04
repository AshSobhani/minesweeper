import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineSweeper {
    final static int WIDTH = 5;
    final static int HEIGHT = 5;
    final static int NUM_MINES = 5;

    JFrame guiFrame = new JFrame();

    public MineSweeper(){
        Board myBoard = new Board(WIDTH, HEIGHT, NUM_MINES);

        createGUI(myBoard);
        myBoard.createMines(NUM_MINES);
        myBoard.countSurrounding();
    }

    public static void main(String[] args){
        MineSweeper runGame = new MineSweeper();
    }

    public void createGUI(Board myBoard){
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex){System.out.println(ex);}

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Minesweeper");
        JPanel msPanel = new JPanel();
        msPanel.setLayout(new GridLayout(HEIGHT, WIDTH));

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                BoardSquareButton myButton = new BoardSquareButton(x, y);
                myButton.addMouseListener(new MouseListener(){
                
                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON3){
                            if(myButton.getBackground() == Color.ORANGE){
                                myButton.setBackground(Color.white);
                                myButton.setText("?");
                            }
                            else{
                            myButton.setBackground(Color.ORANGE);
                            myButton.setText("X");
                            }
                        }
                        else myBoard.investigateToCheck(myButton.getXCoor(), myButton.getYCoor());
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {}
                
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                });
                msPanel.add(myButton);
                myBoard.storeButton(x, y, myButton);
            }
        }

        guiFrame.setLayout(new BorderLayout());
        guiFrame.add(msPanel, BorderLayout.CENTER);

        guiFrame.setPreferredSize(new Dimension(500, 500));
        guiFrame.setMinimumSize(new Dimension(200, 200));

        guiFrame.pack();
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
    }
}
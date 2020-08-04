import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

public class Board{

    private BoardSquareButton buttonArray[][];
    private int width, height, mines, checkedCount;

    public Board(int width, int height, int mines){
        this.buttonArray = new BoardSquareButton[width][height];
        this.width = width;
        this.height = height;
        this.checkedCount = width*height-mines;
        this.mines = mines;
    }

    public BoardSquareButton getButton(int x, int y){
        return this.buttonArray[x][y];
    }

    public void storeButton(int x, int y, BoardSquareButton button){
        this.buttonArray[x][y] = button;
    }

    public void initialiseAll(){
        this.checkedCount = this.width*this.height-this.mines;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                    getButton(x, y).initialise();
            }
        }
    }

    public void createMines(int minesNum){
        Random rand = new Random();
        int mines = 0;
        while (mines != minesNum){
            BoardSquareButton button = buttonArray[rand.nextInt(width)][rand.nextInt(height)];

			if(!button.getStateMine()){
                button.setStateMine(true);
                mines++;
            }
        }
    }

    public void finished(){
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if(!this.buttonArray[x][y].getStateChecked()){
                    this.buttonArray[x][y].setText("" + this.buttonArray[x][y].getAroundMine());
                    this.buttonArray[x][y].setBackground(Color.GREEN);
                    if(this.buttonArray[x][y].getStateMine()){
                        this.buttonArray[x][y].setText("X");
                        this.buttonArray[x][y].setBackground(Color.RED);
                    }
                }
            }
        }
    }

    public void hasWon(){
        if(this.checkedCount == 0){
            finished();
            JOptionPane.showMessageDialog(null, "You Win!");
            initialiseAll();
            createMines(this.mines);
            countSurrounding();
        }
    }

    public void countSurrounding(){
        int x = 0, y = 0;
        int xLess = -1;
        int xMore = 1;
        int yLess = -1;
        int yMore = 1;
        
        for (x = 0; x < this.width; x++) {
            for (y = 0; y < this.height; y++) {
                if(this.buttonArray[x][y].getStateMine()){
                    if (x == 0){
                        xLess = 0;
                    }
                    if (y == 0){
                        yLess = 0;
                    }
                    if (x == this.width-1){
                        xMore = 0;
                    }
                    if (y == this.height-1){
                        yMore = 0;
                    }
                    for (int i = xLess; i <= xMore; i++){
                        for (int j = yLess; j <=yMore; j++){
                            if(!this.buttonArray[x+i][y+j].getStateMine()){
                                this.buttonArray[x+i][y+j].setAroundMine(this.buttonArray[x+i][y+j].getAroundMine()+1);
                            }
                        }
                    }
                    xLess = -1;
                    xMore = 1;
                    yLess = -1;
                    yMore = 1;
                }
            }
        }
    }

    public void investigateToCheck(int x, int y){
        if (this.buttonArray[x][y].getStateChecked()){return;}

        if (!check(this.buttonArray[x][y])){
            return;
        }
        if(this.buttonArray[x][y].getAroundMine() == 0 && !this.buttonArray[x][y].getStateMine()){
            if(x > 0){
                if(y > 0) investigateToCheck(x-1, y-1);
                investigateToCheck(x-1, y);
                if(y < this.height-1) investigateToCheck(x-1, y+1);
            }
            if(y > 0) investigateToCheck(x, y-1);
            if(y < this.height-1) investigateToCheck(x, y+1);
            if (x < this.width-1){
                if(y > 0) investigateToCheck(x+1, y-1);
                investigateToCheck(x+1, y);
                if(y < this.height-1) investigateToCheck(x+1, y+1);
            }
        }
    }

    public boolean check(BoardSquareButton myButton){
        if(myButton.getStateMine()){
            myButton.setText("X");
            myButton.setBackground(Color.RED);
            finished();
            JOptionPane.showMessageDialog(null, "You Lose!");
            initialiseAll();
            createMines(this.mines);
            countSurrounding();
            return false;
        }
        else if(!myButton.getStateChecked()){
            myButton.setText("" + myButton.getAroundMine());
            myButton.setBackground(Color.GREEN);
            this.checkedCount--;
            myButton.setStateChecked(true);
            hasWon();
        }
        return true;
    }
}
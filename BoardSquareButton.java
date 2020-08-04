import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import javafx.scene.text.Font;

public class BoardSquareButton extends JButton{
    static final long serialVersionUID = 7526472295622776147L;
    private int xCoor, yCoor, aroundMine = 0;
    private boolean stateChecked, stateMine, stateFlagged;

    public BoardSquareButton(int x, int y){
        setSize(50, 50);
        setText("?");
        initialise();
        this.xCoor = x;
        this.yCoor = y;
    }

    public void initialise(){
        setText("?");
        setBackground(Color.WHITE);
        setStateChecked(false);
        setStateFlagged(false);
        setStateMine(false);
        setAroundMine(0);
    }

    public void setXCoor(int xCoor){
        this.xCoor = xCoor;
    }
    public void setYCoor(int yCoor){
        this.yCoor = yCoor;
    }
    public void setAroundMine(int aroundMine){
        this.aroundMine = aroundMine;
    }
    public void setStateChecked(boolean stateChecked){
        this.stateChecked = stateChecked;
    }
    public void setStateMine(boolean stateMine){
        this.stateMine = stateMine;
    }

    public void setStateFlagged(boolean stateFlagged){
        this.stateFlagged = stateFlagged;
    }


    public int getXCoor(){
        return this.xCoor;
    }
    public int getYCoor(){
        return this.yCoor;
    }
    public int getAroundMine(){
        return this.aroundMine;
    }
    public boolean getStateChecked(){
        return this.stateChecked;
    }
    public boolean getStateMine(){
        return this.stateMine;
    }
    public boolean getStateFlagged(){
        return this.stateFlagged;
    }

}
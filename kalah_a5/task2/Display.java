package kalah;

import com.qualitascorpus.testsupport.IO;

public class Display {
    private IO io;
    private IDisplayBoard displayBoard;
    private IDisplayGameInformation gameInformation;

    public Display(IO io){
        this.io = io;
    }

    public void setBoardDisplay(IDisplayBoard implementation){
        this.displayBoard = implementation;
        this.displayBoard.setIO(io);
    }

    public void setGameInformation(IDisplayGameInformation implementation){
        this.gameInformation = implementation;
        this.gameInformation.setIO(io);
    }

    public void displayBoard() {
        displayBoard.printBoard();
    }

    public void displayScore(){
        gameInformation.printScore();
    }

    public void gameOver(){
        gameInformation.printGameOver();
    }

    public void displayInvalidMessage(){
        gameInformation.printInvalidMessage();
    }
}

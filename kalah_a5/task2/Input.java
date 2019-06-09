package kalah;

import com.qualitascorpus.testsupport.IO;

public class Input {
    private IO io;

    public Input(IO io){
        this.io = io;
    }

    public  String getPlayerInput(int playerID){
        return io.readFromKeyboard(String.format("Player P%d's turn - Specify house number or 'q' to quit: ", playerID));
    }
}

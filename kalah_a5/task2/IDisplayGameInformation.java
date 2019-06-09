package kalah;

import com.qualitascorpus.testsupport.IO;

public interface IDisplayGameInformation {
    public void printScore();
    public void printGameOver();
    public void printInvalidMessage();
    public void setIO(IO io);
}

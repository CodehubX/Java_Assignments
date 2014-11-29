package Moje;

/**
 * Created by jm on 11/29/2014.
 */
public class Config {
    private int source;
    private int roundsOfMessages;
    private int numberOfProcesses;

    public Config(int source, int roundsOfMessages, int numberOfProcesses) {
        this.source = source;
        this.roundsOfMessages = roundsOfMessages;
        this.numberOfProcesses = numberOfProcesses;
    }

    public int getSource() {
        return source;
    }


    public int getRoundsOfMessages() {
        return roundsOfMessages;
    }


    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }
}

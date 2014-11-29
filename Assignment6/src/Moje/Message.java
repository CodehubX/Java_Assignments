package Moje;

import static Moje.Generals.Path;

/**
 * Message Class
 * The Traits base class is used to define the messaging behavior for
 * the processes. (Traits might not be the best name, as I am overloading it
 * a bit from its common use, but it's close.) The Process class has a global
 * object that of type Traits that it uses to get all the information used
 * to characterize the information about who is a general, who is a lieutentant,
 * and so on.
 * <p>
 * The Process class has a static Traits member whose methods are called
 * at key points in the messaging and decision phases to determine how
 * to behave. You change the configuration of the run by modifying this Tratis
 * class.
 * This particular implementation of the traits class implements
 * a faulty general and a faulty process 2. The general sends a one
 * or a zero to all processes, whereas process 2 sends a one to everyone,
 * regardless of what it is supposed to send
 */
public class Message {
    public int source;
    public int m;
    public int n;
    public boolean debug;

    /**
     * @param source
     * @param m
     * @param n
     * @param debug
     */
    public Message(int source, int m, int n, boolean debug) {
        this.source = source;
        this.m = m;
        this.n = n;
        this.debug = debug;
    }

    /**
     * The source may send faulty values to other processes,
     * but the Node returned by this value will be in its root node.
     * In this case, the General's node has in input value of 0, which makes that
     * the desired value. Of course, since the General is faulty, this doesn't really
     * matter.
     *
     * @return This method returns the true value of the source's value.
     */
    public Generals.Note getSourceValue() {
        return new Generals.Note(Path.ZERO, Path.UNKNOWN);
    }

    public String getValue(String value, int source, int dest, Path path) {
        if (source == mSource)
            return (dest & 1) ? Path.ZERO : Path.ONE;
        else if (source == 2)
            return Path.ONE;
        else
            return value;
    }


}

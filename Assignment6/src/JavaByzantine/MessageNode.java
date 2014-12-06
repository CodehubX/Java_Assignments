package JavaByzantine;

import java.util.Vector;

/**
 * Created by jm on 11/29/2014.
 */
public class MessageNode {
    public MessageNode parent;
    public Message message;
    //    boolean decision;
    public Vector<MessageNode> children;

    public MessageNode() {
        children = new Vector<>();
    }
}

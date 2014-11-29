package JavaByzantine;

import java.util.Vector;

/**
 * Created by jm on 11/29/2014.
 */
public class MessageNode {
    MessageNode parent;
    Message message;
    boolean decision;
    Vector<MessageNode> children;

    public MessageNode() {
        children = new Vector<>();
    }
}

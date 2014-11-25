package JavaByzantine;

import java.util.Vector;


/**
 * A tree of messages. Could be nested inside General. I'm sure this could be
 * simplified, but it is late.
 */
public class MessageTree {
    MessageNode root;
    int owner_id;

    public MessageTree(int id) {
        owner_id = id;
    }

    /**
     * Given a received message, where should it live in the tree?
     */
    private MessageNode findParent(Message m, int round) {
        assert m.path.size() == round + 1;
        int i = 1;
        MessageNode node = root;

        /* Descend tree via prefix matches until we hit our rank. */
        while (i < round) {
            for (MessageNode n : node.children) {
                if (n.message.prefixMatch(m.path)) {
                    node = n;
                    break;
                }
            }
            i++;
        }
        Byzantine.debugPrint("[" + owner_id + "] findParent(" + m + ", " + round + ") -> n" + node);
        return node;
    }

    /**
     * Add received list of messages to ADT for later decision making.
     */
    public void insert(Vector<Message> messages, int round) {
        if (round == 0) {
            assert messages.size() == 1;    // Usually avoid assert in public
            root = new MessageNode();
            root.parent = null;
            root.message = messages.firstElement();
            Byzantine.debugPrint("\t[" + owner_id +
                "] insert(..round 0..) -> root " + root);
        } else {

            for (Message m : messages) {
                MessageNode parent = findParent(m, round);
                // Could optimize this out
                MessageNode node = new MessageNode();
                node.message = m;
                parent.children.add(node);
                Byzantine.debugPrint("\t[" + owner_id + "]insert(" + m + "," + round + " ) -> parent " + parent.message);
            }
        }
    }


    /**
     * Interview question: Static nested classes can't access stuff inside their
     * parent, because they can be used without a instance of the parent.
     */
    public static class MessageNode {
        MessageNode parent;
        Message message;
        boolean decision;
        Vector<MessageNode> children;

        public MessageNode() {
            children = new Vector<MessageNode>();
        }
    }
}


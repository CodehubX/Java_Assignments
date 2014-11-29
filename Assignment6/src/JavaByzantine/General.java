package JavaByzantine;

/*
 * Fun thread-based solution to Byzantine Generals problem.  For algorithm, see
 * "The Byzantine Generals Problem", Lamport, Shostak, Pease 1982.
 * https://github.com/ajfabbri/JavaByzantine
 */
import java.util.Vector;

/**
 * A general in the Byzantine General problem, represented as a thread.
 *
 * @author fabbri
 */
class General implements Runnable {

    final boolean commander_should_attack = true;
    Mission mission;
    int id;
    MessageTree m_tree;

    public General(Mission m) {
        mission = m;
    }

    boolean amCommander() {
        return (id == 0);
    }

    boolean majority(Vector<Message> messages) {
        int truth_sum = 0;
        for (Message m : messages) {
            truth_sum += (m.value ? 1 : -1);
        }
        return (truth_sum > 0);  // More trues than falses?
    }

    public void assignId(int id) {
        this.id = id;
        m_tree = new MessageTree(id);
    }

    /**
     * Communication phase: Iterative approach.
     */
    public void communicationPhase() {
        boolean decision_this_round = false;
        Vector<Message> messages = null;

        System.out.println("Reporting for duty...");
        mission.reportForDuty(this);
        System.out.println("  ... assigned id " + id);

        for (int round = 0; round < mission.numRounds(); round++) {

            // Sending phase
            if (amCommander() && round == 0) {
                Message m = new Message(commander_should_attack);
                m.path.add(id);
                Vector<Message> v = new Vector<Message>();
                v.add(m);
                mission.sendRound(v, id, round);
                break;
            }


            // Send out copies of received messages, adding self to path,
            // and including our decision.
            Vector<Message> newMessages = new Vector();
            if (round != 0) {
                for (Message m : messages) {
                    if (m.senderId() != id) {
                        Message m_new = new Message(m); // a copy
                        m_new.path.add(id);
                        m_new.value = decision_this_round;
                        newMessages.add(m_new);
                    }
                }
            }
            mission.sendRound(newMessages, id, round);

            // Receiving phase
            messages = mission.receiveRound(id, round);
            m_tree.insert(messages, round);

            // Deciding phase
            decision_this_round = majority(messages);
        }
    }

    public void run() {
        communicationPhase();
    }
}

package NovyAssigment.algo;

import NovyAssigment.syssim.Participant;
import NovyAssigment.syssim.SimSystem;
import NovyAssigment.syssim.SysSimException;

import java.util.*;

public class ByzantineImpl {
    private static SimSystem system;
    private static List<Participant> participants = new ArrayList<Participant>();
    private static Map<Integer, Integer> participantIdList = new HashMap<Integer, Integer>();

    private static void setupWithToatlAndTraitorCount(int participantCount, int traitorCount) throws SysSimException {
        System.out.println("System starting with [" + participantCount
            + "] Nodes with [" + traitorCount
            + "] faulty node(s), [Node 0] is the Source.");
        List<Integer> traitors = randomizeTraitor(participantCount, traitorCount);
        for (int i = 0; i < participantCount; i++) {
            NODE_STATUS faith = NODE_STATUS.PERFECT;
            final int participantIndex = i;
            if (traitors.contains(i)) {
                faith = NODE_STATUS.FAULTY;
            }
            Participant participant = system.createParticipant(new ElectionListener(participantCount,
                participantIndex, faith, i == 0 ? ROLE.SOURCE
                : ROLE.NODE));
            participants.add(participant);
            participantIdList.put(participant.getID(), i);
        }
        system.bootUp();
    }

    private static List<Integer> randomizeTraitor(int pCount, int traitorCount) {
        Random random = new Random();
        List<Integer> traitorList = new ArrayList<Integer>();
        for (int i = 0; i < traitorCount; i++) {
            traitorList.add(random.nextInt(pCount));
        }
        return traitorList;
    }

    public static void main(String[] args) throws Exception {
        system = new SimSystem();
    /*
     * 4 nodes with 1 failed
		 */
        setupWithToatlAndTraitorCount(4, 1);
    }

    public enum NODE_STATUS {
        PERFECT("Perfect"), FAULTY("Faulty");
        private String faithType;

        private NODE_STATUS(String s) {
            faithType = s;
        }

        public String getNodeStatus() {
            return faithType;
        }

    }


    public enum ROLE {
        SOURCE("Coordinator"), NODE("Node");
        private String role;

        private ROLE(String s) {
            role = s;
        }

        public String getRole() {
            return role;
        }
    }


    public enum COMMAND {
        ATTACK(1), RETREAT(0);
        private int command;

        private COMMAND(int com) {
            command = com;
        }

        public int getCommand() {
            return command;
        }
    }


    public static class ElectionListener implements Participant.EventListener {
        final List<String> tokens = new ArrayList<String>();
        public boolean init;
        private int participantCount;
        private int participantIndex;
        private NODE_STATUS faithType;
        private ROLE role;
        private int attackCount;
        private int retreatcount;

        public ElectionListener(int participantCount, int participantIndex,
            NODE_STATUS faithType, ROLE role) {
            this.participantCount = participantCount;
            this.participantIndex = participantIndex;
            this.faithType = faithType;
            this.role = role;
        }

        @Override
        public void participantStarted(SimSystem simSystem) {
            String command = "ATTACK";
            if (ROLE.SOURCE == role) {
                participants.remove(0);
                System.out.println("Role : [Node 0 - " + role + "], Faith : [" + faithType + "]");
                for (int index = 0; index < participants.size(); index++) {
                    command = NODE_STATUS.FAULTY.equals(faithType) ? getCommand(index) : command;
                    simSystem.sendMessage(participants.get(index).getID(), new String[] {command});
                    System.out.println("[Node 0 - "
                        + role
                        + "], Sending : ["
                        + command
                        + "] to [Node "
                        + participantIdList.get(participants.get(index)
                        .getID()) + "]");
                }
            } else {
                System.out.println("Role : [" + role + " " + participantIndex + "], Faith : [" + faithType + "]");
            }
        }

        private String getCommand(final int index) {
            if (index % 2 == 1) {
                return "RETREAT";
            }
            return "ATTACK";
        }

        @Override
        public void eventReceived(SimSystem simSystem, String[] event) {
            if (ROLE.SOURCE == role) {
                return;
            }
            System.out.println("[Node " + participantIndex + "] received " + Arrays.toString(event));
            tokens.add(event[0]);
            if (event[0].equals("ATTACK")) {
                attackCount++;
            } else {
                retreatcount++;
            }
            if (!init) {
                alertOtherNodes(simSystem, event[0]);
                init = true;
            }

            if (tokens.size() == participantCount - 1) {
                StringBuilder builder = new StringBuilder("[Node " + participantIndex + "] Recieved Commands : [");
                for (String token : tokens) {
                    builder.append(token);
                    builder.append(" ");
                }
                String decision = (attackCount > retreatcount) ? "ATTACK" : "RETREAT";
                builder.append("] ---> Decision [" + decision + "]");
                System.out.println(builder.toString());
            }
        }

        private void alertOtherNodes(SimSystem simSystem,
            String recievedCommand) {
            List<Participant> localParticipants = new ArrayList<Participant>();
            localParticipants.addAll(participants);
            localParticipants.remove(participantIndex - 1);
            String command;
            for (int index = 0; index < localParticipants.size(); index++) {
                command = NODE_STATUS.FAULTY.equals(faithType) ? getCommand(index)
                    : recievedCommand;
                simSystem.sendMessage(localParticipants.get(index).getID(), new String[] {command});
                System.out.println("["
                    + role
                    + " "
                    + participantIndex
                    + "], Sending : ["
                    + command
                    + "] to [Node "
                    + participantIdList.get(localParticipants.get(index)
                    .getID()) + "]");
            }
        }
    }
}

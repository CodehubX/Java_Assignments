package Moje;

public class Generals {

    public enum Path {
        ONE, ZERO, UNKNOWN, FAULTY;
    }


    public static class Note {
        Path input;
        Path output;

        public Note() {
            this(Path.FAULTY, Path.FAULTY);
        }

        /**
         * Each process has a map of nodes, with the index to the map being
         * a path -  a string that consists of the list of process IDs that
         * the information came through. The input value of the node is set when
         * the message is received in the first round. The output value of the
         * node is set in the second round.
         * <p>
         * Note that we don't ever use the default constructor, but it is
         * required that we have one if we are going to store these objects
         * in a map
         *
         * @param input
         * @param output
         */
        public Note(Path input, Path output) {
            this.input = input;
            this.output = output;
        }
    }

}

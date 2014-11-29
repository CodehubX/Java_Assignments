package Moje;

import java.util.Vector;

/**
 * Created by jm on 11/25/2014.
 */
public class Moje {
    public static void main(String[] args) {
        int N = 7;
        int M = 2;
        int SOURCE = 3;
        boolean DEBUG = false;
        //
        // Create the message tree
        //
        Vector<Process> processes = new Vector<>();
        for (int i = 0; i < N; i++)
            processes.push_back(Process(i));
        //
        // Starting at round 0 and working up to round M, call the
        // SendMessages() method of each process. It will send the appropriate
        // message to all other sibling processes.
        //
        for (int i = 0; i <= M; i++)
            for (int j = 0; j < N; j++)
                processes[j].SendMessages(i, processes);
        //
        // All that is left is to print out the results. For non-faulty processes,
        // we call the Decide() method to see what what the process decision was
        //
        for (int j = 0; j < N; j++) {
            if (processes[j].IsSource())
                System.out.println("Source ");
            System.out.println("Process " + j);
            if (!processes[j].IsFaulty())
                system.out.println(" decides on value " + processes[j].Decide();
            else
                System.out.println(" is faulty");
            System.out.println("\n");
        }
        System.out.println("\n");
        for (; ; ) {
            String s;
            System.out.println("ID of process to dump, or enter to quit: ";
            getline(std::cin, s);
            if (s.size() == 0)
                break;
            int id;
            std::stringstream s1(s);
            s1 id;
            //
            // If Debug mode is turned on, we do a normal dump ahead of the DOT format
            // dump.
            //
            if (DEBUG) {
                system.out.println(processes[id].Dump() + "\n");
                getline(std::cin, s);
            }
            System.out.println(processes[id].DumpDot() + "\n");
        }
    }
}

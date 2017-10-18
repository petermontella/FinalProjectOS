
/**
 *
 * @author The Back Row
 *
 * BASIC NON-PREEMPTIVE PRIORITY SCHEDULER ALGORITHM
 *
 */
import java.util.*;

public class PriorityScheduler {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Comparator<ProcessClass> b = new ProcessClass();
        Queue<ProcessClass> procz = new PriorityQueue(b);
        Queue<ProcessClass> procz1 = new PriorityQueue(b);

        int noProcs, procPrio, procBT, aveWait, aveTurn;

        System.out.print("Enter no of processes: ");
        noProcs = in.nextInt();
        System.out.print("\nEnter burst time: ; enter process priority: ");

        for (int j = 0; j < noProcs; j++) {
            System.out.println("\nProcess[" + j + "]: ");
            procBT = in.nextInt();
            procPrio = in.nextInt();
            ProcessClass y = new ProcessClass(j, procBT, procPrio);
            procz.add(y);

        }
        System.out.println();

        aveWait = 0;
        aveTurn = 0;
        int turn = 0;

        for (int j = 0; j < noProcs; j++) {

            if (j == 0) {
                procz.peek().setWait(0);
                procz.peek().setTurn(procz.peek().getBT());
                turn = procz.peek().getTurn();
                ProcessClass h = new ProcessClass(procz.peek().getProcNum(), procz.peek().getBT(), procz.peek().getPrioNum(), procz.peek().getWait(), procz.poll().getTurn());
                procz1.add(h);
                aveTurn = turn;

            } else {
                procz.peek().setWait(turn);
                aveWait += procz.peek().getWait();
                procz.peek().setTurn(procz.peek().getWait() + procz.peek().getBT());
                ProcessClass h = new ProcessClass(procz.peek().getProcNum(), procz.peek().getBT(), procz.peek().getPrioNum(), procz.peek().getWait(), procz.peek().getTurn());
                procz1.add(h);
                aveTurn += turn;
                turn = procz.poll().getTurn();
            }

        } //end of for loop

        for (int j = 0; j < noProcs; j++) {
            System.out.println(procz1.peek().toString()
                    + "\nWait: " + procz1.peek().getWait()
                    + "\nTurn Around: " + procz1.poll().getTurn());

            System.out.println();
        }//end of second for loop

        aveWait /= noProcs;
        aveTurn /= noProcs;
        System.out.println("Average Wait Time: " + aveWait
                + "\nAverage Turnaround Time: " + aveTurn);
    } //end main

}//end of class

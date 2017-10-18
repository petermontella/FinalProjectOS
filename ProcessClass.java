
/**
 *
 * @author The Back Row
 */
import java.util.Comparator;

public class ProcessClass implements Comparator<ProcessClass> {

    private int processNumber;
    private int priorityNumber;
    private int burstTime;
    private int w8;
    private int turn;

    public ProcessClass() {

    }

    public ProcessClass(int procNum, int bt, int prioNum) {
        processNumber = procNum;
        burstTime = bt;
        priorityNumber = prioNum;

    }

    public ProcessClass(int procNum, int bt, int prioNum, int wait, int turnA) {
        processNumber = procNum;
        burstTime = bt;
        priorityNumber = prioNum;
        w8 = wait;
        turn = turnA;

    }
    //end of Constructors 

    public void setPrioNum(int x) {
        priorityNumber = x;
    }

    public void setWait(int x) {
        w8 = x;
    }

    public void setTurn(int x) {
        turn = x;
    }

    public void setWaitTurn(int wait, int turnA) {
        w8 = wait;
        turn = turnA;
    }

    public int getProcNum() {
        return processNumber;
    }

    public int getPrioNum() {
        return priorityNumber;
    }

    public int getBT() {
        return burstTime;
    }

    public int getWait() {
        return w8;
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return "Process[" + processNumber + "]: Burst Time: " + burstTime
                + " Priority Number: " + priorityNumber;
    }

    @Override
    public int compare(ProcessClass o1, ProcessClass o2) {
        if (o1.getPrioNum() > o2.getPrioNum()) {
            return 1;
        }
        else if (o1.getPrioNum() < o2.getPrioNum()) {
            return -1;
        }
        else{
            return 0;
        }
    }//end of compare

} //end of class

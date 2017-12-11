
/**
 *
 * @author The Back Row
 *
 * BATCH PROCESSING ALGORITHM
 *
 */
import java.util.*;

public class ProcessBatchQueue {

    private static Queue<ProcessClass> procz;
    private static Map<Integer, ProcessClass> procz1;

    public ProcessBatchQueue() {

        procz = new LinkedList();
        procz1 = new HashMap();
    }

    public void add(ProcessClass x) {
        procz.add(x);
        procz1.put(x.getuID(), x);
    }

    public ProcessClass getP(int uID) {
        return procz1.get(uID);
    }

    public int mapSize() {
        return procz1.size();
    }

    public ProcessClass equals1(String x, int uniqueID) {

        for (ProcessClass y : procz1.values()) {
            if (y.getC3() == null) {
                if (y.getC2().equals(x) && y.getuID() == uniqueID) {
                    return y;
                }
                if (y.getuID() == uniqueID) {
                    return y;
                }
            } else {

                if (y.getC3().equals(x) && y.getuID() == uniqueID) {
                    return y;
                }
                if (y.getuID() == uniqueID) {
                    return y;
                }
                if (y.getC2().equals(x) && y.getuID() == uniqueID) {
                    return y;
                }
                if (y.getuID() == uniqueID) {
                    return y;
                }
            }//end of else
        }//end of for
        return null;
    }

    public String exe() {
        if (procz.peek() != null) {

            return procz.poll().toString();
        } else {
            return "Empty.";
        }
    }

    public boolean remove(ProcessClass x) {
        procz1.remove(x.getuID());
        return procz.remove(x);
    }

    @Override
    public String toString() {
        String x = "";
        for (int i = 0; i < procz.size(); i++) {
            x += procz.poll().toString();
        }
        return x;
    }

}//end of class

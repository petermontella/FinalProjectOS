
/**
 *
 * @author The Back Row
 */

public class MemObj {

    private int size;

    public MemObj(int partitionSize) {
        size = partitionSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int partSize) {
        size = partSize;
    }

    @Override
    public String toString() {
        return "" + size;
    }

}

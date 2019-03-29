import exceptions.BinOverflow;
import exceptions.BinUnderflow;

public class Bin {
    public final int MAX_AMOUNT;
    private String stuffName="EMPTY";
    private int stuffAmount=0;

    /**
     * Bin object constructor
     * @param max_amount - max amount of stuff that the bin can contain
     */
    public Bin(int max_amount) {
        MAX_AMOUNT = max_amount;
    }

    /**
     * Returns name of the stuff currently in the bin
     * @return - name string
     */
    public String getStuffName() {
        return stuffName;
    }

    /**
     * Returns amount of the stuff currently in the bin
     * @return - int amount
     */
    public int getStuffAmount() {
        return stuffAmount;
    }

    /**
     * Flushes contents of the bin
     */
    public void flush() {
        stuffAmount=0;
    }

    /**
     * Adds or removes food stuff from the bin
     * @param amount - amount of stuff to be added to the bin if positive or amount of stuff to be removed from
     *               the bin if negative
     * @throws BinOverflow - is thrown if the amount provided would overflow the bin
     * @throws BinUnderflow - is throw if amount provided cannot be removed from the bin
     */
    public void addAmount(int amount) throws BinOverflow, BinUnderflow {
        if(stuffAmount+amount>MAX_AMOUNT) throw new BinOverflow();
        if(stuffAmount+amount<0) throw new BinUnderflow();
        stuffAmount+=amount;
    }

    /**
     * Change the name of the stuff in the bin
     * @param newName - name to be assigned to the stuff
     */
    public void changeStuffName(String newName) {
        flush();
        stuffName=newName;
    }
}

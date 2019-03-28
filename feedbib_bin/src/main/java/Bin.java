import exceptions.BinOverflow;
import exceptions.BinUnderflow;

public class Bin {
    private final int MAX_AMOUNT=40;
    private String stuffName="EMPTY";
    private int stuffAmount=0;

    public String getStuffName() {
        return stuffName;
    }

    public int getStuffAmount() {
        return stuffAmount;
    }

    public void flush(){
        stuffAmount=0;
    }

    public boolean addAmount(int amount) throws BinOverflow, BinUnderflow {
        if(stuffAmount+amount>MAX_AMOUNT) throw new BinOverflow();
        if(stuffAmount+amount<0) throw new BinUnderflow();
        stuffAmount+=amount;
        return true;
    }

    public void changeStuffName(String newName){
        flush();
        stuffName=newName;
    }
}

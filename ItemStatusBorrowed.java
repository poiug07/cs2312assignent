import java.util.ArrayList;

public class ItemStatusBorrowed implements ItemStatus {
    private Member borrower;
    private Day borrowedOn;
    private ArrayList<Member> queue; 

    public ItemStatusBorrowed(Member m, Day bOn, ArrayList<Member> q){
        this.borrower = m;
        this.borrowedOn = bOn;
        this.queue = q;
    }

    public boolean isAvailableTo(Member m) {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Borrowed by " + borrower.getIdPlusName() + " on " + borrowedOn);
        if(queue.size()>0){
            sb.append(" + "+queue.size()+" request(s):");
            for(Member m: queue)
                sb.append(" "+m.getId());
        }
        return sb.toString();
    }

    @Override
    public Member getMember() {
        return borrower;
    }

    @Override
    public boolean over(Day current) {
        // Later can be used for borrow period catering
        return false;
    }

    @Override
    public void printSorryMessage() {
    }
}

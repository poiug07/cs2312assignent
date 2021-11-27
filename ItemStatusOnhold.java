import java.util.ArrayList;

public class ItemStatusOnhold implements ItemStatus {
    private Member member;
    private Day holdUntil;
    private Item item;
    private ArrayList<Member> queue; 

    public ItemStatusOnhold(Member m, Day until, Item i, ArrayList<Member> q){
        this.member = m;
        this.holdUntil = until;
        item = i;
        queue = q;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("On holdshelf for " + member.getIdPlusName() + " until " + holdUntil);
        if(queue.size()>0){
            sb.append(" + "+queue.size()+" request(s):");
            for(Member m: queue)
                sb.append(" "+m.getId());
        }
        return sb.toString();
    }

    @Override
    public boolean isAvailableTo(Member m){
        return member==m;
    }

    @Override
    public Member getMember() {
        return member;
    }

    public String readyToPickMsg() {
        return String.format("Item [%s %s] is ready for pick up by [%s].  On hold due on %s.", item.getId(), item.getName(), member.getIdPlusName(), holdUntil);
    }

    @Override
    public boolean over(Day current) {
        return holdUntil.compareTo(current) < 0;
    }

}

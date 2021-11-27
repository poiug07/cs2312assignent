import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Club {
    private ArrayList<Member> allMembers;
    private ArrayList<Item> allItems;
    private static Club instance = new Club();

    private Club() {
        this.allMembers = new ArrayList<>();
        this.allItems = new ArrayList<>();
    }

    public static Club getInstance() {
        return instance;
    }

    public Member getMember(String id) {
        for(Member m: allMembers) {
            if(m.getId().equals(id))
                return m;
        }
        return null;
    }

    public Item getItem(String id) {
        for(Item i: allItems) {
            if(i.getId().equals(id))
                return i;
        }
        return null;
    }

    protected void addMember(Member m) {
        allMembers.add(m);
        Collections.sort(allMembers);
    }

    protected void deleteMember(Member m) {
        allMembers.remove(m);
    }

    public void listClubMembers() {
        System.out.println(Member.getListingHeader());
        for (Member m : allMembers) {
            System.out.println(m.getListingString());
        }
    }

    protected void addItem(Item i) {
        allItems.add(i);
        Collections.sort(allItems);
    }

    protected void deleteItem(Item i) {
        allItems.remove(i);
    }

    public void listClubItems() {
        System.out.println(Item.getListingHeader());
        for (Item i : allItems) {
            System.out.println(i.getListingString());
        }
    }

    public void updateOnHolds() {
        Day newDate = SystemDate.getInstance();
        for(Item item: allItems){
            item.updateOnHold(newDate);
        }
    }
}
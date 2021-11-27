import java.util.ArrayList;

public class Item implements Comparable<Item> {
    private String id;
    private String name;
    private Day arrDate;
    private ItemStatus status;
    private ArrayList<Member> queue;

    public Item(String i, String n) throws ExItemIdInUse {
        Item existing = Club.getInstance().getItem(i);
        if (existing != null)
            throw new ExItemIdInUse("Item ID already in use: " + existing.getId() + " " + existing.getName());

        this.id = i;
        this.name = n;
        arrDate = SystemDate.getInstance().clone();
        status = new ItemStatusAvailable();
        queue = new ArrayList<>();

        Club.getInstance().addItem(this);
    }

    public String getId() {
        return id;
    }

    public String getListingString() {
        return String.format("%-5s%-17s%11s   %s", this.id, this.name, this.arrDate, this.status);
    }

    public static String getListingHeader() {
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status");
    }

    public boolean isAvailable() {
        return (status instanceof ItemStatusAvailable);
    }

    @Override
    public int compareTo(Item another) {
        return this.id.compareTo(another.id);
    }

    public void changeStatus(ItemStatus s) {
        status = s;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void tryToCheckout(Member m)
            throws ExItemBorrowedByAnother, ExLoanQuotaExceeded, ExItemNotAvailable, ExItemAlreadyBorrowedByThis {
        if (status.isAvailableTo(m)) {
            m.checkoutItem(); // Exception might be thrown by it
            status = new ItemStatusBorrowed(m, SystemDate.getInstance().clone(), queue);
        } else {
            if (status.getMember() == m)
                throw new ExItemAlreadyBorrowedByThis();
            else
                throw new ExItemNotAvailable();
        }
    }

    public void checkin(Member m) throws ExItemBorrowedByAnother {
        if (status.getMember() != m) {
            throw new ExItemBorrowedByAnother();
        } else {
            m.decrementBorrowedItems();
            status = new ItemStatusAvailable();
            if (queue.size() > 0) {
                Member onholdfor = queue.get(0);
                Day holdUntil = SystemDate.getInstance().plus3Days();
                status = new ItemStatusOnhold(onholdfor, holdUntil, this, queue);
                queue.remove(0);
                onholdfor.decrementRequestedItems();
            }
            if (this.getStatus().checkinOperationMsg() != null)
                System.out.println(this.getStatus().checkinOperationMsg());
        }
    }

    public void request(Member m)
            throws ExRequestQuotaExceeded, ExAlreadyRequested, ExItemIsAvailable, ExItemAlreadyBorrowedByThis {
        if (status instanceof ItemStatusAvailable || status.isAvailableTo(m)) {
            throw new ExItemIsAvailable();
        }
        if (status.getMember() == m)
            throw new ExItemAlreadyBorrowedByThis();
        if (!queue.contains(m)) {
            m.request();
            this.queue.add(m);
        } else {
            throw new ExAlreadyRequested();
        }

    }

    public int getIdxInQueue(Member m) {
        return queue.indexOf(m);
    }

    public void cancelRequest(Member m) throws ExRequestNotFound {
        if (queue.contains(m)) {
            queue.remove(m);
            m.decrementRequestedItems();
        } else {
            throw new ExRequestNotFound();
        }
    }

    protected void insertIntoQueue(Member m, int i) {
        // Protected to restrict direct modification of queue
        // only by package and subclasses
        queue.add(i, m);
    }

    public void updateOnHold(Day current) {
        if (status instanceof ItemStatusOnhold) {
            if (status.over(current)) {
                System.out.println("On hold period is over for " + id + " " + name + ".");
                status = new ItemStatusAvailable();
                if (queue.size() > 0) {
                    Member onholdfor = queue.get(0);
                    Day holdUntil = SystemDate.getInstance().plus3Days();
                    status = new ItemStatusOnhold(onholdfor, holdUntil, this, queue);
                    queue.remove(0);
                    onholdfor.decrementRequestedItems();
                }
                if (this.getStatus().checkinOperationMsg() != null)
                    System.out.println(this.getStatus().checkinOperationMsg());
            }
        }
    }

}
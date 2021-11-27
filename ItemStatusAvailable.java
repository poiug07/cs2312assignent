public class ItemStatusAvailable implements ItemStatus {
    @Override
    public String toString() {
        return "Available";
    }

    public boolean isAvailableTo(Member m) {
        return true;
    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public String checkinOperationMsg() {
        return null;
    }

    @Override
    public boolean over(Day current) {
        return false;
    }

    @Override
    public void printSorryMessage() {
    }
}

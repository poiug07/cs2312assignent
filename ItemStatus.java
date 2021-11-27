public interface ItemStatus {
    public String toString();
    boolean isAvailableTo(Member m) throws ExItemAlreadyBorrowedByThis;
    public Member getMember();
    public String checkinOperationMsg();
    public boolean over(Day current);
    public void printSorryMessage();
}

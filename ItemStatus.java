public interface ItemStatus {
    // Exception should not be thrown by status.
    // Status is objective state, caller must decide
    // if it is erroneous and perform actions accordingly.
    public String toString();
    boolean isAvailableTo(Member m);
    public Member getMember();
    public String checkinOperationMsg();
    public boolean over(Day current);
    public void printSorryMessage();
}

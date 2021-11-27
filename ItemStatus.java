public interface ItemStatus {
    // Exception should not be thrown by status.
    // Status is objective state. Caller must decide
    // if state is erroneous and perform actions accordingly.
    public String toString();
    boolean isAvailableTo(Member m);
    public Member getMember();
    public boolean over(Day current);
}

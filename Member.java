public class Member implements Comparable<Member> {

    private String id;
    private String name;
    private Day joinDate;
    private int numOfBorrowedItems;
    private int numOfRequestedItems;

    public Member(String aId, String aName) throws ExMemberIdInUse {
        Member exists = Club.getInstance().getMember(aId);
        if (exists != null)
            throw new ExMemberIdInUse("Member ID already in use: " + exists.getIdPlusName());

        id = aId;
        name = aName;
        joinDate = SystemDate.getInstance().clone();

        Club.getInstance().addMember(this);
    }

    public String getId() {
        return id;
    }

    public String getIdPlusName() {
        return id + " " + name;
    }

    public String getListingString() {
        return String.format("%-5s%-9s%11s%7d%13d",
                this.id,
                this.name,
                this.joinDate,
                this.numOfBorrowedItems,
                this.numOfRequestedItems);
    }

    public static String getListingHeader() {
        return String.format("%-5s%-9s%11s%11s%13s",
                "ID",
                "Name",
                "Join Date",
                "#Borrowed",
                "#Requested");
    }

    @Override
    public int compareTo(Member another) {
        return this.id.compareTo(another.id);
    }

    public void checkoutItem() throws ExLoanQuotaExceeded {
        if (numOfBorrowedItems >= 6) {
            // Throw Exception QuotaLimitExceeded
            throw new ExLoanQuotaExceeded();
        } else {
            numOfBorrowedItems++;
        }
    }

    public void decrementBorrowedItems() {
        numOfBorrowedItems--;
    }

    public void request() throws ExRequestQuotaExceeded {
        if (numOfRequestedItems >= 3)
            throw new ExRequestQuotaExceeded();
        numOfRequestedItems++;
    }

    public void decrementRequestedItems() {
        numOfRequestedItems--;
    }
}

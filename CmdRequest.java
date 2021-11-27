public class CmdRequest extends RecordedCommand {

    private Member member;
    private Item item;
    private ItemStatus statusdump;

    @Override
    public void execute(String[] cmdParts)
            throws ExMemberNotFound, ExItemNotFound, ExRequestQuotaExceeded,
            ExAlreadyRequested, ExItemIsAvailable, ExItemAlreadyBorrowedByThis, ExInsufficientArguments {
        if (!ArgumentNumberChecker.pass(3, cmdParts)) {
            throw new ExInsufficientArguments();
        }
        Club c = Club.getInstance();
        member = c.getMember(cmdParts[1]);
        if (member == null)
            throw new ExMemberNotFound();
        item = c.getItem(cmdParts[2]);
        if (item == null)
            throw new ExItemNotFound();
        statusdump = item.getStatus();

        item.request(member);
        
        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done. This request is no. " + (item.getIdxInQueue(member) + 1) + " in the queue.");
    }

    @Override
    public void undoMe() {
        ItemStatus temp = item.getStatus();
        item.changeStatus(this.statusdump);
        this.statusdump = temp;

        try {
            this.item.cancelRequest(member);
        } catch (ExRequestNotFound e) {
            // Do nothing
        }

        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        ItemStatus temp = item.getStatus();
        item.changeStatus(this.statusdump);
        this.statusdump = temp;

        try {
            this.item.request(member);
        } catch (ExRequestQuotaExceeded | ExAlreadyRequested | ExItemIsAvailable | ExItemAlreadyBorrowedByThis e) {
            // Do nothing
        }

        addUndoCommand(this);
    }
}

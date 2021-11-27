public class CmdCheckin extends RecordedCommand {

    private Member member;
    private Item item;
    private ItemStatus statusdump;

    @Override
    public void execute(String[] cmdParts) throws ExMemberNotFound, ExItemNotFound, ExItemBorrowedByAnother, ExInsufficientArguments {
        if (!ArgumentNumberChecker.pass(3, cmdParts)){
            throw new ExInsufficientArguments();
        }
        Club c = Club.getInstance();
        member = c.getMember(cmdParts[1]);
        if(member==null)
            throw new ExMemberNotFound();
        item = c.getItem(cmdParts[2]);
        if(item==null)
            throw new ExItemNotFound();
        statusdump = item.getStatus();

        item.checkin(member);
        addUndoCommand(this);
        clearRedoList();
        
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        ItemStatus temp = item.getStatus();
        item.changeStatus(this.statusdump);
        this.statusdump = temp;

        temp.printSorryMessage();

        try {
            this.member.checkoutItem();
        } catch (ExLoanQuotaExceeded e) {
            // Do nothing
        }
        addRedoCommand(this);
    }
    
    @Override
    public void redoMe() {
        ItemStatus temp = item.getStatus();
        this.statusdump = temp;
        
        try {
            item.checkin(member);
        } catch (ExItemBorrowedByAnother e) {
            // Do nothing
            e.printStackTrace();
        }
        addUndoCommand(this);
    }
}

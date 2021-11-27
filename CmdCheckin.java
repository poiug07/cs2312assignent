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

        if(temp instanceof ItemStatusOnhold) {
            item.insertIntoQueue(temp.getMember(), 0);
            try {
                temp.getMember().request();
            } catch (ExRequestQuotaExceeded e) {
                // Do nothing
            }
            System.out.printf("Sorry. %s please ignore the pick up notice for %s.\n", temp.getMember().getIdPlusName(), item.getIdPlusName());
        }
    
        try {
            this.member.checkoutItem();
        } catch (ExLoanQuotaExceeded e) {
            // Do nothing
        }
        
        addRedoCommand(this);
    }
    
    @Override
    public void redoMe() {
        this.statusdump = item.getStatus();
        
        try {
            item.checkin(member);
        } catch (ExItemBorrowedByAnother e) {
            // Do nothing
            e.printStackTrace();
        }
        
        addUndoCommand(this);
    }
}

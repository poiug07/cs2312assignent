public class CmdCheckout extends RecordedCommand {
    
    private Member member;
    private Item item;
    private ItemStatus statusdump;

    // TODO: implement checkout
    @Override
    public void execute(String[] cmdParts) throws ExMemberNotFound, ExItemNotFound, ExItemBorrowedByAnother, ExLoanQuotaExceeded, ExItemNotAvailable, ExItemAlreadyBorrowedByThis, ExInsufficientArguments {
        if (!(new ArgumentNumberChecker().pass(3, cmdParts))){
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
        
        // Check item status without instanceof
        item.tryToCheckout(member);
        addUndoCommand(this);
        clearRedoList();
        
        System.out.println("Done.");
    }
    
    @Override
    public void undoMe(){
        ItemStatus temp = item.getStatus();
        item.changeStatus(this.statusdump);
        this.statusdump = temp;

        this.member.decrementBorrowedItems();
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        ItemStatus temp = item.getStatus();
        item.changeStatus(this.statusdump);
        this.statusdump = temp;
        
        try {
            this.member.checkoutItem(); 
        } catch (ExLoanQuotaExceeded e) {
            // Do Nothing
        }
        addUndoCommand(this);
    }
}

public class CmdCancelRequest extends RecordedCommand {

    private Member member;
    private Item item;
    private int idx;

    @Override
    public void execute(String[] cmdParts)
            throws ExMemberNotFound, ExItemNotFound, ExRequestNotFound, ExInsufficientArguments {
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
        idx = item.getIdxInQueue(member);
        
        item.cancelRequest(member);
        
        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        item.insertIntoQueue(member, idx);
        try {
            member.request();
        } catch (ExRequestQuotaExceeded e) {
            // Do nothing
        }
        
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        idx = item.getIdxInQueue(member);
        try {
            item.cancelRequest(member);
        } catch (ExRequestNotFound e) {
            // Do nothing
        }
        
        addUndoCommand(this);
    }

}

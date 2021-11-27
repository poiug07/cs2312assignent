public class CmdRegister extends RecordedCommand {

    private Member m;

    @Override
    public void execute(String[] cmdParts) throws ExMemberIdInUse, ExInsufficientArguments {
        if (!(new ArgumentNumberChecker().pass(3, cmdParts))){
            throw new ExInsufficientArguments();
        }
        m = new Member(cmdParts[1], cmdParts[2]);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Club.getInstance().deleteMember(this.m);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Club.getInstance().addMember(m);
        addUndoCommand(this);
    }
}

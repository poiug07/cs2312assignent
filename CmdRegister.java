public class CmdRegister extends RecordedCommand {

    private Member member;

    @Override
    public void execute(String[] cmdParts) throws ExMemberIdInUse, ExInsufficientArguments {
        if (!ArgumentNumberChecker.pass(3, cmdParts)) {
            throw new ExInsufficientArguments();
        }

        member = new Member(cmdParts[1], cmdParts[2]);

        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Club.getInstance().deleteMember(member);

        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Club.getInstance().addMember(member);

        addUndoCommand(this);
    }
}

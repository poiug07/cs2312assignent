public class CmdStartNewDay extends RecordedCommand {

    // String because, we cannot store 2 instances of SystemDate
    private String dump;

    @Override
    public void execute(String[] cmdParts) {
        SystemDate instance = SystemDate.getInstance();
        dump = instance.toString();

        instance.set(cmdParts[1]);
        Club.getInstance().updateOnHolds();
        
        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        SystemDate instance = SystemDate.getInstance();
        String temp = instance.toString();
        instance.set(dump);
        dump = temp;

        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        SystemDate instance = SystemDate.getInstance();
        String temp = instance.toString();
        instance.set(dump);
        dump = temp;
        
        addUndoCommand(this);
    }
}

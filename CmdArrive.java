public class CmdArrive extends RecordedCommand {
    
    private Item item;

    @Override
    public void execute(String[] cmdParts) throws ExItemIdInUse, ExInsufficientArguments {
        if (!ArgumentNumberChecker.pass(3, cmdParts)){
            throw new ExInsufficientArguments();
        }
        item = new Item(cmdParts[1], cmdParts[2]);
        addUndoCommand(this);

        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Club.getInstance().deleteItem(item);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Club.getInstance().addItem(item);
        addUndoCommand(this);
    }
}

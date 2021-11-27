interface Command {
    void execute(String[] cmdParts) throws ExItemIdInUse, ExMemberIdInUse, ExMemberNotFound, ExItemNotFound, ExItemBorrowedByAnother, ExLoanQuotaExceeded, ExItemNotAvailable, ExRequestQuotaExceeded, ExAlreadyRequested, ExItemIsAvailable, ExItemAlreadyBorrowedByThis, ExRequestNotFound, ExInsufficientArguments;
}
package com.group.original.panopticon.file.differences;

public enum TransferOrder {
    DIRECT(true),
    REVERS(false);

    private boolean isDirect;

    public boolean isDirect() {
        return isDirect;
    }

    private TransferOrder(boolean isDirect) {
        this.isDirect = isDirect;
    }
}

package com.group.original.panopticon.file;

public enum TransferOrder {
    DIRECT(true),
    REVERS(false);

    private boolean isDirect;

    public boolean isDirect() {
        return isDirect;
    }

    public TransferOrder getOpposite() {
        if (this == DIRECT) return REVERS;
        return DIRECT;
    }

    private TransferOrder(boolean isDirect) {
        this.isDirect = isDirect;
    }
}

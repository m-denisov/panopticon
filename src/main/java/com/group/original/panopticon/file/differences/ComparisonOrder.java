package com.group.original.panopticon.file.differences;

public enum ComparisonOrder {
    DIRECT(true),
    REVERSE(false);

    private boolean isDirect;

    public boolean isDirect() {
        return isDirect;
    }

    private ComparisonOrder(boolean isDirect) {
        this.isDirect = isDirect;
    }
}

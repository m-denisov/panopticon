package com.group.original.panopticon.investigator;

public enum AnalysisType {
    REGULAR(false),
    DEEP(true);

    private boolean booleanValue;

    AnalysisType(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }
}

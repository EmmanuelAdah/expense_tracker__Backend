package com.expensetracker.data.models;

public enum Type {
    FIXED, VARIABLE, CAPITAL, DIRECT, INDIRECT, OPERATIONAL;

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

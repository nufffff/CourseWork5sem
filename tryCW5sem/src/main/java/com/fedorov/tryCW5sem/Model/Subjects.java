package com.fedorov.tryCW5sem.Model;

public enum Subjects {
    Math("Математика"),
    Programming("Программирование"),
    History("История");

    private final String displayValue;

    private Subjects(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

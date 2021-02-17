package com.wafflarchiveapi.API;

public enum Round {
    FIRST ("B2:D11"),
    SECOND ("B12:D21"),
    THIRD ("B22:D31"),
    ;

    private final String cellRange;

    Round(String cellRange) {
        this.cellRange = cellRange;
    }

    public String getCellRange() {
        return cellRange;
    }
}

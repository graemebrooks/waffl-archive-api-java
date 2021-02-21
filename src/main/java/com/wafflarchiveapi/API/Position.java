package com.wafflarchiveapi.API;

public enum Position {
    QUARTERBACK ("AU2:AV13"),
    RUNNINGBACK ("BC2:BD13"),
    WIDE_RECEIVER ("BK2:BL13"),
    TIGHT_END ("BS2:BT13"),
    KICKER ("CI2:CJ13"),
    DEFENSE ("CQ2:CR13")
    ;

    private final String PointsByPositionCellRange;

    Position(String PointsByPositionCellRange) {
        this.PointsByPositionCellRange = PointsByPositionCellRange;
    }

    public String getPointsByPositionCellRange() {
        return PointsByPositionCellRange;
    }
}

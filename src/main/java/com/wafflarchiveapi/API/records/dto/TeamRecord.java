package com.wafflarchiveapi.API.records.dto;

import java.util.ArrayList;

public class TeamRecord {

    private PointsByPosition pointsByPosition = new PointsByPosition();
    private ArrayList<Season> seasons;

    public PointsByPosition getPointsByPosition() {
        return pointsByPosition;
    }

    public void setPointsByPosition(PointsByPosition pointsByPosition) {
        this.pointsByPosition = pointsByPosition;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
}

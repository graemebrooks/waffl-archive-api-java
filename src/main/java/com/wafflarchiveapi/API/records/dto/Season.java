package com.wafflarchiveapi.API.records.dto;

import java.util.List;

public class Season {

    private int year;
    private int wins;
    private int losses;
    private double pointsFor;
    private double pointsForPerGame;
    private double pointsAgainst;
    private double pointsAgainstPerGame;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getPointsFor() {
        return pointsFor;
    }

    public void setPointsFor(double pointsFor) {
        this.pointsFor = pointsFor;
    }

    public double getPointsForPerGame() {
        return pointsForPerGame;
    }

    public void setPointsForPerGame(double pointsForPerGame) {
        this.pointsForPerGame = pointsForPerGame;
    }

    public double getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(double pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }

    public double getPointsAgainstPerGame() {
        return pointsAgainstPerGame;
    }

    public void setPointsAgainstPerGame(double pointsAgainstPerGame) {
        this.pointsAgainstPerGame = pointsAgainstPerGame;
    }

}

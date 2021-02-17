package com.wafflarchiveapi.API;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class StatSheet {

    public ArrayList<TeamStats> teams;

    private GoogleSheet googleSheet;
    private String STATSHEET_SPREADSHEET_ID;

    public StatSheet() throws IOException, GeneralSecurityException {
        this.googleSheet = new GoogleSheet();
        this.STATSHEET_SPREADSHEET_ID = "10hTrKAubzc-uZ_RtKGVvU3cg9hhN2cDYll5p1ltfwmk";
    }

    private List<List<Object>> getStatSheetValues(Round round) throws IOException {
        return googleSheet.getSheetValuesFromRange(STATSHEET_SPREADSHEET_ID,
                getFormattedRange());
    }

    private String getFormattedRange() {
        return String.valueOf("All-Time Regular Season Team Stats") + "!" + "A1:AG13";
    }

    private class TeamStats {

        public String teamName;
        public int allTimeWins;
        public int allTimelosses;
        public double allTimePointsScored;
        public double allTimePointsPerGame;
        public double allTimePointsAgainst;
        public double allTimePointsAgainstPerGame;
        public double averageMarginPerGame;
        public int longestWinStreak;
        public int longestLossStreak;
        public Game highestScoringGame;
        public Game lowestScoringGame;
        public Season bestSeason;
        public Season worstSeason;
        public int winningSeasons;
        public int losingSeasons;
        public TeamInfo teamInfo;


        public TeamStats() {
        }

        private class Game {
            public double score;
            public String week;

            public Game(double score, String week) {
                this.score = score;
                this.week = week;
            }
        }

        private class Season {
            public String record;
            public int year;
        }
    }

}

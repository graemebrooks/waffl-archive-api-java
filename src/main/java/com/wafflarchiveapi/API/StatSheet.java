package com.wafflarchiveapi.API;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StatSheet {

    public ArrayList<TeamStats> teams;

    private GoogleSheet googleSheet;
    private String STATSHEET_SPREADSHEET_ID;
    private List<List<Object>> statSheetValues;

    public StatSheet() throws IOException, GeneralSecurityException {
        this.googleSheet = new GoogleSheet();
        this.STATSHEET_SPREADSHEET_ID = "10hTrKAubzc-uZ_RtKGVvU3cg9hhN2cDYll5p1ltfwmk";
        this.statSheetValues = getStatSheetValues();
        this.teams = new ArrayList<>(16);
        populateTeams();
    }

    private List<List<Object>> getStatSheetValues() throws IOException {
        return googleSheet.getSheetValuesFromRange(STATSHEET_SPREADSHEET_ID,
                getFormattedRange());
    }

    private Object getCellValueFromRow(List<Object> row, int cellIndex) {
        return row.get(cellIndex);
    }

    private String getFormattedRange() {
        return "All-Time Regular Season Team Stats" + "!" + "A1:AG13";
    }

    private void populateTeams() throws IOException {
        for (List<Object> teamRow : statSheetValues) {
            Optional<TeamInfo> team = identifyTeamFromName(String.valueOf(teamRow.get(1)));
            if (team.isPresent()) {
                TeamStats teamStats = new TeamStats(teamRow, team.get());
                teams.add(teamStats);
            }
        }
    }

    private Optional<TeamInfo> identifyTeamFromName(String teamName) {
        return Arrays.stream(TeamInfo.values()).filter(team -> team.clubName.equals(teamName)).findFirst();
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
        public String logoUrl;
        public String primaryColor;
        public String secondaryColor;

        public TeamStats(List<Object> teamStatsRow, TeamInfo teamInfo) {

            this.teamName = teamInfo.clubName;
            setWinLossStats(teamStatsRow);
            setPointsStats(teamStatsRow);
            setStreakStats(teamStatsRow);
            setSingleGameScoringStats(teamStatsRow);
            setSeasonStats(teamStatsRow);
            this.logoUrl = teamInfo.logoURL;
            this.primaryColor = teamInfo.primaryColor;
            this.secondaryColor = teamInfo.secondaryColor;
        }

        private void setWinLossStats(List<Object> teamStatsRow) {
            this.allTimeWins = Integer.parseInt(getCellValueFromRow(teamStatsRow, 2).toString());
            this.allTimelosses = Integer.parseInt(getCellValueFromRow(teamStatsRow, 3).toString());
        }

        private void setPointsStats(List<Object> teamStatsRow) {
            this.allTimePointsScored =
                    Double.parseDouble(getCellValueFromRow(teamStatsRow, 7).toString());
            this.allTimePointsPerGame =
                    Double.parseDouble(getCellValueFromRow(teamStatsRow, 8).toString());
            this.allTimePointsAgainst =
                    Double.parseDouble(getCellValueFromRow(teamStatsRow, 9).toString());;
            this.allTimePointsAgainstPerGame =
                    Double.parseDouble(getCellValueFromRow(teamStatsRow, 10).toString());
            this.averageMarginPerGame =
                    Double.parseDouble(getCellValueFromRow(teamStatsRow, 11).toString());
        }

        private void setSingleGameScoringStats(List<Object> teamStatsRow) {
            this.highestScoringGame =
                    new Game(Double.parseDouble(getCellValueFromRow(teamStatsRow, 16).toString()),
                            getCellValueFromRow(teamStatsRow, 17).toString());
            this.lowestScoringGame =
                    new Game(Double.parseDouble(getCellValueFromRow(teamStatsRow, 18).toString()),
                            getCellValueFromRow(teamStatsRow, 19).toString());
        }

        private void setStreakStats(List<Object> teamStatsRow) {
            this.longestWinStreak =
                    Integer.parseInt(getCellValueFromRow(teamStatsRow, 12).toString());
            this.longestLossStreak =
                    Integer.parseInt(getCellValueFromRow(teamStatsRow, 13).toString());
        }

        private void setSeasonStats(List<Object> teamStatsRow) {
            this.bestSeason =
                    new Season(getCellValueFromRow(teamStatsRow, 20).toString(),
                            Integer.parseInt(getCellValueFromRow(teamStatsRow, 21).toString()));
            this.worstSeason =
                    new Season(getCellValueFromRow(teamStatsRow, 22).toString(),
                            Integer.parseInt(getCellValueFromRow(teamStatsRow, 23).toString()));
            this.winningSeasons =
                    Integer.parseInt(getCellValueFromRow(teamStatsRow, 30).toString());
            this.losingSeasons =
                    Integer.parseInt(getCellValueFromRow(teamStatsRow, 31).toString());
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

            public Season(String record, int year) {
                this.record = record;
                this.year = year;
            }
        }
    }

}

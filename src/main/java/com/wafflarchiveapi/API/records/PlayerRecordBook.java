package com.wafflarchiveapi.API.records;

import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class PlayerRecordBook {

    private GoogleSheet googleSheet;
    private String RECORDBOOK_SPREADSHEET_ID;

    public PlayerRecord[] highestPlayerScores;
    public PlayerRecord[] highestQuarterbackScores;
    public PlayerRecord[] highestRunningbackScores;
    public PlayerRecord[] highestWideReceiverScores;
    public PlayerRecord[] highestTightEndScores;

    public PlayerRecord[] bestQuarterbackSeasons;
    public PlayerRecord[] bestRunningbackSeasons;
    public PlayerRecord[] bestWideReceiverSeasons;
    public PlayerRecord[] bestTightEndSeasons;
    public PlayerRecord[] bestDefenseSeasons;

    public PlayerRecordBook() throws IOException, GeneralSecurityException {
        this.googleSheet = new GoogleSheet();
        this.RECORDBOOK_SPREADSHEET_ID = "16N1aqct2O9zsIWcy9W-AhMd-IW8JCoF8DY1fqElYPB8";
        this.highestPlayerScores = buildHighestPositionalScores("B2:C11");
        this.highestQuarterbackScores = buildHighestPositionalScores("D2:E11");
        this.highestRunningbackScores = buildHighestPositionalScores("F2:G11");
        this.highestWideReceiverScores = buildHighestPositionalScores("H2:I11");
        this.highestTightEndScores = buildHighestPositionalScores("J2:K11");

        this.bestQuarterbackSeasons = buildBestSeasonsScores("L2:N11");
        this.bestRunningbackSeasons = buildBestSeasonsScores("O2:R11");
        this.bestWideReceiverSeasons = buildBestSeasonsScores("R2:T11");
        this.bestTightEndSeasons = buildBestSeasonsScores("U2:W11");
        this.bestDefenseSeasons = buildBestSeasonsScores("AA2:AC11");
    }

    private String getFormattedRange(String cellRange) {
        return "Player Records" + "!" + cellRange;
    }

    private PlayerRecord[] buildHighestPositionalScores(String cellRange) throws IOException {
        List<List<Object>> scoreValues =
                googleSheet.getSheetValuesFromRange(RECORDBOOK_SPREADSHEET_ID,
                getFormattedRange(cellRange));
        PlayerRecord[] highestScoresArray = new PlayerRecord [10];
        for (int i = 0; i < 10; i++) {
            List<Object> recordRow = scoreValues.get(i);
            highestScoresArray[i] = new PlayerRecord(
                    Double.parseDouble(recordRow.get(0).toString()),
                    recordRow.get(1).toString()
            );
        }
        return highestScoresArray;
    }

    private PlayerRecord[] buildBestSeasonsScores(String cellRange) throws IOException {
        List<List<Object>> scoreValues =
                googleSheet.getSheetValuesFromRange(RECORDBOOK_SPREADSHEET_ID,
                        getFormattedRange(cellRange));
        PlayerRecord[] bestSeasonScoresArray = new PlayerRecord [10];
        for (int i = 0; i < 10; i++) {
            List<Object> recordRow = scoreValues.get(i);
            bestSeasonScoresArray[i] = new PlayerRecord(
                    Double.parseDouble(recordRow.get(0).toString()),
                    recordRow.get(2).toString()
            );
        }
        return bestSeasonScoresArray;
    }

    public static class PlayerRecord {
        public double score;
        public String playerAndDate;

        public PlayerRecord(double score, String playerAndDate) {
            this.score = score;
            this.playerAndDate = playerAndDate;
        }
    }
}

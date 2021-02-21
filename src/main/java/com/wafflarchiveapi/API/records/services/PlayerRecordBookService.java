package com.wafflarchiveapi.API.records.services;

import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class PlayerRecordBookService {

    @Autowired
    private GoogleSheet googleSheet;

    @Value("${RECORDBOOK_SPREADSHEET_ID}")
    private String RECORDBOOK_SPREADSHEET_ID;

    public HashMap<String, PlayerRecord[]> build() throws IOException {
        HashMap<String, PlayerRecord[]> playerRecordHashMap = new HashMap<>();

        playerRecordHashMap.put("highestPlayerScores", buildPlayerRecordsArray("B2:C11",0, 1));
        playerRecordHashMap.put("highestQuarterbackScores", buildPlayerRecordsArray("D2:E11",0, 1));
        playerRecordHashMap.put("highestRunningbackScores", buildPlayerRecordsArray("F2:G11",0, 1));
        playerRecordHashMap.put("highestWideReceiverScores", buildPlayerRecordsArray("H2:I11",0, 1));
        playerRecordHashMap.put("highestTightEndScores", buildPlayerRecordsArray("J2:K11",0, 1));

        playerRecordHashMap.put("bestQuarterbackSeasons", buildPlayerRecordsArray("L2:N11",0, 2));
        playerRecordHashMap.put("bestRunningbackSeasons", buildPlayerRecordsArray("O2:R11",0, 2));
        playerRecordHashMap.put("bestWideReceiverSeasons", buildPlayerRecordsArray("R2:T11",0, 2));
        playerRecordHashMap.put("bestTightEndSeasons", buildPlayerRecordsArray("U2:W11",0, 2));
        playerRecordHashMap.put("bestDefenseSeasons", buildPlayerRecordsArray("AA2:AC11",0, 2));

        return playerRecordHashMap;
    }


    private PlayerRecord[] buildPlayerRecordsArray(String cellRange, int scoreIndex,
                                                        int playerAndDateIndex) throws IOException {
        List<List<Object>> scoreValues = getPlayerRecordLists(cellRange);
        PlayerRecord[] resultArray = new PlayerRecord[10];
        populateResultArray(scoreValues, resultArray, scoreIndex, playerAndDateIndex);
        return resultArray;
    }


    private void populateResultArray(List<List<Object>> scoreValues, PlayerRecord[] resultArray,
                              int scoreIndex, int playerAndDateIndex) {
        for (int i = 0; i < 10; i++) {
            List<Object> recordRow = scoreValues.get(i);
            PlayerRecord pr = new PlayerRecord();
            pr.setScore(Double.parseDouble(recordRow.get(scoreIndex).toString()));
            pr.setPlayerAndDate(recordRow.get(playerAndDateIndex).toString());
            resultArray[i] = pr;
        }
    }

    private List<List<Object>> getPlayerRecordLists(String cellRange) throws IOException {
        return googleSheet.getSheetValuesFromRange(RECORDBOOK_SPREADSHEET_ID,
                getFormattedRange(cellRange));
    }

    private String getFormattedRange(String cellRange) {
        return "Player Records" + "!" + cellRange;
    }


    public static class PlayerRecord {
        private double score;
        private String playerAndDate;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getPlayerAndDate() {
            return playerAndDate;
        }

        public void setPlayerAndDate(String playerAndDate) {
            this.playerAndDate = playerAndDate;
        }
    }

}

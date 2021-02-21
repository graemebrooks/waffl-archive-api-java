package com.wafflarchiveapi.API.records.services;

import com.wafflarchiveapi.API.Position;
import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import com.wafflarchiveapi.API.records.dto.TeamRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class PointsByPositionService {

    @Autowired
    private GoogleSheet googleSheet;

    @Value("${RECORDBOOK_SPREADSHEET_ID}")
    private String RECORDBOOK_SPREADSHEET_ID;

    public void populatePointsByPosition(HashMap<String, TeamRecord> teamRecordHashMap) throws IOException {
        setPointsByPosition(teamRecordHashMap, Position.QUARTERBACK);
        setPointsByPosition(teamRecordHashMap, Position.RUNNINGBACK);
        setPointsByPosition(teamRecordHashMap, Position.WIDE_RECEIVER);
        setPointsByPosition(teamRecordHashMap, Position.TIGHT_END);
        setPointsByPosition(teamRecordHashMap, Position.KICKER);
        setPointsByPosition(teamRecordHashMap, Position.DEFENSE);
    }

    private void setPointsByPosition(HashMap<String, TeamRecord> teamRecordHashMap,
                                     Position position) throws IOException {
        List<List<Object>> positionPointCells = getPointsByPositionListsFromCells(position);
        positionPointCells.forEach(row -> {
            setPositionPoints(teamRecordHashMap, position, row);
        });
    }

    private List<List<Object>> getPointsByPositionListsFromCells(Position position) throws IOException {
        return googleSheet.getSheetValuesFromRange(RECORDBOOK_SPREADSHEET_ID,
                getFormattedYearlyTeamRecordsRange(
                        position.getPointsByPositionCellRange()));
    }

    private void setPositionPoints(HashMap<String, TeamRecord> teamRecordHashMap, Position position, List<Object> row) {
        String teamName = row.get(1).toString();
        switch (position) {
            case QUARTERBACK:
                teamRecordHashMap.get(teamName).getPointsByPosition().setQuarterback(Double.parseDouble(row.get(0).toString()));
                break;
            case RUNNINGBACK:
                teamRecordHashMap.get(teamName).getPointsByPosition().setRunningback(Double.parseDouble(row.get(0).toString()));
                break;
            case WIDE_RECEIVER:
                teamRecordHashMap.get(teamName).getPointsByPosition().setWideReceiver(Double.parseDouble(row.get(0).toString()));
                break;
            case TIGHT_END:
                teamRecordHashMap.get(teamName).getPointsByPosition().setTightEnd(Double.parseDouble(row.get(0).toString()));
                break;
            case KICKER:
                teamRecordHashMap.get(teamName).getPointsByPosition().setKicker(Double.parseDouble(row.get(0).toString()));
                break;
            case DEFENSE:
                teamRecordHashMap.get(teamName).getPointsByPosition().setDefense(Double.parseDouble(row.get(0).toString()));
                break;
        }
    }

    private static String getFormattedYearlyTeamRecordsRange(String cellRange) {
        return "Yearly Team Records" + "!" + cellRange;
    }

}

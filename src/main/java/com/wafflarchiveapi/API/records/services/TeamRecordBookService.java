package com.wafflarchiveapi.API.records.services;

import com.wafflarchiveapi.API.TeamInfo;
import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import com.wafflarchiveapi.API.records.dto.PointsByPosition;
import com.wafflarchiveapi.API.records.dto.Season;
import com.wafflarchiveapi.API.records.dto.TeamRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TeamRecordBookService {

    @Autowired
    private GoogleSheet googleSheet;
    private static String RECORDBOOK_SPREADSHEET_ID = "16N1aqct2O9zsIWcy9W-AhMd" +
            "-IW8JCoF8DY1fqElYPB8";

    public HashMap<String, TeamRecord> teamRecordHashMap;

    public HashMap<String, TeamRecord> build() throws IOException {
        HashMap<String, TeamRecord> teamRecordHashMap = new HashMap<>();
        for (TeamInfo team : TeamInfo.values()) {
            TeamRecord teamRecord = new TeamRecord();
            teamRecord.setPointsByPosition(getPointsByPosition(team.clubName));
            if (team.seasonsCellRange != null) {
                teamRecord.setSeasons(getSeasons(team.seasonsCellRange));
            }
            teamRecordHashMap.put(team.clubName, teamRecord);
        }
        return teamRecordHashMap;
    }

    private PointsByPosition getPointsByPosition(String teamName) throws IOException {
        PointsByPosition pbp = new PointsByPosition();
        pbp.setQuarterback(getPositionPoints(teamName, "AU2:AV13"));
        pbp.setRunningback(getPositionPoints(teamName, "BC2:BD13"));
        pbp.setWideReceiver(getPositionPoints(teamName, "BK2:BL13"));
        pbp.setTightEnd(getPositionPoints(teamName, "BS2:BT13"));
        pbp.setKicker(getPositionPoints(teamName, "CI2:CJ13"));
        pbp.setDefense(getPositionPoints(teamName, "CQ2:CR13"));

        return pbp;
    }

    private Double getPositionPoints(String teamName, String cellRange) throws IOException {
        List<List<Object>> positionPointCells =
                googleSheet.getSheetValuesFromRange(RECORDBOOK_SPREADSHEET_ID,
                        getFormattedYearlyTeamRecordsRange(
                                cellRange));
        Optional<List<Object>> positionPointsRow =
                positionPointCells.stream().filter(team -> team.get(1).toString().equals(teamName)).findFirst();
        return positionPointsRow.map(p -> Double.parseDouble(p.get(0).toString())).orElse(null);
    }

    private static String getFormattedYearlyTeamRecordsRange(String cellRange) {
        return "Yearly Team Records" + "!" + cellRange;
    }

    private ArrayList<Season> getSeasons(String cellRange) throws IOException {
        ArrayList<Season> seasons = new ArrayList<>();
        List<List<Object>> seasonCells =
                googleSheet.getSheetValuesFromRange(RECORDBOOK_SPREADSHEET_ID,
                        getFormattedTeamHistoryRange(cellRange));
        for (List<Object> seasonRow : seasonCells) {
            seasons.add(buildSeason(seasonRow));
        }
        return seasons;
    }

    private Season buildSeason(List<Object> seasonRow) {
        Season season = new Season();
        season.setYear(Integer.parseInt(seasonRow.get(0).toString()));
        season.setWins(Integer.parseInt(seasonRow.get(1).toString()));
        season.setLosses(Integer.parseInt(seasonRow.get(2).toString()));
        season.setPointsFor(Double.parseDouble(seasonRow.get(3).toString()));
        season.setPointsForPerGame(Double.parseDouble(seasonRow.get(4).toString()));
        season.setPointsAgainst(Double.parseDouble(seasonRow.get(5).toString()));
        season.setPointsAgainstPerGame(Double.parseDouble(seasonRow.get(6).toString()));

        return season;
    }

    private static String getFormattedTeamHistoryRange(String cellRange) {
        return "Team History" + "!" + cellRange;
    }


}

package com.wafflarchiveapi.API.records.services;

import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import com.wafflarchiveapi.API.records.dto.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonService {

    @Autowired
    private GoogleSheet googleSheet;

    @Value("${RECORDBOOK_SPREADSHEET_ID}")
    private String RECORDBOOK_SPREADSHEET_ID;

    public ArrayList<Season> getSeasons(String cellRange) throws IOException {
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

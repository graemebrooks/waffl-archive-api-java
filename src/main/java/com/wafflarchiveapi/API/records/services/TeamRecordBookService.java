package com.wafflarchiveapi.API.records.services;

import com.wafflarchiveapi.API.TeamInfo;
import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import com.wafflarchiveapi.API.records.dto.TeamRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class TeamRecordBookService {

    @Autowired
    private GoogleSheet googleSheet;

    @Autowired
    private PointsByPositionService pointsByPositionService;

    @Autowired
    private SeasonService seasonService;

    public HashMap<String, TeamRecord> build() throws IOException {
        HashMap<String, TeamRecord> teamRecordHashMap = initializeTeamRecordHashMap();
        pointsByPositionService.populatePointsByPosition(teamRecordHashMap);
        return teamRecordHashMap;
    }

    private HashMap<String, TeamRecord> initializeTeamRecordHashMap() throws IOException {
        HashMap<String, TeamRecord> teamRecordHashMap = new HashMap<>();
        for (TeamInfo team : TeamInfo.values()) {
            addTeamRecordWithSeasons(teamRecordHashMap, team);
        }
        return teamRecordHashMap;
    }

    private void addTeamRecordWithSeasons(HashMap<String, TeamRecord> teamRecordHashMap, TeamInfo team) throws IOException {
        TeamRecord teamRecord = new TeamRecord();
        if (team.seasonsCellRange != null) {
            teamRecord.setSeasons(seasonService.getSeasons(team.seasonsCellRange));
        }
        teamRecordHashMap.put(team.clubName, teamRecord);
    }
}

package com.wafflarchiveapi.API;

import com.wafflarchiveapi.API.drafts.DraftClass;
import com.wafflarchiveapi.API.records.dto.TeamRecord;
import com.wafflarchiveapi.API.records.services.PlayerRecordBookService;
import com.wafflarchiveapi.API.records.services.TeamRecordBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

@RestController
public class Controller {

    @Autowired
    TeamRecordBookService teamRecordBookService;

    @Autowired
    PlayerRecordBookService playerRecordBookService;

    @GetMapping("index/draftData")
    public DraftClass draftClass(@RequestParam(value = "year") String year) throws IOException, GeneralSecurityException {
        return new DraftClass(Integer.parseInt(year));
    }

    @GetMapping("index/statSheet")
    public StatSheet statSheet() throws IOException, GeneralSecurityException {
        return new StatSheet();
    }

    @GetMapping("index/playerRecordBook")
    public HashMap<String, PlayerRecordBookService.PlayerRecord[]> playerRecordBook() throws IOException, GeneralSecurityException {
        return playerRecordBookService.build();
    }

    @GetMapping("index/teamRecordBook")
    public HashMap<String, TeamRecord> teamRecordBook() throws IOException {
        return teamRecordBookService.build();
    }

}

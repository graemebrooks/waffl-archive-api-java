package com.wafflarchiveapi.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class Controller {

    @GetMapping("index/draftData")
    public DraftClass draftClass(@RequestParam(value = "year") String year) throws IOException, GeneralSecurityException {
        return new DraftClass(Integer.parseInt(year));
    }

    @GetMapping("index/statSheet")
    public StatSheet statSheet() throws IOException, GeneralSecurityException {
        return new StatSheet();
    }

    @GetMapping("index/playerRecords")
    public PlayerRecordBook playerRecordBook() throws IOException, GeneralSecurityException {
        return new PlayerRecordBook();
    }

}

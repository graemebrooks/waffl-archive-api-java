package com.wafflarchiveapi.API.records.services;

import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayerRecordBookService {

    @Autowired
    private GoogleSheet googleSheet;

    @Value("${RECORDBOOK_SPREADSHEET_ID}")
    private String RECORDBOOK_SPREADSHEET_ID;

}

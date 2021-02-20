package com.wafflarchiveapi.API.googlesheetsapi;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.wafflarchiveapi.API.googlesheetsapi.GoogleCredentialProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleSheet {

    private static final String APPLICATION_NAME = "waffl-archive";
    public Sheets sheetsService;

    private int count = 0;

    public GoogleSheet() throws IOException,
            GeneralSecurityException {
        this.sheetsService = getSheetsService();
    }

    public List<List<Object>> getSheetValuesFromRange(String spreadsheetId, String range)
            throws IOException {
        ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
        System.out.println(++count);
        return response.getValues();
    }

    private Sheets getSheetsService() throws IOException, GeneralSecurityException {
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), GoogleCredentialProvider.authorize())
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}

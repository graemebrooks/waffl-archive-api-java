package com.wafflarchiveapi.API;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleCredentialProvider {

    private static Sheets sheetsService;
    private static final String APPLICATION_NAME = "waffl-archive";

    private static Credential authorize () throws IOException, GeneralSecurityException {
        InputStream in = GoogleCredentialProvider.class.getResourceAsStream("/credentials.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

        Credential credential = GoogleCredential
                .fromStream(new FileInputStream("src/main/resources/credentials.json"))
                .createScoped(SCOPES);

        return credential;
    }

    public static Sheets getSheetsService() throws IOException,
            GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void getSheetValues(String spreadsheetId, String range) throws IOException,
            GeneralSecurityException {
        sheetsService = getSheetsService();


        ValueRange response =
                sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            for (List row : values) {
                System.out.println(row.get(0));
            }
        }
    }

}

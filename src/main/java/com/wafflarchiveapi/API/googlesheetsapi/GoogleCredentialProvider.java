package com.wafflarchiveapi.API.googlesheetsapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleCredentialProvider {

    public static Credential authorize () throws IOException, GeneralSecurityException {
        return GoogleCredential
                .fromStream(new FileInputStream("src/main/resources/credentials.json"))
                .createScoped(getScopes(SheetsScopes.SPREADSHEETS_READONLY));
    }

    private static List<String> getScopes(String sheetScopeOption) {
        return Collections.singletonList(sheetScopeOption);
    }
}

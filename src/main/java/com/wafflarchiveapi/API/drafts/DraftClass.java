package com.wafflarchiveapi.API.drafts;

import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import com.wafflarchiveapi.API.Round;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class DraftClass {

    public int draftYear;
    public DraftRound firstRound;
    public DraftRound secondRound;
    public DraftRound thirdRound;

    private GoogleSheet googleSheet;
    private String DRAFT_SPREADSHEET_ID;

    public DraftClass(int draftYear) throws IOException, GeneralSecurityException {
        this.draftYear = draftYear;
        this.googleSheet = new GoogleSheet();
        this.DRAFT_SPREADSHEET_ID = "1UyEK38Llr17ph_qrnuN2CUaKZitpXoKoR59uJqVqgIc";
        populateDraftRounds();
    }

    private void populateDraftRounds() throws IOException {
        firstRound = new DraftRound(Round.FIRST, getDraftRoundSheetValues(Round.FIRST));
        secondRound = new DraftRound(Round.SECOND, getDraftRoundSheetValues(Round.SECOND));
        thirdRound = new DraftRound(Round.THIRD, getDraftRoundSheetValues(Round.THIRD));
    }

    private List<List<Object>> getDraftRoundSheetValues(Round round) throws IOException {
        return googleSheet.getSheetValuesFromRange(DRAFT_SPREADSHEET_ID,
                getFormattedRange(round.getCellRange()));
    }

    private String getFormattedRange(String cellRange) {
        return String.valueOf(draftYear) + " Draft!" + cellRange;
    }
}

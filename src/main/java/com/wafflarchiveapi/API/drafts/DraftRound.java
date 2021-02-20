package com.wafflarchiveapi.API.drafts;

import com.wafflarchiveapi.API.Round;

import java.util.ArrayList;
import java.util.List;

public class DraftRound {

    public Round round;
    public ArrayList<DraftSelection> draftSelections;

    public DraftRound(Round round, List<List<Object>> draftSelectionsCellData) {
        this.round = round;
        this.draftSelections = new ArrayList<DraftSelection>();
        populateDraftSelections(draftSelectionsCellData);
    }

    private void populateDraftSelections(List<List<Object>> draftSelectionsCellData) {
        for (int i = 0; i < draftSelectionsCellData.size(); i++) {
            draftSelections.add(i, new DraftSelection(
                    Integer.parseInt(String.valueOf(draftSelectionsCellData.get(i).get(0))),
                    String.valueOf(draftSelectionsCellData.get(i).get(1)),
                    String.valueOf(draftSelectionsCellData.get(i).get(2))
            ));
        }
    }

    public static class DraftSelection {

        public int pickNumber;
        public String owner;
        public String player;

        public DraftSelection(int pickNumber, String owner, String player) {
            this.pickNumber = pickNumber;
            this.owner = owner;
            this.player = player;
        }
    }

}

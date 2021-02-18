package com.wafflarchiveapi.API;

public enum TeamInfo {
    BEER ("Beer","Austin", "https://i.imgur.com/aTs50El.png", "#00005b", "#ffce00"),
    CARTELS ("Cartels", "El Paso", "https://i.imgur.com/lIRYULG.png", "ee8a10", "#dd366d"),
    CEREAL_KILLERS ("Ceral Killers", "Addison", "https://i.imgur.com/E2zSnEJ.png","#251903", "#df1d35"),
    CHILI_PEPPERS ("CHili Peppers", "California", "https://i.imgur.com/a9jFlAW.png", "#dd1919", "#ffb600"),
    DIGITAL_RAYS ("Digital Rays", "Rock Rose", "https://i.imgur.com/Je5Chs0.png", "#00f1ee", "#db00c9"),
    FEVER ("Fever", "Cedar Park", "https://i.imgur.com/V3qdKPU.png", "#045f1f", "#cccf44"),
    FLUSH ("Flush", "North Loop", "https://i.imgur.com/s1cIn4l.png", "#6bc3d5", "#ffffff"),
    ICE_BABIES ("Ice Babies", "Cedar Park", "https://i.imgur.com/KE0cDMD.png", "#7437bf", "#ffffff"),
    ISLANDERS ("Islanders", "Central Texas", "https://i.imgur.com/bDpjNeF.png", "1e6b0e", "ff6d00"),
    LIBRARIANS ("Librarians", "San Marcos", "https://i.imgur.com/znuRXVw.png", "#2138b8", "#b80403"),
    NADOES ("Nadoes", "Stillwater", "https://i.imgur.com/lLmmaih.png", "#41b1f0", "#a7a7a7"),
    NOT_THE_JETS ("Not the Jets", "University of Texas", "https://i.imgur.com/q4j1M2D.png",
            "#000b6d", "#ffe600"),
    REDACTED ("Redacted", "Anderson Mill", "https://i.imgur.com/EdXYU3Y.png", "#151515", "#ebebeb"),
    TOAD_LICKERS ("Toad Lickers", "Travis County", "https://i.imgur.com/TpSQJZN.png", "#206632", "#e53841"),
    WENCHES ("Wenches", "Wrigleyville", "https://i.imgur.com/qVAzfoj.png", "#00205b", "#fa6300"),
    WHALE_SHARKS ("Whale Sharks", "Walnut Creek", "https://i.imgur.com/tW0aTiP.png", "#62c4e9", "#f10909"),
    ;

    public final String clubName;
    public final String currentLocation;
    public final String logoURL;
    public final String primaryColor;
    public final String secondaryColor;

    TeamInfo(String clubName, String currentLocation, String logoURL, String primaryColor,
             String secondaryColor) {
        this.clubName = clubName;
        this.currentLocation =currentLocation;
        this.logoURL = logoURL;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }
}

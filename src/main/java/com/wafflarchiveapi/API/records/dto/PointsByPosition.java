package com.wafflarchiveapi.API.records.dto;

public class PointsByPosition {

        private Double runningback;
        private Double quarterback;
        private Double wideReceiver;
        private Double tightEnd;
        private Double kicker;
        private Double defense;

        public Double getRunningback() {
            return runningback;
        }

        public Double getQuarterback() {
            return quarterback;
        }

        public Double getWideReceiver() {
            return wideReceiver;
        }

        public Double getTightEnd() {
            return tightEnd;
        }

        public Double getKicker() {
            return kicker;
        }

        public Double getDefense() {
            return defense;
        }

        public void setRunningback(Double runningback) {
            this.runningback = runningback;
        }

        public void setQuarterback(Double quarterback) {
            this.quarterback = quarterback;
        }

        public void setWideReceiver(Double wideReceiver) {
            this.wideReceiver = wideReceiver;
        }

        public void setTightEnd(Double tightEnd) {
            this.tightEnd = tightEnd;
        }

        public void setKicker(Double kicker) {
            this.kicker = kicker;
        }

        public void setDefense(Double defense) {
            this.defense = defense;
        }
}

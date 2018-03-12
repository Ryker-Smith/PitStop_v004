package net.fachtnaroe.pcpitstop.pitstop_v004;

/**
 * Created by fachtna on 20/02/18.
 */

public class PPSN {
    public String Number;

    public PPSN() {
        // Constructor
        Number = new String("1234567T");
    }

    public String Get() {
        return Number;
    }

    public Boolean Set(String CandidatePPS) {
        if (Valid(CandidatePPS)) {
            Number = CandidatePPS;
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean Valid(String CandidatePPS) {
        // this will ONLY work with PPS of
        // form nnnnnnnA
        if (CandidatePPS.length() != 8) {
            return false;
        }
        char[] chars = CandidatePPS.toCharArray();
        Integer multiplier = 8;
        Integer total = 0;
        Integer remainder;

        for(Integer i=0; i < 7;i++) {
            total +=  ( ((int) chars[i] - 48) * multiplier);
            multiplier--;
        }
        remainder = total % 23;
        if ((char)(remainder + 64) == chars[7]) {
            return true;
        }
        else {
            return false;
        }
    }

}
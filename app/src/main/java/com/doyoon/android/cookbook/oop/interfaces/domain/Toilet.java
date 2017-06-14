package com.doyoon.android.cookbook.oop.interfaces.domain;

/**
 * Created by DOYOON on 6/13/2017.
 */

public class Toilet {
    public String OBJECTID;
    public String GU_NM;
    public String HNR_NAM;
    public String MTC_AT;
    public String MASTERNO;
    public String SLAVENO;
    public String NEADRES_NM;
    public String CREAT_DE;
    public String LAT;
    public String LNG;

    public String getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(String OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getGU_NM() {
        return GU_NM;
    }

    public void setGU_NM(String GU_NM) {
        this.GU_NM = GU_NM;
    }

    public String getHNR_NAM() {
        return HNR_NAM;
    }

    public void setHNR_NAM(String HNR_NAM) {
        this.HNR_NAM = HNR_NAM;
    }

    public String getMTC_AT() {
        return MTC_AT;
    }

    public void setMTC_AT(String MTC_AT) {
        this.MTC_AT = MTC_AT;
    }

    public String getMASTERNO() {
        return MASTERNO;
    }

    public void setMASTERNO(String MASTERNO) {
        this.MASTERNO = MASTERNO;
    }

    public String getSLAVENO() {
        return SLAVENO;
    }

    public void setSLAVENO(String SLAVENO) {
        this.SLAVENO = SLAVENO;
    }

    public String getNEADRES_NM() {
        return NEADRES_NM;
    }

    public void setNEADRES_NM(String NEADRES_NM) {
        this.NEADRES_NM = NEADRES_NM;
    }

    public String getCREAT_DE() {
        return CREAT_DE;
    }

    public void setCREAT_DE(String CREAT_DE) {
        this.CREAT_DE = CREAT_DE;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLNG() {
        return LNG;
    }

    public void setLNG(String LNG) {
        this.LNG = LNG;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

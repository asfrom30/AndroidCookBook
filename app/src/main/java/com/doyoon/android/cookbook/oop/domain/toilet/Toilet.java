package com.doyoon.android.cookbook.oop.domain.toilet;

/**
 * Created by DOYOON on 6/13/2017.
 */

public class Toilet {
    private GeoInfoPublicToiletWGS GeoInfoPublicToiletWGS;

    public GeoInfoPublicToiletWGS getGeoInfoPublicToiletWGS ()
    {
        return GeoInfoPublicToiletWGS;
    }

    public void setGeoInfoPublicToiletWGS (GeoInfoPublicToiletWGS GeoInfoPublicToiletWGS)
    {
        this.GeoInfoPublicToiletWGS = GeoInfoPublicToiletWGS;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [GeoInfoPublicToiletWGS = "+GeoInfoPublicToiletWGS+"]";
    }
}

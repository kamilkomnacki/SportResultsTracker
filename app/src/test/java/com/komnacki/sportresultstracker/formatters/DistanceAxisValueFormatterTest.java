package com.komnacki.sportresultstracker.formatters;

import org.junit.Assert;
import org.junit.Test;

public class DistanceAxisValueFormatterTest {
    private DistanceAxisValueFormatter distanceAxisValueFormatter = new DistanceAxisValueFormatter();

    @Test
    public void testGetFormattedValue() throws Exception {
        String result = distanceAxisValueFormatter.getFormattedValue(0f, null);
        Assert.assertEquals("0.0m", result);

        result = distanceAxisValueFormatter.getFormattedValue(13.913f, null);
        Assert.assertEquals("13.913m", result);

        result = distanceAxisValueFormatter.getFormattedValue(512.32f, null);
        Assert.assertEquals("512.32m", result);

        result = distanceAxisValueFormatter.getFormattedValue(1533f, null);
        Assert.assertEquals("1.533 km", result);

        result = distanceAxisValueFormatter.getFormattedValue(32000f, null);
        Assert.assertEquals("32.000 km", result);
    }
}
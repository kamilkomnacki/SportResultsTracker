package com.komnacki.sportresultstracker.formatters;

import org.junit.Assert;
import org.junit.Test;

public class TimeAxisValueFormatterTest {
    TimeAxisValueFormatter timeAxisValueFormatter = new TimeAxisValueFormatter();

    @Test
    public void testGetFormattedValue() throws Exception {
        String result = timeAxisValueFormatter.getFormattedValue(-1, null);
        Assert.assertEquals("", result);

        result = timeAxisValueFormatter.getFormattedValue(0f, null);
        Assert.assertEquals("", result);

        result = timeAxisValueFormatter.getFormattedValue(5, null);
        Assert.assertEquals("5 ms", result);

        result = timeAxisValueFormatter.getFormattedValue(13, null);
        Assert.assertEquals("13 ms", result);

        result = timeAxisValueFormatter.getFormattedValue(429, null);
        Assert.assertEquals("429 ms", result);

        result = timeAxisValueFormatter.getFormattedValue(1236, null);
        Assert.assertEquals("1:236 s", result);

        result = timeAxisValueFormatter.getFormattedValue(53850, null);
        Assert.assertEquals("53:850 s", result);

        result = timeAxisValueFormatter.getFormattedValue(813943, null);
        Assert.assertEquals("8:13:943 min", result);

        result = timeAxisValueFormatter.getFormattedValue(4123914, null);
        Assert.assertEquals("41:23:914 min", result);

        result = timeAxisValueFormatter.getFormattedValue(32658096, null);
        Assert.assertEquals("3:26:58:096 h", result);

        result = timeAxisValueFormatter.getFormattedValue(380137594, null);
        Assert.assertEquals("38:01:37:600 h", result);


    }
}
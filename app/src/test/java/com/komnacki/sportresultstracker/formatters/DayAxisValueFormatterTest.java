package com.komnacki.sportresultstracker.formatters;

import com.github.mikephil.charting.charts.BarLineChartBase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DayAxisValueFormatterTest {
    @Mock
    BarLineChartBase<?> chart;
    @InjectMocks
    DayAxisValueFormatter dayAxisValueFormatter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetFormattedValue() throws Exception {
        String result = dayAxisValueFormatter.getFormattedValue(18300f, null);
        Assert.assertEquals("8th Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18299f, null);
        Assert.assertEquals("7th Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18291f, null);
        Assert.assertEquals("30th Jan", result);

        result = dayAxisValueFormatter.getFormattedValue(18292f, null);
        Assert.assertEquals("31st Jan", result);

        result = dayAxisValueFormatter.getFormattedValue(18293f, null);
        Assert.assertEquals("1st Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18294f, null);
        Assert.assertEquals("2nd Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18295f, null);
        Assert.assertEquals("3rd Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18296f, null);
        Assert.assertEquals("4th Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18297f, null);
        Assert.assertEquals("5th Feb", result);

        result = dayAxisValueFormatter.getFormattedValue(18298f, null);
        Assert.assertEquals("6th Feb", result);
    }
}